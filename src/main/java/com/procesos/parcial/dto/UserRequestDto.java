package com.procesos.parcial.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Name field cannot be empty")
    private String name;
    @NotBlank(message = "Last name field cannot be empty")
    private String lastName;
    @Email
    @NotBlank(message = "Email field cannot be empty")
    private String email;
    @NotBlank(message = "Password field cannot be empty")
    private String password;
    @NotBlank(message = "Address field cannot be empty")
    private String address;
    @Past(message = "Must be a past date")
    @NotNull(message = "Birthday field cannot be empty")
    private LocalDate birthday;

}
