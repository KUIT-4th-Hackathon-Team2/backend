package com.konkukrent.demo.dto.RentalDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RentalResponseDto {
    private Long rentalId;
    private Long productId;
    private String productName;
    private Long userId;
    private String userName;
    private Long studentNum;
    private LocalDateTime rentalTime;
    private LocalDate expirationDate;
}
