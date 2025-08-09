package com.walkway.product_service.controller;

import com.walkway.product_service.dto.product.ProductCreateDto;
import com.walkway.product_service.dto.product.ProductDto;
import com.walkway.product_service.dto.product.ProductUpdateDto;
import com.walkway.product_service.exception.product.ProductAlreadyExistsException;
import com.walkway.product_service.exception.product.ProductNotFoundException;
import com.walkway.product_service.exception.product_category.ProductCategoryNotFoundException;
import com.walkway.product_service.exception.product_gender.ProductGenderNotFoundException;
import com.walkway.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name ="Product Read Update Delete Operations",
        description = "Product Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Product",
            description = "Users can Create the Product",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody @Valid ProductCreateDto productCreateDto
    ) throws ProductCategoryNotFoundException, ProductAlreadyExistsException, ProductGenderNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Product",
            description = "Users can Read all the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> readProduct(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProduct());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product",
            description = "Users can Read the Product by id",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> readProduct(
            @PathVariable Integer productId
    ) throws ProductNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProduct(productId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Product",
            description = "Users can Update the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Integer productId,
            @Valid @RequestBody ProductUpdateDto productUpdateDto
    ) throws ProductCategoryNotFoundException, ProductAlreadyExistsException, ProductGenderNotFoundException, ProductNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProduct(productUpdateDto,productId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Product",
            description = "Users can Delete the Product",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer productId
    ) throws ProductNotFoundException {
        productService.deleteProduct(productId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}