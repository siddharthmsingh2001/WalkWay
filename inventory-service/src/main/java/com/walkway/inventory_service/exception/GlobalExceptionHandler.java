package com.walkway.inventory_service.exception;

import com.walkway.inventory_service.dto.ErrorResponseDto;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_location.InventoryLocationNotFoundException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_stock.InventoryStockNotFoundException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_warehouse.InventoryWarehouseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventoryLocationAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateInventoryLocation(InventoryLocationAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_LOCATION_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(InventoryLocationNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentInventoryLocation(InventoryLocationNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_LOCATION_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(InventoryProductSnapshotAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateInventoryProductSnapshot(InventoryProductSnapshotAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_PRODUCT_SNAPSHOT_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(InventoryProductSnapshotNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentInventoryProductSnapshot(InventoryProductSnapshotNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_PRODUCT_SNAPSHOT_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(InventoryStockAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateInventoryStock(InventoryStockAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_STOCK_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(InventoryStockNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentInventoryStock(InventoryStockNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_STOCK_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(InventoryWarehouseAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateInventoryWarehouse(InventoryWarehouseAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_WAREHOUSE_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(InventoryWarehouseNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentInventoryWarehouse(InventoryWarehouseNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.INVENTORY_WAREHOUSE_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException cause, WebRequest request) {
        StringBuilder sb = new StringBuilder();
        cause.getBindingResult().getFieldErrors().forEach(error ->
                sb.append("[").append(error.getField()).append(": ").append(error.getDefaultMessage()).append("] ")
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.VALIDATION_EXCEPTION,
                                sb.toString().trim()
                        )
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonParseError(HttpMessageNotReadableException cause, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.JSON_PARSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

}
