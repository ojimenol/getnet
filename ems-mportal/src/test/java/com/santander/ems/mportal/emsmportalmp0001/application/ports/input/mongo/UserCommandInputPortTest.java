package com.santander.ems.mportal.emsmportalmp0001.application.ports.input.mongo;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserCreateCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserEditCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserCommandOutputPort;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserQueryOutputPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class UserCommandInputPortTest {

    @InjectMocks
    private UserCommandInputPort userCommandInputPort;

    @Mock
    private UserCommandOutputPort userCommandOutputPort;

    @Mock
    private UserQueryOutputPort userQueryOutputPort;

    @Test
    void testCreateUser() {
        UserCreateCommand userCreateCommand = new UserCreateCommand("John Doe", (byte)30, "USA");
        User userDto = new User().requestToCreate(userCreateCommand);
        when(userCommandOutputPort.create(any())).thenReturn(userDto);
        User user = userCommandInputPort.createUser(userCreateCommand);
        assertEquals(user.name(), userDto.name());
    }

    @Test
    void testEditUser() {
        UserEditCommand userEditCommand = new UserEditCommand("John Doe", (byte)30, "USA");
        User userDto = new User("1", "John Doe", (byte)30, "USA");
        when(userQueryOutputPort.getById("1")).thenReturn(userDto);
        when(userCommandOutputPort.update(any())).thenReturn(userDto);
        User user = userCommandInputPort.editUser(userEditCommand, "1");
        assertEquals(user.name(), userDto.name());
    }

    @Test
    void testDeleteUser() {
        User userDto = new User("1", "John Doe", (byte)30, "USA");
        when(userQueryOutputPort.getById("1")).thenReturn(userDto);
        doNothing().when(userCommandOutputPort).deleteById("1");
        userCommandInputPort.deleteUser("1");
        verify(userCommandOutputPort, times(1)).deleteById("1");
    }
}