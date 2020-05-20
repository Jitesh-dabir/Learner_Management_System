package com.bl.learningmanagementsystem.controller;

import com.bl.learningmanagementsystem.dto.JwtRequest;
import com.bl.learningmanagementsystem.dto.PasswordResetModel;
import com.bl.learningmanagementsystem.dto.UserDTO;
import com.bl.learningmanagementsystem.responsedto.JwtResponse;
import com.bl.learningmanagementsystem.responsedto.Response;
import com.bl.learningmanagementsystem.model.User;
import com.bl.learningmanagementsystem.repository.UserRepository;
import com.bl.learningmanagementsystem.service.EmailServiceImpl;
import com.bl.learningmanagementsystem.service.UserDetailsServiceImpl;
import com.bl.learningmanagementsystem.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
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

    @GetMapping("/login")
    public String login() {
        return "Login successFull";
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @GetMapping("/requestResetPassword")
    public Response requestResetPassword(@Valid @RequestParam(value = "email") String email) throws AddressException, MessagingException {
        User user = userRepository.findByEmail(email);
        final String token = jwtTokenUtil.generatePasswordResetToken(String.valueOf(user.getId()));
        emailService.sentEmail(user, token);
        return new Response(200, token);
    }

    @PutMapping("/resetPassword")
    public Response resetPassword(@Valid @RequestBody PasswordResetModel passwordRequestModel) {
        boolean result = userDetailsService.resetPassword(passwordRequestModel.getPassword(), passwordRequestModel.getToken());
        if (result)
            return new Response(200, "Successfully updated");
        return new Response(500, "UnSuccessFull");
    } 
}