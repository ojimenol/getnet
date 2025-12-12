package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest.mongo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo.UserQueryUseCase;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
class UserQueryControllerTest {

	@InjectMocks
	private UserQueryController userQueryController;
	@Mock
	private UserQueryUseCase userQueryUseCase;

	@Test
	void getById() {
		final User userDto = new User("1","JISG",(byte) 25,"Mexico");
		when(userQueryUseCase.getUserById(anyString())).thenReturn(userDto);
		final User user = userQueryController.getUserById("1");
		assertNotNull(user);
		assertEquals(user.id(), user.id());
	}

	@Test
	void getAll() {

		List<User> listUser = new ArrayList<>();
		listUser.add(new User("1","Juan", (byte)25, "Mexico"));
		listUser.add(new User("2","Pedro", (byte)25, "Mexico"));

		when(userQueryUseCase.getAllUsers()).thenReturn(listUser);
		final List<User> listUserResponse = userQueryController.getAllUsers();
		assertNotNull(listUserResponse);
		assertEquals(listUser.get(0).id(), listUserResponse.get(0).id());
	}
}