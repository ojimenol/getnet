package com.santander.ems.mportal.emsmportalmp0001.application.usecases;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerces;

public interface CommercesUseCase {

    Commerces getCommerces(CommercesGetCommand command);

}
