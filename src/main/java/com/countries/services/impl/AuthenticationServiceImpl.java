package com.countries.services.impl;

import com.countries.core.exceptions.CustomException;
import com.countries.core.security.JwtAuthenticationRequest;
import com.countries.core.security.TokenHelper;
import com.countries.core.security.UserTokenState;
import com.countries.jpa.entity.User;
import com.countries.jpa.repository.UserRepository;
import com.countries.services.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.countries.core.constants.AppConstant.Security.EXPIRE_IN;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private TokenHelper tokenHelper;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    public AuthenticationServiceImpl(TokenHelper tokenHelper, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenHelper = tokenHelper;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public UserTokenState createAuthenticationToken(JwtAuthenticationRequest authenticationRequest) {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        Optional<User> optionalUser = userRepository.findUserByUsername(authenticationRequest.getUsername());
        if (!optionalUser.isPresent()) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();
        String jwtToken = tokenHelper.createToken(authenticationRequest.getUsername(), user.getRoles());

        return new UserTokenState(jwtToken, EXPIRE_IN);
    }
}
