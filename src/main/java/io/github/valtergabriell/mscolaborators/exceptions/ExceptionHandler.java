package io.github.valtergabriell.mscolaborators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    public ResponseEntity<ApiResponseExceptions> response(RequestException e) {
        ApiResponseExceptions apiResponseExceptions = new ApiResponseExceptions(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiResponseExceptions, HttpStatus.BAD_REQUEST);
    }

}
