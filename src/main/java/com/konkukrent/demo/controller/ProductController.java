package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.ProductDto.ProductCreateRequestDto;
import com.konkukrent.demo.dto.ProductDto.ProductResponseDto;
import com.konkukrent.demo.dto.ProductDto.ProductUpdateRequestDto;
import com.konkukrent.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductCreateRequestDto request) {
        return ResponseEntity.status(201).body(productService.createProduct(request));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductUpdateRequestDto remainNumber) {
        return ResponseEntity.ok(productService.updateProduct(productId, remainNumber));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
