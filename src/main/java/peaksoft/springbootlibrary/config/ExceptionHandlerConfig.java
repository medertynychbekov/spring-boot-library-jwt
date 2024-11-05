package peaksoft.springbootlibrary.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.springbootlibrary.exception.MyCustomException;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
