package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.RentalDto.RentalRequestDto;
import com.konkukrent.demo.dto.RentalDto.RentalResponseDto;
import com.konkukrent.demo.dto.RentalDto.UserRentalResponseDto;
import com.konkukrent.demo.entity.Rental;
import com.konkukrent.demo.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// todo : expiration date 뒷부분 짜르는 형식 (?)
@Service
public class RentalService {

    // todo : productRepository, userRepository 가져오기
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    // 모든 대여 내역 조회
    @Transactional(readOnly = true)
    public List<RentalResponseDto> getAllRentals(){
        return rentalRepository.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    // 사용자 별 대여 내역 조회
    @Transactional(readOnly = true)
    public List<UserRentalResponseDto> getRentalsByUserId(Long userId){
        return rentalRepository.findByUserId(userId).stream().map(this::mapToUserRentalResponseDTO).collect(Collectors.toList());
    }

    // 물품 대여
    @Transactional
    public RentalResponseDto createRental(RentalRequestDto request){

        // todo : product, user repository 에서 id로 객체 조회
        // Product product;
        // User user;

        Rental rental = new Rental();
        // rental.setProduct(product);
        // rental.setUser(user);
        rental.setRentalTime(LocalDateTime.now());

        // todo : LocalDateTime.now() + product 의 rentalPeriod
        // rental.setExpirationDate(LocalDateTime.now().plusSeconds());
        Rental savedRental = rentalRepository.save(rental);
        return mapToResponseDTO(savedRental);
    }

    // 물품 반납
    @Transactional
    public void deleteRental(Long rentalId){
        rentalRepository.deleteById(rentalId);
    }

    // getAllRentals 함수 내에서 객체 매핑
    private RentalResponseDto mapToResponseDTO(Rental rental) {
        RentalResponseDto dto = new RentalResponseDto();
        dto.setRentalId(rental.getRentalId());

        // todo : product, user 연결
        // dto.setProductId(rental.getProduct().getId());
        // dto.setProductName(rental.getProduct().getName());
        // dto.setUserId(rental.getUser().getId());
        // dto.setUserName(rental.getUser().getName());
        // dto.setStudentNum(rental.getUser().getStudentNum());

        dto.setRentalTime(rental.getRentalTime());
        dto.setExpirationDate(rental.getExpirationDate());

        return dto;
    }

    // getRentalsByUserId 함수 내에서 객체 매핑
    private UserRentalResponseDto mapToUserRentalResponseDTO(Rental rental) {
        UserRentalResponseDto dto = new UserRentalResponseDto();
        dto.setRentalId(rental.getRentalId());

        // todo : product, user 연결
        // dto.setProductId(rental.getProduct().getId());
        // dto.setUserId(rental.getUser().getId());

        dto.setRentalTime(rental.getRentalTime());
        dto.setExpirationDate(rental.getExpirationDate());

        return dto;
    }
}
