package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest;

import com.santander.ems.mportal.emsmportalmp0001.application.ports.input.HelloWorldInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example controller for
 * Web applications
 *
 * @author Santander Technology
 */
@RestController
@RequestMapping("/ems-mportal-mp0001")
@Slf4j
@Tag(name = "Greetings", description = "Greetings controllers")
@RequiredArgsConstructor
public class HelloWorldController {

    private final HelloWorldInputPort helloWorldInputPort;

    /**
     * Basic method to control the "/hello"
     * endpoint that it receives request
     * with GET HTTP method.
     *
     * When response code is "200",
     * the operation is successful.
     *
     * When response code is "401",
     * the authentication is empty
     * or invalid.
     *
     * Method produces a response
     * in TEXT_PLAIN format.
     *
     * @return String constant message
     */
	@Operation(
			summary = "Says hello",
			description = "Endpoint that says 'Hello world!'"
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "successful operation",
					content = @Content(
							schema = @Schema(implementation = String.class)
					)
			),
			@ApiResponse(
					responseCode = "401",
					description = "UNAUTHORIZED",
					content = @Content(
							schema = @Schema(implementation = String.class)
					))
	})
	@GetMapping(value="/hello", produces=MediaType.TEXT_PLAIN_VALUE)
	public String sayHello() {
		log.info("Log from Servlet controller");
		return helloWorldInputPort.sayHello();
	}
}
