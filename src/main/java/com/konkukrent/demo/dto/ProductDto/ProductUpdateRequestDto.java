package com.konkukrent.demo.dto.ProductDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor

public class ProductUpdateRequestDto {

    @NotNull
    private int remainNumber;
}