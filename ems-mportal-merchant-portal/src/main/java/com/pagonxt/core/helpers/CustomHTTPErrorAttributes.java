package com.pagonxt.core.helpers;

import com.pagonxt.core.models.ErrorModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CustomHTTPErrorAttributes extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        var errorModel = Objects.isNull(body)
                ? new ErrorModel(status, exception.getLocalizedMessage(), exception.getClass().toString())
                : body;
        return super.handleExceptionInternal(exception, errorModel, headers, status, request);
      }
}

