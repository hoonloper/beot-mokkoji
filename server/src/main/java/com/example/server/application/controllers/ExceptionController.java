package com.example.server.application.controllers;

import com.example.server.application.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    /* Validator Exception */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionError handleValidException(final MethodArgumentNotValidException ex) {
        log.warn("Validator Exception: " + ex.getMessage());
        return new ExceptionError(ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }
    /* 400 - Bad Request */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionError handleBadRequest(final RuntimeException ex) {
        log.warn("Bad Request: ", ex);
        return new ExceptionError(ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }

    /* 401 - Unauthorized */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionError handleUnauthorized(final RuntimeException ex) {
        log.warn("Unauthorized: ", ex);
        return new ExceptionError(ex.getMessage(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED.value());
    }

    /* 403 - Forbidden */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionError handleForbidden(final RuntimeException ex) {
        log.warn("Forbidden: ", ex);
        return new ExceptionError(ex.getMessage(), HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN.value());
    }

    /* 404 - Not Found */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionError handleNotFound(final RuntimeException ex) {
        log.warn("Not Found: ", ex);
        return new ExceptionError(ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }


    /* 500 - Internal Server Error(and ALL) */
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionError handleServerError(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("Server Error: ", ex);
        return new ExceptionError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /* 알 수 없는 에러 */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionError handleUnknownException(final RuntimeException ex) {
        log.warn("알 수 없음, ", ex);
        return new ExceptionError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
