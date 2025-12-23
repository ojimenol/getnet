package com.pagonxt.core.helpers;

public interface Environment {

    public static Boolean isLocal() {
        return Boolean.parseBoolean(System.getenv("local"));
    }
}
