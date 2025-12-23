package com.pagonxt.core.configs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public abstract class ConfigLoader {

    private ConfigLoader() {}

    static String appInsightsPrefix = "applicationinsights";

    public static Properties readPropsFromFile(String name) throws FileNotFoundException {
        Properties props = new Properties();

        BufferedReader brVaultConfig = new BufferedReader(new FileReader(name));
        JsonElement brVaultConfigJson = JsonParser.parseReader(brVaultConfig);

        if (brVaultConfigJson.isJsonNull()) {
            return props;
        }

        JsonObject jsonObjectVaultConfig = brVaultConfigJson.getAsJsonObject();

        BufferedReader brEnv = new BufferedReader(new FileReader("config/env.json"));
        JsonObject jsonObjectEnv = JsonParser.parseReader(brEnv).getAsJsonObject();
        Set<?> jsonSetEnv =  jsonObjectEnv.keySet();


        Iterator<?> i = jsonSetEnv.iterator();
        while(i.hasNext()){
            String keyEnv = i.next().toString();
            String value = jsonObjectEnv.get(keyEnv).toString();
            value = value.replace("\"","");

            String keyVault = value.replace("$vault.", "");

            if(!keyVault.isEmpty()) {
                String valueVault = jsonObjectVaultConfig.get(keyVault).toString();
                valueVault = valueVault.replace("\"","");


                if(keyEnv.contains(appInsightsPrefix)) {
                    setAppInsightKeysSystemProperties(keyEnv, valueVault);
                }
                else {
                    props.put(keyEnv, valueVault);
                }

            }
        }
        return props;
    }

    public static Properties readPropsFromLocalJson() throws FileNotFoundException {
        Properties props = new Properties();
        BufferedReader brEnv = new BufferedReader(new FileReader("config/local.json"));
        JsonElement brEnvJson = JsonParser.parseReader(brEnv);

        if (brEnvJson.isJsonNull()) {
            return props;
        }

        JsonObject jsonObjectEnv = brEnvJson.getAsJsonObject();
        Set<?> jsonSetEnv =  jsonObjectEnv.keySet();

        Iterator<?> i = jsonSetEnv.iterator();
        while(i.hasNext()){
            String keyEnv = i.next().toString();
            String value = jsonObjectEnv.get(keyEnv).toString();
            value = value.replace("\"","");

            if(keyEnv.contains(appInsightsPrefix)) {
                setAppInsightKeysSystemProperties(keyEnv, value);
            }
            else {
                props.put(keyEnv, value);
            }
        }
        return props;
    }

    private static void setAppInsightKeysSystemProperties(String key, String value) {
        System.setProperty(key, value);
    }
}
