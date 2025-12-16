package com.santander.ems.mportal.emsmportalmp0001.application.usecases;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerce;

import java.util.List;

public interface CommercesUseCase {

    List<Commerce> getCommerces(CommercesGetCommand command);

}
