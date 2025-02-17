package com.ocr.monitorsensors.security.controller;

import com.ocr.monitorsensors.api.controller.AbstractSensorAPIController;
import com.ocr.monitorsensors.security.data.dto.AuthenticationResponse;
import com.ocr.monitorsensors.security.data.dto.SignInRequest;
import com.ocr.monitorsensors.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController extends AbstractSensorAPIController {
    private final AuthenticationService authenticationService;


    @Operation(summary = "Receive authentication token by name and password")
    @PostMapping("/sign-in")
    public AuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        log.info("New autentification request from {}", request.getUsername());
        return authenticationService.signIn(request);
    }
}
