package com.aymer.sirketimceptebackend.config;

import com.aymer.sirketimceptebackend.controller.common.dto.AppResponse;
import com.aymer.sirketimceptebackend.exception.ServiceException;
import com.aymer.sirketimceptebackend.utils.LocaleAwareMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

/**
 * User: ealtun
 * Date: 21.03.2020
 * Time: 13:03
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private LocaleAwareMessageProvider messageProvider;

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<Object> handleBusinessExceptions(ServiceException ex, WebRequest request) {
        String errorMessage = this.messageProvider.getMessage(ex.getMsgKey(), ex.getMsgArgs());
        logger.debug(errorMessage, ex);
        return handleExceptionInternal(ex, new AppResponse(HttpStatus.BAD_REQUEST.value(), errorMessage), new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex, WebRequest request) {
        return logMessages(ex, request, "error.java.lang.Throwable");
    }

    private ResponseEntity<Object> logMessages(Exception ex, WebRequest request, String messageKey) {
        String errorKey = "FK-" + UUID.randomUUID().toString();
        String errorMessage = messageProvider.getMessage(messageKey);
        logger.error(errorKey, ex);
        return handleExceptionInternal(ex, new AppResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage + " (" + errorKey + ")"), new HttpHeaders(), HttpStatus.OK, request);
    }
}
