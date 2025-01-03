package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.RentalDto.RentalRequestDto;
import com.konkukrent.demo.dto.RentalDto.RentalResponseDto;
import com.konkukrent.demo.dto.RentalDto.UserRentalResponseDto;
import com.konkukrent.demo.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Rentals")
@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Operation(
            summary = "모든 대여 내역 조회",
            description = "모든 대여 내역을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "모든 대여 내역을 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<RentalResponseDto>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @Operation(
            summary = "사용자 대여 내역 조회",
            description = "사용자의 대여 내역을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "사용자의 대여 내역을 조회합니다."
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRentalResponseDto>> getRentalsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(rentalService.getRentalsByUserId(userId));
    }

    @Operation(
            summary = "물품 대여",
            description = "물품을 대여합니다."
    )
    @ApiResponse(
            responseCode = "201",
            description = "물품 대여에 성공하였습니다."
    )
    @PostMapping
    public ResponseEntity<RentalResponseDto> createRental(@RequestBody RentalRequestDto request) {
        RentalResponseDto response = rentalService.createRental(request);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(
            summary = "물품 반납",
            description = "물품을 반납합니다."
    )
    @ApiResponse(
            responseCode = "204",
            description = "물품 반납에 성공하였습니다."
    )
    @DeleteMapping("/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long rentalId) {
        rentalService.deleteRental(rentalId);
        return ResponseEntity.noContent().build();
    }
}
