package com.santander.ems.mportal.emsmportalmp0001.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the SwaggerConfig.
 *
 * Disables in native because WebApplicationContextRunner is not supported in native mode
 */
@DisabledInNativeImage
class SwaggerConfigTest {


	private WebApplicationContextRunner applicationContextRunner = new WebApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(SwaggerConfig.class));

	@Test
	void testIsSwaggerConfigPresent() {
		this.applicationContextRunner.run((context) -> {
			assertThat(context).overridingErrorMessage("We expect the apiInfo bean to be present").hasBean("apiInfo");
			assertThat(context).overridingErrorMessage("We expect the api bean to be present").hasBean("api");

			// OpenAPI assert
			OpenAPI openAPI = (OpenAPI) context.getBean("apiInfo");
			assertTrue(openAPI.getInfo().getTitle().contains("ems-mportal-mp0001"), "The title should contain 'ems-mportal-mp0001'");
			assertTrue(openAPI.getInfo().getVersion().contains("1.0.0-SNAPSHOT"), "The version should contain '1.0.0-SNAPSHOT'");
			assertTrue(openAPI.getComponents().getSecuritySchemes().containsKey("BearerAuth"), "The security schemes should contain 'BearerAuth'");

			// GroupedOpenApi assert
			GroupedOpenApi groupedOpenApi = (GroupedOpenApi) context.getBean("api");
			assertThat(groupedOpenApi).overridingErrorMessage("We expect the api bean to be present").isNotNull();
			assertTrue(groupedOpenApi.getGroup().equalsIgnoreCase("api-web"), "The group should be 'api-web'");
			assertTrue(groupedOpenApi.getPackagesToScan().contains("com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest"), "The packages to scan should contain 'com.santander.ems.mportal.emsmportalmp0001.web'");

			// OpenApiCustomiser assert
			PathItem pathItem = new PathItem();
			pathItem.operation(PathItem.HttpMethod.GET, new Operation());
			openAPI.setPaths(new Paths().addPathItem("test", pathItem));
			groupedOpenApi.getOpenApiCustomizers().forEach(openApiCustomizer ->
					assertDoesNotThrow(() ->
							openApiCustomizer.customise(openAPI), "The OpenApiCustomizer should not throw an exception"));

			//openAPI headers assert
			Map<String, Parameter> oc = openAPI.getComponents().getParameters();
			assertTrue(oc.containsKey("x-santander-client-id"), "The parameters should contain 'x-santander-client-id'");

		});
	}

}
