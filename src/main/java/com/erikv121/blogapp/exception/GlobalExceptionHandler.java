package com.erikv121.blogapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ModelAndView mav = new ModelAndView("error/404"); // templates/error/404.html
        mav.addObject("message", ex.getMessage());
        mav.addObject("timestamp", LocalDateTime.now());
        mav.addObject("path", request.getDescription(false));
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, WebRequest request) {
        ModelAndView mav = new ModelAndView("error/500"); // templates/error/500.html
        mav.addObject("message", ex.getMessage());
        mav.addObject("timestamp", LocalDateTime.now());
        mav.addObject("path", request.getDescription(false));
        return mav;
    }
}
