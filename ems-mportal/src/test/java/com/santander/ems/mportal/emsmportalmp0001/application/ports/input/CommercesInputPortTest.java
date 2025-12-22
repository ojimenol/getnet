package com.santander.ems.mportal.emsmportalmp0001.application.ports.input;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.mapper.NuekApiMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerces;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.NuekRequestDTO;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.NuekService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class CommercesInputPortTest {

    @Mock
    private NuekService nuekService;

    @Mock
    private NuekApiMapper nuekApiMapper;

    @InjectMocks
    private CommercesInputPort commercesInputPort;


    @Test
    void testCommerces() {
        final var request = new CommercesGetCommand("","","","","","","");

        final var mockCommerces = Commerces.builder().build();

        when(nuekApiMapper.toRequestParams(any(CommercesGetCommand.class)))
           .thenReturn(NuekRequestDTO.builder().build());

        when(nuekService.getCommerces(any(NuekRequestDTO.class)))
            .thenReturn(mockCommerces);

        var commerces = commercesInputPort.getCommerces(request);
        assertEquals(mockCommerces, commerces);
    }

}
