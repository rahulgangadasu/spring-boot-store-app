package com.storeapi.store.dtos;

import com.storeapi.store.validations.Lowercase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Lowercase(message = "Email must be lowercase") //custom validator.
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5, max = 25, message = "Password must be at least 5 characters long")
    private String password;
}
