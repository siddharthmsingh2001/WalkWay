package com.walkway.product_service.controller;

import com.walkway.product_service.dto.product_colour.ProductColourCreateDto;
import com.walkway.product_service.dto.product_colour.ProductColourDto;
import com.walkway.product_service.dto.product_colour.ProductColourUpdateDto;
import com.walkway.product_service.exception.product_colour.ProductColourAlreadyExistsException;
import com.walkway.product_service.exception.product_colour.ProductColourNotFoundException;
import com.walkway.product_service.service.ProductColourService;
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
        name ="Product Colour Create Read Update Delete Operations",
        description = "Product Colour Create Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductColourController {

    private final ProductColourService productColourService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Create Product Colour",
            description = "Admin can Create the Colour associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PostMapping("/colour")
    public ResponseEntity<ProductColourDto> createColour(
            @RequestBody @Valid ProductColourCreateDto productColourCreateDto
    ) throws ProductColourAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productColourService.createProductColour(productColourCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Product Colour",
            description = "Users can Read all the Colours associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/colour")
    public ResponseEntity<List<ProductColourDto>> readColour(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productColourService.getProductColour());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product Colour",
            description = "Users can Read the Colour by id, associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/colour/{productColourId}")
    public ResponseEntity<ProductColourDto> readColour(
            @PathVariable Integer productColourId
    ) throws ProductColourNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productColourService.getProductColour(productColourId.byteValue()));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Product Colour",
            description = "Admin can Update the Colour associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PutMapping("/colour/{productColourId}")
    public ResponseEntity<ProductColourDto> updateColour(
            @PathVariable Integer productColourId,
            @RequestBody @Valid ProductColourUpdateDto productColourUpdateDto
            ) throws ProductColourNotFoundException, ProductColourAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productColourService.updateProductColour(productColourUpdateDto, productColourId.byteValue()));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "HTTP Status NO CONTENT"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Delete Product Colour",
            description = "Admin can Delete the Colour associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @DeleteMapping("/colour/{productColourId}")
    public ResponseEntity<Void> deleteColour(
            @PathVariable Integer productColourId
    ) throws ProductColourNotFoundException {
        productColourService.deleteProductColour(productColourId.byteValue());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
