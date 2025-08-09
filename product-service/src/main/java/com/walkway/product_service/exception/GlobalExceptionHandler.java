package com.walkway.product_service.exception;


import com.walkway.product_service.dto.ErrorResponseDto;
import com.walkway.product_service.exception.product.ProductAlreadyExistsException;
import com.walkway.product_service.exception.product.ProductNotFoundException;
import com.walkway.product_service.exception.product_category.ProductCategoryAlreadyExistsException;
import com.walkway.product_service.exception.product_category.ProductCategoryNotFoundException;
import com.walkway.product_service.exception.product_colour.ProductColourAlreadyExistsException;
import com.walkway.product_service.exception.product_colour.ProductColourNotFoundException;
import com.walkway.product_service.exception.product_gender.ProductGenderAlreadyExistsException;
import com.walkway.product_service.exception.product_gender.ProductGenderNotFoundException;
import com.walkway.product_service.exception.product_item.ProductItemAlreadyExistsException;
import com.walkway.product_service.exception.product_item.ProductItemNotFoundException;
import com.walkway.product_service.exception.product_size.ProductSizeAlreadyExistsException;
import com.walkway.product_service.exception.product_size.ProductSizeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductGenderAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateProductGender(ProductGenderAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_GENDER_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductGenderNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentProductGender(ProductGenderNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_GENDER_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductColourAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateProductColour(ProductColourAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_COLOUR_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductColourNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentProductColour(ProductColourNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_COLOUR_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductSizeAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateProductSize(ProductSizeAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_SIZE_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductSizeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentProductSize(ProductSizeNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_SIZE_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductCategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateProductCategory(ProductCategoryAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_CATEGORY_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentProductCategory(ProductCategoryNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_CATEGORY_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateProduct(ProductAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentProduct(ProductNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_MISSING_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductItemAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateProduct(ProductItemAlreadyExistsException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_ITEM_EXISTS_EXCEPTION,
                                cause.getMessage()
                        )
                );
    }

    @ExceptionHandler(ProductItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAbsentProduct(ProductItemNotFoundException cause, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                request.getDescription(false),
                                ResponseStatus.PRODUCT_ITEM_MISSING_EXCEPTION,
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
