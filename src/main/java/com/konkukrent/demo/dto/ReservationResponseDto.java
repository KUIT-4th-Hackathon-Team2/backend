package com.konkukrent.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationResponseDto {
    private Long reservationId;

    private Long productId;
    private String productName;

    private Long userId;
    private String userName;
    private Long studentNum;

    private LocalDateTime reservationTime;
}
