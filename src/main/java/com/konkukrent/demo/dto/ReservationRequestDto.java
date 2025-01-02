package com.konkukrent.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequestDto {
    @NotNull
    private Long productId;

    @NotNull
    private Long userId;
}
