package com.procesos.parcial.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private String name;
    private String lastName;
    private String email;
    private String address;
    private LocalDate birthday;

}
