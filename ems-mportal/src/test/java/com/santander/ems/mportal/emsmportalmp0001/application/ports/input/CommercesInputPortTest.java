package com.santander.ems.mportal.emsmportalmp0001.application.ports.input;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerce;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.CommerceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class CommercesInputPortTest {

    @Mock
    private CommerceService commerceService;

    @InjectMocks
    private CommercesInputPort commercesInputPort;


    @Test
    void testCommerces() {
        var getCommand = new CommercesGetCommand("", "", "", "", "", "", "");

        final List<Commerce> mockCommerces = List.of();

        when(commerceService.getCommerces(
                getCommand.personCode(), getCommand.personType(),
                getCommand.billingDateFrom(), getCommand.billingDateTo(), getCommand.order(),
                getCommand.listDateFrom(), getCommand.listDateTo())).thenReturn(mockCommerces);

        var commerces = commercesInputPort.getCommerces(getCommand);
        assertEquals(mockCommerces, commerces);
    }

}
