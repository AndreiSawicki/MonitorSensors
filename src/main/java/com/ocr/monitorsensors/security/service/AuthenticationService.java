package com.ocr.monitorsensors.security.service;

import com.ocr.monitorsensors.security.data.dto.AuthenticationResponse;
import com.ocr.monitorsensors.security.data.dto.SignInRequest;
import com.ocr.monitorsensors.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signIn(SignInRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        return new AuthenticationResponse(jwtService.generateToken(user));
    }
}
