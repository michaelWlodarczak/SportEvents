package sportEvents.controller;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sportEvents.service.exception.BusinessServiceException;
import sportEvents.service.exception.EventException;
import sportEvents.service.exception.UserNotExistException;

import java.time.Instant;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessServiceException.class)
    ResponseEntity<RestControllerExceptionHandler.ErrorMessage> handleException(BusinessServiceException ex) {
        return ResponseEntity.badRequest() // -> status code = 400
                .body(new RestControllerExceptionHandler.ErrorMessage(ex.getMessage(), Instant.now())); // JSON -> { message: "....", time: "..." }
    }

    @ExceptionHandler(UserNotExistException.class)
    ResponseEntity<RestControllerExceptionHandler.ErrorMessage> handleException(UserNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND) // -> status code = 404
                .body(new RestControllerExceptionHandler.ErrorMessage(ex.getMessage(), Instant.now()));
    }

    @ExceptionHandler(EventException.class)
    ResponseEntity<RestControllerExceptionHandler.ErrorMessage> handleException(EventException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND) // -> status code = 404
                .body(new RestControllerExceptionHandler.ErrorMessage(ex.getMessage(), Instant.now()));
    }

    @Value
    private static class ErrorMessage {
        String message;
        Instant time;
    }

}
