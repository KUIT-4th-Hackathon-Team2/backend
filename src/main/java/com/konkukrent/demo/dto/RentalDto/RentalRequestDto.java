package com.konkukrent.demo.dto.RentalDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RentalRequestDto {
    private Long productId;
    private Long userId;
}
