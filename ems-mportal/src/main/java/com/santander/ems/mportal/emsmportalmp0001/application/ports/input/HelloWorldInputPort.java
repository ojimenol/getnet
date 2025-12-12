package com.santander.ems.mportal.emsmportalmp0001.application.ports.input;

import com.santander.ems.mportal.emsmportalmp0001.domain.service.HelloWorldService;
import com.santander.ems.mportal.emsmportalmp0001.application.usecases.HelloWorldUseCase;
import org.springframework.stereotype.Component;

/**
 * Input port for hello world application
 * 
 * @author Created by Team DCOE Mx
 */
@Component
public class HelloWorldInputPort implements HelloWorldUseCase {
    
    /**
     * Say hello
     * 
     * @return a greeting
     */
    @Override
    public String sayHello() {
        return HelloWorldService.sayHello();
    }
}
