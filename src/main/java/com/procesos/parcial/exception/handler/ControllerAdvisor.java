package com.procesos.parcial.exception.handler;

import com.procesos.parcial.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Method to throw exception if no data found.
     * @param ex An instance of  the NoDataFoundException class that extends RuntimeException.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, "Product not found"));
    }

    /**
     * Method to throw exception if there is an illegal argument.
     * @param ex Instance IllegalArgumentException class.
     * @return ResponseEntity with the status and message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> illegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, "Entity must not be null"));
    }

}
