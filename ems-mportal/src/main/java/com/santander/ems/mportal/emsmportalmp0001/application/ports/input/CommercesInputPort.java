package com.santander.ems.mportal.emsmportalmp0001.application.ports.input;

import com.santander.ems.mportal.emsmportalmp0001.application.usecases.CommercesUseCase;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerce;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.CommerceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Input port for commerces
 */
@Component
@AllArgsConstructor
public class CommercesInputPort implements CommercesUseCase {

    private CommerceService commerceService;

    public List<Commerce> getCommerces(CommercesGetCommand command) {
        return commerceService.getCommerces(
            command.personCode(), command.personType(), command.billingDateFrom(), command.billingDateTo(),
                command.order(), command.listDateFrom(), command.listDateTo());
    }
}
