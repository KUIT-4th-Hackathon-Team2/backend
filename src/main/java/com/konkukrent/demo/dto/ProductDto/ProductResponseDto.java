package com.konkukrent.demo.dto.ProductDto;

import lombok.*;

@Data
@AllArgsConstructor

public class ProductResponseDto {
    private Long id;
    private String name;
    private int totalNumber;
    private int remainNumber;
    private int rentalPeriod;
}
