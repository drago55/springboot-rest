package com.server.api.controllers;

import com.server.api.exception.ClientException;
import com.server.api.exception.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentials(final BadCredentialsException e, final WebRequest request){
        log.error("Bad credentials exception ", e);
        final ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, "Invalid user name or password !",
                request.getDescription(false));
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler({ClientException.class})
    public ResponseEntity<ApiError> handleAllClientException(final ClientException e, final WebRequest request) {
        log.error("Client exception ", e);
        final ApiError apiError = e.getApiError();
            apiError.setPath(request.getDescription(false));
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,final HttpHeaders headers,
            final HttpStatus status,final WebRequest request) {
        logger.error("Argument not valid exception", ex);

        final Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());
        body.put("path", request.getDescription(false));
        final Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        body.put("errors", errors);
        logger.error("Errors " + errors);

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleAllExceptions(final Exception ex,final WebRequest request) {
        logger.error("Exception ", ex);
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}