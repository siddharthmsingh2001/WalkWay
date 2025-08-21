package com.walkway.product_service.controller;

import com.walkway.product_service.dto.product_gender.ProductGenderCreateDto;
import com.walkway.product_service.dto.product_gender.ProductGenderDto;
import com.walkway.product_service.dto.product_gender.ProductGenderUpdateDto;
import com.walkway.product_service.exception.product_gender.ProductGenderAlreadyExistsException;
import com.walkway.product_service.exception.product_gender.ProductGenderNotFoundException;
import com.walkway.product_service.service.ProductGenderService;
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
        name ="Product Gender Read Update Delete Operations",
        description = "Product Gender Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductGenderController {

    private final ProductGenderService productGenderService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Product Gender",
            description = "Admin can Create the Gender associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PostMapping("/gender")
    public ResponseEntity<ProductGenderDto> createGender(
            @RequestBody @Valid ProductGenderCreateDto productGenderDto
    ) throws ProductGenderAlreadyExistsException {
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(productGenderService.createProductGender(productGenderDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Product Gender",
            description = "Users can Read all the Genders associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/gender")
    public ResponseEntity<List<ProductGenderDto>> readGender(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productGenderService.getProductGender());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product Gender",
            description = "Users can Read the Gender by id, associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/gender/{productGenderId}")
    public ResponseEntity<ProductGenderDto> readGender(
            @PathVariable Integer productGenderId
    ) throws ProductGenderNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productGenderService.getProductGender(productGenderId.byteValue()));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Product Gender",
            description = "Admin can Update the Gender associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PutMapping("/gender/{productGenderId}")
    public ResponseEntity<ProductGenderDto> updateGender(
            @PathVariable Integer productGenderId,
            @Valid @RequestBody ProductGenderUpdateDto productGenderDto
    ) throws ProductGenderNotFoundException, ProductGenderAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productGenderService.updateProductGender(productGenderDto, productGenderId.byteValue()));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Product Gender",
            description = "Admin can Delete the Gender associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @DeleteMapping("/gender/{productGenderId}")
    public ResponseEntity<Void> deleteGender(
            @PathVariable Integer productGenderId
    ) throws ProductGenderNotFoundException {
        productGenderService.deleteProductGender(productGenderId.byteValue());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}