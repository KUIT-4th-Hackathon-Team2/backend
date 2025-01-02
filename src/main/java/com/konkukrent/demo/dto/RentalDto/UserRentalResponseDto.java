package com.konkukrent.demo.dto.RentalDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class UserRentalResponseDto {

    private Long rentalId;
    private Long productId;
    private String productName;
    private LocalDateTime rentalTime;
    private LocalDateTime expirationDate;
}
