package org.example.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EmailVerificationBody (
        @NotNull(message = "fromEmail Cannot be null")
        @NotEmpty(message = "fromEmail Cannot be empty")
        String fromEmail,
        @NotNull(message = "From cannot be null")
        @NotEmpty(message = "From cannot be empty")
        String from,
        @NotNull(message = "To cannot be null")
        @NotEmpty(message = "To cannot be empty")
        String to,
        @NotNull(message = "Subject cannot be null")
        @NotEmpty(message = "Subject cannot be empty")
        String subject,
        @NotNull(message = "Verification token cannot be null")
        @NotEmpty(message = "Verification token cannot be empty")
        String verificationToken
){

}
