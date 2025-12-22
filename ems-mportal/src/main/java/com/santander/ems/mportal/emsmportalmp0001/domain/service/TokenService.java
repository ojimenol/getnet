package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.AuthToken;

public interface TokenService {

    boolean tokenExistsAndNotExpired(String name);

    <T> T getToken(String name, Class<T> returnClass);

    void putToken(String name, AuthToken token);
}
