package com.konkukrent.demo.dto.RentalDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class RentalResponseDto {
    private Long rentalId;
    private Long productId;
    private String productName;
    private Long userId;
    private String userName;
    private int studentNum;
    private LocalDateTime rentalTime;
    private LocalDateTime expirationDate;
}
