package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.NuekRequestDTO;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEToken;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.SasToken;

public interface NuekAuthService {

    SasToken getSasToken(String id, String pwd, String realm);

    JWEToken getJWEToken4GroupedBilling(NuekRequestDTO request);

    JWEToken getJWEToken4Commerces(NuekRequestDTO request);

    JWEToken getJWEToken4CommerceTpvs(NuekRequestDTO request);

    JWEToken getJWEToken4TpvOperations(NuekRequestDTO requestDTO);

    JWEToken getJWEToken4TpvTransactions(NuekRequestDTO requestDTO);
}
