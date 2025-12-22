package com.santander.ems.mportal.emsmportalmp0001.application.ports.input;

import com.santander.ems.mportal.emsmportalmp0001.application.usecases.CommercesUseCase;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.mapper.NuekApiMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerces;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.NuekService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Input port for commerces
 */
@Component
@AllArgsConstructor
public class CommercesInputPort implements CommercesUseCase {

    private NuekService nuekService;

    private NuekApiMapper nuekMapper;

    public Commerces getCommerces(CommercesGetCommand command) {

        return nuekService.getCommerces(nuekMapper.toRequestParams(command));
    }
}
