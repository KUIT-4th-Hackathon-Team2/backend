package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.ReservationRequestDto;
import com.konkukrent.demo.dto.ReservationResponseDto;
import com.konkukrent.demo.entity.Reservation;
import com.konkukrent.demo.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservations")
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @Operation(
            summary = "모든 예약 내역 조회",
            description = "모든 예약 내역을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "모든 예약 내역을 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> findAllReservations() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

    @Operation(
            summary = "사용자 예약 내역 조회",
            description = "사용자의 예약 내역을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "사용자의 예약 내역을 조회합니다."
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDto>> findReservationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.findReservationsByUserId(userId));
    }

    @Operation(
            summary = "예약 생성",
            description = "물품 대여를 예약합니다."
    )
    @ApiResponse(
            responseCode = "201",
            description = "물품 대여 예약에 성공하였습니다."
    )
    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        return ResponseEntity.status(201).body(reservationService.createReservation(reservationRequestDto));
    }

    @Operation(
            summary = "예약 취소",
            description = "물품 대여 예약을 취소합니다."
    )
    @ApiResponse(
            responseCode = "204",
            description = "물품 대여 예약을 취소하였습니다."
    )
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
