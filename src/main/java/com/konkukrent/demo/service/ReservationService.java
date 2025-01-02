package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.ReservationRequestDto;
import com.konkukrent.demo.dto.ReservationResponseDto;
import com.konkukrent.demo.entity.Reservation;
import com.konkukrent.demo.entity.User;
import com.konkukrent.demo.repository.ReservationRepository;
import com.konkukrent.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // TODO: user, product Null 예외 처리 필요
    @Transactional
    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto) {
        User user = userRepository.getUserById(reservationRequestDto.getUserId());
        Product product = productRepository.getProductById(reservationRequestDto.getProductId());

        Reservation reservation = new Reservation(user, product);
        Reservation saved = reservationRepository.save(reservation);
        return entityToDto(saved);
    }

    // TODO: reservation이 존재하지 않을 경우 예외 처리 필요
    @Transactional
    public void deleteReservation(Long reservationId) {
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
