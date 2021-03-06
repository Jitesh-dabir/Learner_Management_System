package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.EmailDto;
import com.bl.learningmanagementsystem.dto.LoginDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.User;
import com.bl.learningmanagementsystem.repository.UserRepository;
import com.bl.learningmanagementsystem.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, IUserDetailsService {

    @Value("${spring.redis.key}")
    private static final String REDIS_INDEX_KEY = "LMS_TOKEN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private IRedisUtil redisUtil;

    @Autowired
    private IRabbitMq rabbitMq;

    /**
     * @param username
     * @return response(Method to load user by its username from database.)
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType
                .USER_NOT_FOUND, "User not found with username"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    /**
     * @param loginDto
     * @return response(Method to valid user details and allow for login)
     */
    @Override
    public Map<Object, Object> loginUser(LoginDto loginDto) throws Exception {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType
                .INVALID_EMAIL_ID, "User not found with email"));
        String authenticationToken = getAuthenticationToken(user.getEmail(), loginDto.getPassword());
        redisUtil.save(REDIS_INDEX_KEY, user.getEmail(), authenticationToken);
        return redisUtil.getAll(REDIS_INDEX_KEY);
    }

    /**
     * @param userName, password
     * @return Method to get authentication token
     * @throws Exception
     */
    @Override
    public String getAuthenticationToken(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userName, password));
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userName);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    /**
     * @param user
     * @return response(Method to register user and save to database)
     */
    @Override
    public User save(UserDto user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User newUser = modelMapper.map(user, User.class);
        User registeredUser = userRepository.save(newUser);
        registeredUser.setCreatorUser(registeredUser.getId());
        return userRepository.save(newUser);
    }

    /**
     * @param password
     * @param token
     * @return Method to reset password.
     */
    @Override
    public User resetPassword(String password, String token) {
        String encodedPassword = bcryptEncoder.encode(password);
        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new LmsAppServiceException(LmsAppServiceException.exceptionType.INVALID_TOKEN, "Token expired");
        }
        long id = Long.valueOf(jwtTokenUtil.getSubjectFromToken(token));
        return userRepository.findById(id)
                .map(user -> {
                    user.setPassword(encodedPassword);
                    return user;
                })
                .map(userRepository::save).get();
    }

    /**
     * @param email
     * @return Method to get password reset token.
     * @throws MessagingException
     * @throws LmsAppServiceException
     */
    @Override
    public String getResetPasswordToken(String email) throws MessagingException, LmsAppServiceException, JsonProcessingException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType
                .INVALID_EMAIL_ID, "User not found with email"));
        final String token = jwtTokenUtil.generatePasswordResetToken(String.valueOf(user.getId()));
        sentEmail(user, token);
        return token;
    }

    /**
     * @param user, token
     * @return Method to send reset password request email.
     * @throws MessagingException
     */
    public void sentEmail(User user, String token) throws MessagingException, JsonProcessingException {
        EmailDto emailDto = new EmailDto();
        emailDto.setTo(user.getEmail());
        emailDto.setSubject("Password-Reset-Request");
        emailDto.setText("Hii " + user.getFirstName() + "\n" + " You requested to reset password, if YES then click on link put your new password and NO then ignore \n" +
                "http://localhost:8084/reset_password?json={%22password%22:%22" + null + "%22+,%22token%22:" + token + "}");
       rabbitMq.send(emailDto);
       //rabbitMq.sendMail(emailDto);
    }
}