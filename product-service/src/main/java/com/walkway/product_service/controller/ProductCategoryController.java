package com.walkway.product_service.controller;

import com.walkway.product_service.dto.product_category.ProductCategoryCreateDto;
import com.walkway.product_service.dto.product_category.ProductCategoryDto;
import com.walkway.product_service.dto.product_category.ProductCategoryUpdateDto;
import com.walkway.product_service.exception.product_category.ProductCategoryAlreadyExistsException;
import com.walkway.product_service.exception.product_category.ProductCategoryNotFoundException;
import com.walkway.product_service.service.ProductCategoryService;
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
        name ="Product Category Read Update Delete Operations",
        description = "Product Category Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Product Category",
            description = "Admin can Create the Category associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PostMapping("/category")
    public ResponseEntity<ProductCategoryDto> createCategory(
            @RequestBody @Valid ProductCategoryCreateDto productCategoryCreateDto
    ) throws ProductCategoryNotFoundException, ProductCategoryAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCategoryService.createProductCategory(productCategoryCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Product Category",
            description = "Users can Read all the Category associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/category")
    public ResponseEntity<List<ProductCategoryDto>> readCategory(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.getProductCategory());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product Category",
            description = "Users can Read the Category by id, associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/category/{productCategoryId}")
    public ResponseEntity<ProductCategoryDto> readCategory(
            @PathVariable Integer productCategoryId
    ) throws ProductCategoryNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.getProductCategory(productCategoryId.byteValue()));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Product Category",
            description = "Admin can Update the Category associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PutMapping("/category/{productCategoryId}")
    public ResponseEntity<ProductCategoryDto> updateCategory(
            @PathVariable Integer productCategoryId,
            @Valid @RequestBody ProductCategoryUpdateDto productCategoryUpdateDto
    ) throws ProductCategoryNotFoundException, ProductCategoryAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.updateProductCategory(productCategoryId.byteValue(), productCategoryUpdateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Product Category",
            description = "Admin can Delete the Category associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @DeleteMapping("/category/{productCategoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Integer productCategoryId
    ) throws ProductCategoryNotFoundException {
        productCategoryService.deleteProductCategory(productCategoryId.byteValue());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}