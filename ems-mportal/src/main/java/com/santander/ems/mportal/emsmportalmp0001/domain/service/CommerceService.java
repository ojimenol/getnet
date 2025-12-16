package com.santander.ems.mportal.emsmportalmp0001.domain.service;


import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerce;

import java.util.List;

public interface CommerceService {
  List<Commerce> getCommerces(String personCode, String personType,
                              String billingDateFrom, String billingDateTo, String order,
                              String listDateFrom, String listDateTo);
}