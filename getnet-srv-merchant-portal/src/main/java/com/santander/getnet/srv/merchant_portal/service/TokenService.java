package com.santander.getnet.srv.merchant_portal.service;

import com.santander.getnet.srv.merchant_portal.model.AuthToken;

public interface TokenService {

    boolean tokenExistsAndNotExpired(String name);

    <T> T getToken(String name, Class<T> returnClass);

    void putToken(String name, AuthToken token);
}
