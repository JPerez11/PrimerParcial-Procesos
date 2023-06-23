package com.procesos.parcial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "Title field cannot be empty")
    private String title;
    @NotNull(message = "Price field cannot be empty")
    private double price;
    @NotBlank(message = "Description field cannot be empty")
    private String description;
    @NotBlank(message = "Category field cannot be empty")
    private String category;
    @NotBlank(message = "Image field cannot be empty")
    private String image;
    @NotNull(message = "User id field cannot be empty")
    private Long userId;

}
