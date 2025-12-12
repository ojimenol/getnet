package com.santander.ems.mportal.emsmportalmp0001;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@AutoConfigureObservability
@SpringBootTest
class ApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;


	@Test
	void testContextLoads() {
		assertNotNull(applicationContext, "We expect the applicationContext to be present");
	}
}
