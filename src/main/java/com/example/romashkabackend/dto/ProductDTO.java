package com.example.    romashkabackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull
    @NotBlank(message = "Name should not blank")
    @Size(max = 255, message = "Product name is limited to 255 characters")
    String name;

    @Size(max = 4096, message = "Product description is limited to 4096 characters")
    String description;

    @Min(value = 0, message = "The price of the product cannot be less than 0")
    double price = 0;

    Boolean available = false;
}