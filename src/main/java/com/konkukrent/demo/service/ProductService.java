package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.ProductDto.ProductCreateRequestDto;
import com.konkukrent.demo.dto.ProductDto.ProductResponseDto;
import com.konkukrent.demo.dto.ProductDto.ProductUpdateRequestDto;
import com.konkukrent.demo.entity.Product;
import com.konkukrent.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 물품 조회
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    // 물품 생성
    @Transactional
    public ProductResponseDto createProduct(ProductCreateRequestDto request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setTotalNumber(request.getTotalNumber());
        product.setRemainNumber(request.getTotalNumber());
        product.setRentalPeriod(request.getRentalPeriod());
        Product savedProduct = productRepository.save(product);
        return mapToResponseDto(savedProduct);
    }

    // 물품 수정
    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductUpdateRequestDto request) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Product product = optionalProduct.get();
        product.setRemainNumber(request.getRemainNumber());
        Product updatedProduct = productRepository.save(product);
        return mapToResponseDto(updatedProduct);
    }

    // 물품 삭제
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // Entity to DTO
    private ProductResponseDto mapToResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getTotalNumber(),
                product.getRemainNumber(),
                product.getRentalPeriod()
        );
    }
}
