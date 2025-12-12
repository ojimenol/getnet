package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.condition.DisabledInNativeImage;

import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.data.UserEntity;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;

@DisabledInNativeImage
@ExtendWith(MockitoExtension.class)
class UserDboMapperTest {

	@InjectMocks
	private UserDboMapper userDboMapper;

	@Test
	void testUserDboMapper() {
		User user = new User("1", "Juan", (byte)25, "Mexico");
		UserEntity userEntity = new UserEntity("1", "Juan", (byte)25, "Mexico");
		User userNull = null;
		UserEntity userEntityNull = null;

		UserEntity userTest = userDboMapper.toDbo(user);
		assertNotNull(userTest);
		assertEquals("1",userTest.id());
		assertEquals("Juan",userTest.name());

		userTest = userDboMapper.toDbo(userNull);
		assertNull(userTest);

		userTest = userDboMapper.toDboCreate(user);
		assertNotNull(userTest);
		assertEquals("Juan",userTest.name());

		userNull = null;
		userTest = userDboMapper.toDboCreate(userNull);
		assertNull(userTest);

		user = userDboMapper.toDomain(userEntity);
		assertNotNull(user);
		assertEquals("Juan",user.name());

		user = userDboMapper.toDomain(userEntityNull);
		assertNull(userTest);

	}
}