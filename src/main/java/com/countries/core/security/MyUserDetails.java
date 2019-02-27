package com.countries.core.security;

import com.countries.core.exceptions.CustomException;
import com.countries.core.utils.AppUtils;
import com.countries.jpa.entity.User;
import com.countries.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Slf4j
public class MyUserDetails implements UserDetailsService {

    private UserRepository userRepository;
    private AuthorityDetails authorityDetails;

    @Autowired
    public MyUserDetails(UserRepository userRepository, AuthorityDetails authorityDetails) {
        this.userRepository = userRepository;
        this.authorityDetails = authorityDetails;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {

        User user;

        try {
//            if(username.contains("@")) {
//                userOptional = userRepository.findByEmail(username);
//            } else {
//                userOptional = userRepository.findUserByUsername(username);
//            }
            Optional<User> userOptional = userRepository.findUserByUsername(username);

            log.info("user details : {}", AppUtils.toJSON(userOptional));

            if (userOptional.isPresent()) {
                user = userOptional.get();
                log.info("user getUsername : {}", user.getUsername());
                if (user.getStatus().equals(true)) {
                    user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
                    userRepository.saveAndFlush(user);

                    return org.springframework.security.core.userdetails.User//
                            .withUsername(username)//
                            .password(user.getPassword())//
                            .authorities(authorityDetails.getAuthorities(user))//
                            .accountExpired(false)//
                            .accountLocked(false)//
                            .credentialsExpired(false)//
                            .disabled(false)//
                            .build();
                }
            }

        } catch(Exception e){
            throw new CustomException("user not found", HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
