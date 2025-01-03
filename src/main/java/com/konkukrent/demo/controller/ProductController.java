package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.ProductDto.ProductCreateRequestDto;
import com.konkukrent.demo.dto.ProductDto.ProductResponseDto;
import com.konkukrent.demo.dto.ProductDto.ProductUpdateRequestDto;
import com.konkukrent.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "물품 조회",
            description = "모든 물품을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "모든 물품을 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(
            summary = "물품 생성",
            description = "새로운 물품을 추가합니다."
    )
    @ApiResponse(
            responseCode = "201",
            description = "물품이 추가되었습니다."
    )
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductCreateRequestDto request) {
        return ResponseEntity.status(201).body(productService.createProduct(request));
    }

    @Operation(
            summary = "물품 수정",
            description = "물품 세부사항을 수정합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "물품 세부사항이 수정되었습니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "물품 세부사항이 수정되었습니다."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "해당 물품이 존재하지 않습니다."
            )
    })
    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductUpdateRequestDto remainNumber) {
        return ResponseEntity.ok(productService.updateProduct(productId, remainNumber));
    }

    @Operation(
            summary = "물품 삭제",
            description = "물품을 삭제합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "물품이 삭제되었습니다."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "해당 물품이 존재하지 않습니다."
            )
    })
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
