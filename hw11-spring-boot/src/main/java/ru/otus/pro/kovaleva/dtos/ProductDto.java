package ru.otus.pro.kovaleva.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductDto {
    private String title;
    private Double price;
}
