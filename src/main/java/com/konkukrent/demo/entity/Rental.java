package com.konkukrent.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Rental {
    private Long rentalId;
    private Instant rentalTime;
    private Instant expirationDate;
    private Long userId;
    private Long productId;
}
