package com.santander.ems.mportal.emsmportalmp0001.application.ports.input.mongo;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserQueryOutputPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class UserQueryInputPortTest {

    @InjectMocks
    private UserQueryInputPort userQueryInputPort;

    @Mock
    private UserQueryOutputPort userQueryOutputPort;

    @Test
    void testGetUserById() {
        User userDto = new User("1", "John Doe", (byte)30, "USA");
        when(userQueryOutputPort.getById("1")).thenReturn(userDto);
        User user = userQueryInputPort.getUserById("1");
        assertEquals(user.name(), userDto.name());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("1", "John Doe", (byte)30, "USA"));
        users.add(new User("2", "Jane Doe", (byte)25, "USA"));
        when(userQueryOutputPort.getAll()).thenReturn(users);
        List<User> allUsers = userQueryInputPort.getAllUsers();
        assertEquals(allUsers.size(), users.size());
    }
}