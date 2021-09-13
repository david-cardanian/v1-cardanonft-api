package com.cardanonft.api.exception;

import com.cardanonft.api.response.DefaultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// MissingServletRequestParameterException handling code goes here.
		Object response = new DefaultResponse(e);
		logger.info(e.getMessage());
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Object response = new DefaultResponse(e);
		logger.info(e.getMessage());
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(response, HttpStatus.METHOD_NOT_ALLOWED);
		return responseEntity;
	}
}
