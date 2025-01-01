package com.konkukrent.demo.dto.RentalDto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserRentalResponseDto {

    private Long rentalId;
    private Long productId;
    private String productName;
    private Instant rentalTime;
    private Instant expirationDate;
}
