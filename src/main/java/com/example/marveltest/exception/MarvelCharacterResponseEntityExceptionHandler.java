package com.example.marveltest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception Handler - map exceptions to HTTP code
 */
@ControllerAdvice
@RestController
public class MarvelCharacterResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler for Character Not Found Exception
     *
     * @param mcnfe
     * @return 404 with error message
     */
    @ExceptionHandler(MarvelCharacterNotFoundException.class)
    public final ResponseEntity<String> handleMarvelCharacterNotFoundException(MarvelCharacterNotFoundException mcnfe) {
        return new ResponseEntity<String>(mcnfe.getMessage(), HttpStatus.NOT_FOUND);
    }
}
