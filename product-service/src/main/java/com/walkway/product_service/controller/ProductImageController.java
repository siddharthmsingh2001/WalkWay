package com.walkway.product_service.controller;

import com.walkway.product_service.dto.product_image.ProductImageCreateDto;
import com.walkway.product_service.dto.product_image.ProductImageDto;
import com.walkway.product_service.exception.product_image.ProductImageNotFoundException;
import com.walkway.product_service.exception.product_item.ProductItemNotFoundException;
import com.walkway.product_service.service.ProductImageService;
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
        name ="Product Image Read Update Delete Operations",
        description = "Product Image Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductImageController {

    private final ProductImageService productImageService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Create Product Image",
            description = "User can Create the Image associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PostMapping("/image")
    public ResponseEntity<ProductImageDto> createImage(
            @RequestBody @Valid ProductImageCreateDto productImageCreateDto
    ) throws ProductItemNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productImageService.createProductImage(productImageCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Product Image",
            description = "Users can Read all the Image associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/image")
    public ResponseEntity<List<ProductImageDto>> readImage(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productImageService.getProductImage());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product Image",
            description = "Users can Read the Image by id, associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/image/{productImageId}")
    public ResponseEntity<ProductImageDto> readImage(
            @PathVariable Integer productImageId
    ) throws ProductImageNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productImageService.getProductImage(productImageId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Product Image",
            description = "Admin can Delete the Image associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @DeleteMapping("/image/{productImageId}")
    public ResponseEntity<Void> deleteGender(
            @PathVariable Integer productImageId
    ) throws ProductImageNotFoundException {
        productImageService.deleteProductImage(productImageId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}