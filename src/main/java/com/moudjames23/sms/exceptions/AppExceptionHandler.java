package com.moudjames23.sms.exceptions;

import com.moudjames23.sms.dto.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.moudjames23.sms.dto.HttpResponse.generateResponse;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public HttpEntity<Object> resourceNotFoundHandler(ResourceNotFoundException exception)
    {
        return generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public HttpEntity<Object> resourceAlreadyExistHandler(ResourceAlreadyExistException exception)
    {
        return generateResponse(exception.getMessage(), HttpStatus.CONFLICT, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity<Object> invalidArgumentException(MethodArgumentNotValidException exception)
    {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return generateResponse("Fields are required", HttpStatus.BAD_REQUEST, errors);
    }
}
