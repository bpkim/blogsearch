package com.blogsearch.api.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(HttpServletRequest request, ChangeSetPersister.NotFoundException exception) {
        String message = getMessage(request, exception, "NotFoundException");

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .errorType("notFound")
                .message(message).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleIOException(HttpServletRequest request, IOException exception) {
        String message = getMessage(request, exception, "IOException");

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .errorType("InternalServerIOError")
                .message(message).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleIllegalAccessException(HttpServletRequest request, IllegalAccessException exception) {
        String message = getMessage(request, exception, "IllegalAccessException");

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .errorType("IllegalError")
                .message(message).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        String message = getMessage(request, exception, "ArgumentException");

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .errorType("ArgumentError")
                .message(message).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(HttpServletRequest request, NullPointerException exception) {
        String message = getMessage(request, exception, "NullPointerException");
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .errorType("InternalServerError")
                .message(message).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);

    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleException(HttpServletRequest request, Exception exception) {
        String message = getMessage(request, exception, "InternalException");

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .errorType("InternalServerError")
                .message(message).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);

    }

    private String getMessage(HttpServletRequest request, Exception exception, String defaultMsg) {
        String LOG_MESSAGE_FORMAT = "EXCEPTION : method {}, message {}";
        String message = exception.getMessage();
        log.error(LOG_MESSAGE_FORMAT, request.getMethod(), message);

        if(StringUtils.isEmpty(message)){
            message = defaultMsg;
        }
        return message;
    }
}
