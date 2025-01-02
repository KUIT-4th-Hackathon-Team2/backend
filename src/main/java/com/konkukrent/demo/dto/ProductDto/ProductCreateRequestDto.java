package com.konkukrent.demo.dto.ProductDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class ProductCreateRequestDto {

    @NotNull
    private String name;

    @NotNull
    private int totalNumber;

    @NotNull
    private int rentalPeriod;

}
