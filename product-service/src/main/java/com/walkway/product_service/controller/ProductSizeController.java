package com.walkway.product_service.controller;

import com.walkway.product_service.dto.product_size.ProductSizeCreateDto;
import com.walkway.product_service.dto.product_size.ProductSizeDto;
import com.walkway.product_service.dto.product_size.ProductSizeUpdateDto;
import com.walkway.product_service.exception.product_size.ProductSizeAlreadyExistsException;
import com.walkway.product_service.exception.product_size.ProductSizeNotFoundException;
import com.walkway.product_service.service.ProductSizeService;
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
        name ="Product Size Read Update Delete Operations",
        description = "Product Size Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductSizeController {

    private final ProductSizeService productSizeService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Product Size",
            description = "Admin can Create the Size associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PostMapping("/size")
    public ResponseEntity<ProductSizeDto> createSize(
            @RequestBody @Valid ProductSizeCreateDto productSizerCreateDto
    ) throws ProductSizeAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productSizeService.createProductSize(productSizerCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Product Size",
            description = "Users can Read all the Sizes associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/size")
    public ResponseEntity<List<ProductSizeDto>> readSize(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productSizeService.getProductSize());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product Size",
            description = "Users can Read the Size by id, associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/size/{productSizeId}")
    public ResponseEntity<ProductSizeDto> readSize(
            @PathVariable Integer productSizeId
    ) throws ProductSizeNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productSizeService.getProductSize(productSizeId.byteValue()));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Product Size",
            description = "Admin can Update the Size associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PutMapping("/size/{productSizeId}")
    public ResponseEntity<ProductSizeDto> updateSize(
            @PathVariable Integer productSizeId,
            @Valid @RequestBody ProductSizeUpdateDto productSizeUpdateDto
    ) throws ProductSizeNotFoundException, ProductSizeAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productSizeService.updateProductSize(productSizeUpdateDto, productSizeId.byteValue()));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Product Size",
            description = "Admin can Delete the Size associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @DeleteMapping("/size/{productSizeId}")
    public ResponseEntity<Void> deleteSize(
            @PathVariable Integer productSizeId
    ) throws ProductSizeNotFoundException {
        productSizeService.deleteProductSize(productSizeId.byteValue());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}