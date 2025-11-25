package com.santander.getnet.srv.merchant_portal.service;

import com.santander.getnet.srv.merchant_portal.dto.NuekRequestDTO;
import com.santander.getnet.srv.merchant_portal.model.JWEToken;
import com.santander.getnet.srv.merchant_portal.model.SasToken;

public interface NuekAuthService {

    SasToken getSasToken(String id, String pwd, String realm);

    JWEToken getJWEToken4Commerces(NuekRequestDTO request);

    JWEToken getJWEToken4CommerceTpvs();
}
