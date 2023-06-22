package com.procesos.parcial.exception.handler;

import com.procesos.parcial.exception.IncorrectPasswordException;
import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.exception.ProductAlreadyExistsException;
import com.procesos.parcial.exception.ProductNotBelongUserException;
import com.procesos.parcial.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.procesos.parcial.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class to handle exceptions.
 */
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError fieldError) {
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(Constants.MESSAGE_ERROR_KEY, Constants.WRONG_CREDENTIALS_MESSAGE));
    }
    /**
     * Method to throw exception if no data found.
     * @param ex An instance of  the NoDataFoundException class that extends RuntimeException.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, Constants.PRODUCT_NOT_FOUND_MESSAGE));
    }

    /**
     * Method to throw exception if there is an illegal argument.
     * @param ex Instance IllegalArgumentException class.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> illegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, Constants.ILLEGAL_ARGUMENT_MESSAGE));
    }
    /**
     * Method to throw exception if there is a product already exists
     * @param ex Instance ProductAlreadyExistsException class.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> productAlreadyExistsException(ProductAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, Constants.PRODUCT_ALREADY_EXISTS_MESSAGE));
    }
    /**
     * Method to throw exception if the product doesn't belong to the user
     * @param ex Instance ProductNotBelongUserException class.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(ProductNotBelongUserException.class)
    public ResponseEntity<Map<String, String>> productNotBelongUserException(ProductNotBelongUserException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, Constants.PRODUCT_NOT_BELONG_USER_MESSAGE));
    }
    /**
     * Method to throw exception if no user found
     * @param ex Instance UserNotFoundException class.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> userNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, Constants.USER_NOT_FOUND_MESSAGE));
    }

    /**
     * Method to throw exception if incorrect password.
     * @param ex An instance of  the IncorrectPasswordException class that extends RuntimeException.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(Constants.MESSAGE_ERROR_KEY, Constants.INCORRECT_PASSWORD_MESSAGE));
    }

}
