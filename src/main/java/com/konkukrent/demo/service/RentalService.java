package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.RentalDto.RentalRequestDto;
import com.konkukrent.demo.dto.RentalDto.RentalResponseDto;
import com.konkukrent.demo.dto.RentalDto.UserRentalResponseDto;
import com.konkukrent.demo.entity.Product;
import com.konkukrent.demo.entity.Rental;
import com.konkukrent.demo.entity.User;
import com.konkukrent.demo.repository.ProductRepository;
import com.konkukrent.demo.repository.RentalRepository;
import com.konkukrent.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
    // todo 예외 처리 remainNumber <= 0
    // todo 같은 사용자의 중복 물품 대여
    @Transactional
    public RentalResponseDto createRental(RentalRequestDto request){

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + request.getProductId()));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        Rental rental = new Rental();
        rental.setProduct(product);
        rental.setUser(user);
        rental.setRentalTime(LocalDateTime.now());
        rental.setExpirationDate(LocalDate.now().plusDays(product.getRentalPeriod()));

        product.setRemainNumber(product.getRemainNumber() - 1);
        Rental savedRental = rentalRepository.save(rental);

        return mapToResponseDTO(savedRental);
    }

    // 물품 반납
    @Transactional
    public void deleteRental(Long rentalId){
        Product product = rentalRepository.getReferenceById(rentalId).getProduct();
        product.setRemainNumber(product.getRemainNumber() + 1);
        rentalRepository.deleteById(rentalId);
    }

    // getAllRentals 함수 내에서 객체 매핑
    private RentalResponseDto mapToResponseDTO(Rental rental) {

        Product product = rental.getProduct();
        User user = rental.getUser();

        RentalResponseDto dto = new RentalResponseDto();

        dto.setRentalId(rental.getRentalId());
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setStudentNum(user.getStudentNum());
        dto.setRentalTime(rental.getRentalTime());
        dto.setExpirationDate(rental.getExpirationDate());

        return dto;
    }

    // getRentalsByUserId 함수 내에서 객체 매핑
    private UserRentalResponseDto mapToUserRentalResponseDTO(Rental rental) {

        Product product = rental.getProduct();

        UserRentalResponseDto dto = new UserRentalResponseDto();

        dto.setRentalId(rental.getRentalId());
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setRentalTime(rental.getRentalTime());
        dto.setExpirationDate(rental.getExpirationDate());

        return dto;
    }
}
