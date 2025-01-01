package com.konkukrent.demo.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Column(nullable = false)
    private LocalDateTime expirationTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private static final int EXPIRATION_MINS = 10;

    public Reservation(User user, Product product) {
        this.user = user;
        this.product = product;
        this.reservationTime = LocalDateTime.now();
        this.expirationTime = reservationTime.plusMinutes(EXPIRATION_MINS);
    }
}
