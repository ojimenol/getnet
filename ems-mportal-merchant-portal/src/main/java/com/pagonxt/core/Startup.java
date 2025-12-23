package com.pagonxt.core;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import com.pagonxt.core.models.LogMessage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.FileNotFoundException;
import java.util.Properties;

import static com.pagonxt.core.configs.ConfigLoader.*;
import static com.pagonxt.core.helpers.Environment.*;

@SpringBootApplication
public class Startup {
    public static void main(String[] args) throws FileNotFoundException {
        Properties props = isLocal() != null && isLocal()
                ? readPropsFromLocalJson()
                : readPropsFromFile("vault/vault-config.json");

        ApplicationInsights.attach();
        LogMessage.infoGeneric("Application started at port 8080 ", Startup.class);

        new SpringApplicationBuilder(Startup.class)
                .properties(props).run(args);
    }
}
