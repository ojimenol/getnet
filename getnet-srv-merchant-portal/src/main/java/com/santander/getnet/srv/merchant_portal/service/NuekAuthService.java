package com.santander.getnet.srv.merchant_portal.service;

import com.santander.getnet.srv.merchant_portal.model.JWERequest;
import com.santander.getnet.srv.merchant_portal.model.JWEToken;
import com.santander.getnet.srv.merchant_portal.model.SasToken;

public interface NuekAuthService {

    SasToken getSasToken(String id, String pwd, String realm);

    JWEToken getJWEToken(JWERequest request);
}
