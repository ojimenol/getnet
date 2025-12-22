package com.santander.ems.mportal.emsmportalmp0001.domain.mapper;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWERequest;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEToken;
import com.santander.getnet.nuek.auth.client.model.data.JWEGenerateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JWERequestMapper {

    JWEGenerateRequest jweRequestToJweClientRequest(JWERequest request);

    JWEToken toJweToken(String jwe);
}
