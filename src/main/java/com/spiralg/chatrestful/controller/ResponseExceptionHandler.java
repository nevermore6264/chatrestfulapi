package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.common.exception.TcpChatException;
import com.spiralg.chatrestful.model.api.view.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({TcpChatException.class})
    protected ResponseEntity<ApiError> handleTcpChatException(TcpChatException ex, WebRequest request) {
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity<ApiError> handleException(RuntimeException ex, WebRequest request) {
//        ApiError error = new ApiError();
//        error.setMessage("Unknow error");
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }
}
