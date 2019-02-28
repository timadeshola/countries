package com.countries.services;

import com.countries.core.security.JwtAuthenticationRequest;
import com.countries.core.security.UserTokenState;

public interface AuthenticationService {

    UserTokenState createAuthenticationToken(JwtAuthenticationRequest authenticationRequest);
}
