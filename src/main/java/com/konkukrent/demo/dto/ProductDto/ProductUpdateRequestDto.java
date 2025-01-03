package com.konkukrent.demo.dto.ProductDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDto {

    private String name;
    private Integer totalNumber;
    private Integer remainNumber;
    private Integer rentalPeriod;
}