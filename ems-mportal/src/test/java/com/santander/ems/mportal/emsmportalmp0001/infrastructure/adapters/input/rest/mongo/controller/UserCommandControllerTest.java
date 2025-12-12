package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest.mongo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserCreateCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserEditCommand;
import com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo.UserCommandUseCase;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
class UserCommandControllerTest {

	@InjectMocks
	private UserCommandController userCommandController;

    @Mock
    private UserCommandUseCase useCase;

	@Test
	void testCreate() {
		User userReturn = new User("1","Juan", (byte)25, "Mexico");
		UserCreateCommand userCreateCommand = new UserCreateCommand("Juan", (byte)25, "Mexico");
		when(this.userCommandController.create(any())).thenReturn(userReturn);
		User user = this.userCommandController.create(userCreateCommand);
		assertNotNull(user);
		assertEquals("Juan", user.name());
	}

	@Test
	void testUserEdit() {
		User userReturn = new User("1","Pedro", (byte)28, "Mexico");
		UserEditCommand userEditCommand = new UserEditCommand("Pedro", (byte)28, "Mexico");
		when(this.userCommandController.edit(any(),anyString())).thenReturn(userReturn);
		User user = this.userCommandController.edit(userEditCommand,"1");
		assertNotNull(user);
		assertEquals("Pedro", user.name());
	}

	@Test
	void testDeleteUserById() {
		doAnswer((i) -> {
			assertEquals("1", i.getArgument(0));
			return null;
		}).when(useCase).deleteUser("1");
		this.userCommandController.delete("1");
	}
}