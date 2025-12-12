package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest.mongo.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.santander.ems.mportal.emsmportalmp0001.domain.exception.UserException;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
class UserControllerAdviceTest {

	@InjectMocks
	private UserControllerAdvice userControllerAdvice;

	@Test
	void testHandleEmptyInput() {

		UserException userException = new UserException("Test error");

		ResponseEntity<String> response = userControllerAdvice.handleEmptyInput(userException);

	    String expectedMessage = "Test error";
	    String actualMessage = response.getBody().toString();

	    assertTrue(actualMessage.contains(expectedMessage));
	    assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

	}
}