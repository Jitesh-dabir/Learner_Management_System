package com.bl.learningmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.bl.learningmanagementsystem.dto.JwtRequestDto;
import com.bl.learningmanagementsystem.dto.UserDto;
import com.bl.learningmanagementsystem.response.ResponseDto;
import com.bl.learningmanagementsystem.repository.UserRepository;
import com.bl.learningmanagementsystem.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bl.learningmanagementsystem.model.User;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, IUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public ResponseDto save(UserDto user) {
        user.setCreatorStamp(LocalDateTime.now());
        user.setCreatorUser(user.getFirstName());
        user.setVerified("yes");
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User newUser = modelMapper.map(user, User.class);
        userRepository.save(newUser);
        return new ResponseDto(200, "Register successfull");
    }

    @Override
    public boolean resetPassword(String password, String token) {

        String encodedPassword = bcryptEncoder.encode(password);
        if (jwtTokenUtil.isTokenExpired(token)) {
            return false;
        }
        long id = Long.parseLong(jwtTokenUtil.getSubjectFromToken(token));

        User user = entityManager.find(User.class, id);
        user.setPassword(encodedPassword);
        User updatedUser = userRepository.save(user);
        if (updatedUser != null && updatedUser.getPassword().equalsIgnoreCase(encodedPassword))
            return true;
        return false;
    }

    @Override
    public String getResetPasswordToken(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        final String token = jwtTokenUtil.generatePasswordResetToken(String.valueOf(user.getId()));
        //emailService.sentEmail(user, token);
        return token;
    }

    @Override
    public String getAuthenticationToken(JwtRequestDto authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
