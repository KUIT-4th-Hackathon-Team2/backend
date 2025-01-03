package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.ReservationRequestDto;
import com.konkukrent.demo.dto.ReservationResponseDto;
import com.konkukrent.demo.entity.Product;
import com.konkukrent.demo.entity.Rental;
import com.konkukrent.demo.entity.Reservation;
import com.konkukrent.demo.entity.User;
import com.konkukrent.demo.exception.exceptionClass.CustomException;
import com.konkukrent.demo.repository.ProductRepository;
import com.konkukrent.demo.repository.ReservationRepository;
import com.konkukrent.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.konkukrent.demo.exception.properties.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ReservationResponseDto> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDto> findReservationsByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto) {
        User user = userRepository.findById(reservationRequestDto.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Product product = productRepository.findById(reservationRequestDto.getProductId())
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        Reservation reservation = new Reservation(user, product);
        Reservation saved = reservationRepository.save(reservation);
        return entityToDto(saved);
    }

    @Transactional
    public void deleteReservation(Long reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            throw new CustomException(RESERVATION_NOT_FOUND);
        }
        reservationRepository.deleteById(reservationId);
    }

    private ReservationResponseDto entityToDto(Reservation reservation) {
        User user = reservation.getUser();
        Product product = reservation.getProduct();

        return new ReservationResponseDto(
                reservation.getId(),
                product.getId(),
                product.getName(),
                user.getId(),
                user.getName(),
                user.getStudentNum(),
                reservation.getReservationTime()
        );
    }
}
