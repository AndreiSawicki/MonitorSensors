package com.ocr.monitorsensors.security.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Authentication request")
public class SignInRequest {

    @Schema(description = "User name")
    @NotBlank
    private String username;

    @Schema(description = "Password")
    @NotBlank
    private String password;
}
