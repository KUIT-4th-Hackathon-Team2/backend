package com.konkukrent.demo.dto.RentalDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RentalRequestDto {
    @NotNull
    private Long reservationId;
}
