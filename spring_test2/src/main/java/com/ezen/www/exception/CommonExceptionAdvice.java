package com.ezen.www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

	//http에 대한 상태가 NOT_FOUND 상태
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handler404(NoHandlerFoundException ex) {
		
		log.info(">>>Exception>>>{}",ex.getMessage());
		
		return "custom404";
		
	}
	
//	@ExceptionHandler(Exception.class)
//	public String exception(Exception ex) {
//		
//		log.info(">>>>exception >>{}",ex.getMessage());
//	
//		return "error_page";
//	}
	
}
