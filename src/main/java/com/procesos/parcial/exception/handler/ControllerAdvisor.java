package com.procesos.parcial.exception.handler;

import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.exception.ProductAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.procesos.parcial.util.Constants;

import java.util.Collections;
import java.util.Map;

/**
 * Class to handle exceptions.
 */
@ControllerAdvice
public class ControllerAdvisor {

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
     * Method to throw exception if there is an illegal argument.
     * @param ex Instance ProductAlreadyExistsException class.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> productAlreadyExistsException(ProductAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, Constants.PRODUCT_ALREADY_EXISTS_MESSAGE));
    }

}
