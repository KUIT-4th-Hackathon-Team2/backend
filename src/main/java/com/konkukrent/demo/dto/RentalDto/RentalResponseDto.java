package com.konkukrent.demo.dto.RentalDto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RentalResponseDto {
    private Long rentalId;
    private Long productId;
    private String productName;
    private Long userId;
    private String userName;
    private int studentNum;
    private Instant rentalTime;
    private Instant expirationDate;
}
