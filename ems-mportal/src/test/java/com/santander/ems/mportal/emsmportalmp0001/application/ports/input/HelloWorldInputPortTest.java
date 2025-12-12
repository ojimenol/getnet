package com.santander.ems.mportal.emsmportalmp0001.application.ports.input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class HelloWorldInputPortTest {
    
    @InjectMocks
    private HelloWorldInputPort helloWorldInputPort;

    @Test
    void testSayHello() {
        String hello = helloWorldInputPort.sayHello();
        assertEquals("Hello World!", hello);
    }

}
