package com.santander.getnet.srv.merchant_portal.mapper;

import com.santander.getnet.nuek.client.model.data.JWEGenerateRequest;
import com.santander.getnet.srv.merchant_portal.model.JWERequest;
import com.santander.getnet.srv.merchant_portal.model.JWEToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JWERequestMapper {

    JWEGenerateRequest jweRequestToJweClientRequest(JWERequest request);

    JWEToken toJweToken(String jweToken);
}
