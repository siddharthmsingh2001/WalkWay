package com.walkway.product_service.controller;

import com.walkway.product_service.dto.product_item.ProductItemCreateDto;
import com.walkway.product_service.dto.product_item.ProductItemDto;
import com.walkway.product_service.dto.product_item.ProductItemUpdateDto;
import com.walkway.product_service.exception.product.ProductNotFoundException;
import com.walkway.product_service.exception.product_colour.ProductColourNotFoundException;
import com.walkway.product_service.exception.product_item.ProductItemAlreadyExistsException;
import com.walkway.product_service.exception.product_item.ProductItemNotFoundException;
import com.walkway.product_service.exception.product_size.ProductSizeNotFoundException;
import com.walkway.product_service.service.ProductItemService;
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
        name ="Product Item Read Update Delete Operations",
        description = "Product Item Read Update and Delete Operations can be performed here"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductItemController {

    private final ProductItemService productItemService;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Create Product Item",
            description = "Admin can Create the Item associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PostMapping("/product-item")
    public ResponseEntity<ProductItemDto> createItem(
            @RequestBody @Valid ProductItemCreateDto productItemCreateDto
    ) throws ProductColourNotFoundException, ProductSizeNotFoundException, ProductNotFoundException, ProductItemAlreadyExistsException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productItemService.createProductItem(productItemCreateDto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
            }
    )
    @Operation(
            summary = "Read Product Item",
            description = "Users can Read all the Items associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/product-item")
    public ResponseEntity<List<ProductItemDto>> readItem(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productItemService.getProductItem());
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product Item",
            description = "Users can Read the Item by id, associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/product-item/{productItemId}")
    public ResponseEntity<ProductItemDto> readItem(
            @PathVariable Integer productItemId
    ) throws ProductItemNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productItemService.getProductItem(productItemId));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND")
            }
    )
    @Operation(
            summary = "Read Product Item",
            description = "Users can Read the Item by Product Item Code, associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @GetMapping("/product-item/{productItemCode}")
    public ResponseEntity<ProductItemDto> readItemByCode(
            @PathVariable Integer productItemCode
    ) throws ProductItemNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productItemService.getProductItemByCode(productItemCode));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND"),
                    @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT")
            }
    )
    @Operation(
            summary = "Update Product Item",
            description = "Admin can Update the Item associated to the Products",
            security = @SecurityRequirement(name = "product-service-pkce"))
    @PutMapping("/product-item/{productItemId}")
    public ResponseEntity<ProductItemDto> updateItem(
            @PathVariable Integer productItemId,
            @Valid @RequestBody ProductItemUpdateDto productItemUpdateDto
    ) throws ProductColourNotFoundException, ProductSizeNotFoundException, ProductItemNotFoundException, ProductNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productItemService.updateProductItem(productItemUpdateDto, productItemId));
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
    @DeleteMapping("/product-item/{productItemId}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Integer productItemId
    ) throws ProductItemNotFoundException {
        productItemService.deleteProductItem(productItemId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}