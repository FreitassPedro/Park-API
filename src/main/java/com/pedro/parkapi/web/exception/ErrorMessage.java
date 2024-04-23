package com.pedro.parkapi.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErrorMessage {
    private String path;
    private String method;
    private int status;
    private String statusText;
    private String message;
    //JSONINCLUDE NON_NON Inclui apenas se NÃO for nulo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> erros;

    public ErrorMessage () {}
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErros(result);
    }

    private void addErros(BindingResult result) {
        this.erros = new HashMap<>();
        for(FieldError fieldError : result.getFieldErrors()) {
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
