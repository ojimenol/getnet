package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HelloWorldServiceTest {
    @Test
    public void testSayHello() {
        assertEquals("Hello World!", HelloWorldService.sayHello());
    }
}
