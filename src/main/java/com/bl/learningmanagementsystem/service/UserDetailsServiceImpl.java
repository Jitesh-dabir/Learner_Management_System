package com.bl.learningmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.bl.learningmanagementsystem.dto.Response;
import com.bl.learningmanagementsystem.responseDto.UserDTO;
import com.bl.learningmanagementsystem.repository.UserRepository;
import com.bl.learningmanagementsystem.service.UserService;
import com.bl.learningmanagementsystem.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bl.learningmanagementsystem.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

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
    private JavaMailSender sender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getFirst_name(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public Response save(UserDTO user) {
        user.setCreator_stamp(LocalDateTime.now());
        user.setCreator_user(user.getFirst_name());
        user.setVerified("yes");
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User newUser = modelMapper.map(user, User.class);
        userRepository.save(newUser);
        return new Response(200, "Register successfull");
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
    public void sentEmail(User user, String token) throws MessagingException {
        String recipientAddress = user.getEmail();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(recipientAddress);
        helper.setText("Hii " + user.getFirst_name() + "\n" + " You requested to reset password, if YES then click on link put your new password and NO then ignore \n" +
                "http://localhost:8084/reset_password?json={%22password%22:%22" + null + "%22+,%22token%22:" + token + "}");
        helper.setSubject("Password-Reset-Request");
        sender.send(message);
    }
}
