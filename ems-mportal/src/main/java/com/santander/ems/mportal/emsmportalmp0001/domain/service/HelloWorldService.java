package com.santander.ems.mportal.emsmportalmp0001.domain.service;

/**
 * Domain layer service class, in this case the application business logic is simply printing Hello World!
 * 
 * @author Created by Team DCOE Mx
 */
public class HelloWorldService {
    // Private constructor to hide the implicit public one
    private HelloWorldService() {}
    
    /**
     * Method to say hello
     * 
     * @return a greeting
     */
    public static String sayHello() {
        return "Hello World!";
    }
}
