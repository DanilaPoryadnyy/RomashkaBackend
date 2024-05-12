package com.example.romashkabackend.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    @NotBlank(message = "Имя обязательно")
    @Size(max = 255, message = "Название товара ограничено 255 символами")
    private String name;

    @Size(max = 4096, message = "Описание товара ограничено 4096 символами")
    private String description;

    @Min(value = 0, message = "Цена товара не может быть меньше 0")
    private double price = 0;

    private Boolean available = false;
}
