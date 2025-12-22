package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest;

import com.santander.ems.mportal.emsmportalmp0001.application.ports.input.CommercesInputPort;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerces;
import com.santander.framework.springboot.core.exceptions.dto.ErrorModelGateway;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Example controller for
 * Web applications
 *
 * @author Santander Technology
 */
@RestController
@RequestMapping("/ems-mportal-mp0001")
@Slf4j
@Tag(name = "Commerce", description = "ComercioController")
@RequiredArgsConstructor
public class CommercesController {

    private final CommercesInputPort commercesInputPort;

    /**
     * Basic method to control the "/v1/commerce"
     * endpoint that it receives request
     * with GET HTTP method.
     *
     * When response code is "200", the operation is successful.
     *
	 * When response code is "400", the input data is bad.
	 *
     * When response code is "401", the authentication is empty or invalid.
	 *
	 * When response code is "403", the access is forbidden.
     *
	 * When response code is "404", the resource is not found.
	 *
	 * When response code is "500", an internal error has been produced.
	 *
	 * When response code is "503", service is unavailable.
	 *
	 * When response code is "504", is gateway timeout.
	 *
     * Method produces a response
     * in APPLICATION/JSON format.
     *
     * @return CommerceListResponse constant message
     */
	@Operation(
			summary = "Retrieves a list of commerces.",
			description = "The commerces included in the response can be filtered by personCode, personType, " +
				"billingDateFrom, billingDateTo, listDateFrom, listDateTo and can be ordered ascending or descending"
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "successful operation",
					content = @Content(
							schema = @Schema(implementation = Commerces.class)
					)
			),
			//Bad request response
			@ApiResponse(
					responseCode = "400",
					description = "Bad Request",
					content = @Content(schema = @Schema(implementation = ErrorModelGateway.class))),
			//Unauthorized response
			@ApiResponse(
					responseCode = "401",
					description = "UNAUTHORIZED",
					content = @Content(
							schema = @Schema(implementation = ErrorModelGateway.class)
					)),
			//Forbidden response
			@ApiResponse(
					responseCode = "403",
					description = "Forbidden",
					content = @Content(schema = @Schema(implementation = ErrorModelGateway.class))),
			//Not Found response
			@ApiResponse(
					responseCode = "404",
					description = "Not Found",
					content = @Content(schema = @Schema(implementation = ErrorModelGateway.class))),
			//Internal server response
			@ApiResponse(
					responseCode = "500",
					description = "Internal server error",
					content = @Content(schema = @Schema(implementation = ErrorModelGateway.class))),
			//Service unavailable response
			@ApiResponse(
					responseCode = "503",
					description = "Service unavailable",
					content = @Content(schema = @Schema(implementation = ErrorModelGateway.class))),
			//Gateway timeout response
			@ApiResponse(
					responseCode = "504",
					description = "Gateway timeout",
					content = @Content(schema = @Schema(implementation = ErrorModelGateway.class)))

	})
	@GetMapping(value="/v2/commerce", produces=MediaType.APPLICATION_JSON_VALUE)
	public Commerces getCommerces(
			@RequestHeader("Authorization") String authorization, @RequestParam("personCode") String personCode,
			@RequestParam ("personType")String personType, @RequestParam("billingDateFrom") String billingDateFrom,
			@RequestParam("billingDateTo") String billingDateTo, @RequestParam("order") String order,
			@RequestParam("listDateFrom") String listDateFrom, @RequestParam("listDateTo") String listDateTo) {
		log.info("Log from /v2/commerce");
		return Optional.of(new CommercesGetCommand(
				personCode, personType, billingDateFrom, billingDateTo, order, listDateFrom, listDateTo))
			.map(commercesInputPort::getCommerces)
			.orElseThrow(() -> new RuntimeException("v2/commerce : Can't get commerces"));
	}
}
