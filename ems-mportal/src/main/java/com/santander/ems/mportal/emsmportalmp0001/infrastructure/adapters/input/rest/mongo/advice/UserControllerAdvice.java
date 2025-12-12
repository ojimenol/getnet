package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest.mongo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.santander.ems.mportal.emsmportalmp0001.domain.exception.UserException;

/**
 *  {@link UserControllerAdvice} class that is responsible for handling exceptions.
 * 
 * @author Created by Team DCOE Mx
 */
@ControllerAdvice
public class UserControllerAdvice {

    /**
     * This method is responsible for handling exceptions.
     *
	 * @param emptyInputException	object of type {@link UserException}, this object has data about the exception.
	 *
	 * @return {@link ResponseEntity} with exception data.
	 */
	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> handleEmptyInput(UserException emptyInputException) {
		return new ResponseEntity<>(emptyInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}

}