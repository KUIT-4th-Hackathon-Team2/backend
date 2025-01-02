package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.ReservationRequestDto;
import com.konkukrent.demo.dto.ReservationResponseDto;
import com.konkukrent.demo.entity.Reservation;
import com.konkukrent.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> findAllReservations() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDto>> findReservationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.findReservationsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        return ResponseEntity.status(201).body(reservationService.createReservation(reservationRequestDto));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
