package com.santander.getnet.srv.merchant_portal.service.impl;

import com.santander.getnet.srv.merchant_portal.model.AuthToken;
import com.santander.getnet.srv.merchant_portal.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenServiceImpl implements TokenService {

    private final Map<String, AuthToken> tokens;

    public TokenServiceImpl() {
        this.tokens = new ConcurrentHashMap<>();
    }

    public boolean tokenExistsAndNotExpired(String name) {
        return tokens.containsKey(name) && !tokens.get(name).isExpired();
    }

    public <T> T getToken(String name, Class<T> returnClass) {
        return Optional.of(name).map(tokens::get).map(returnClass::cast).orElse(null);
    }

    public void putToken(String name, AuthToken token) {
        tokens.put(name, token);
    }
}
