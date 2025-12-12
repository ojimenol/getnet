package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.mapper.UserDboMapper;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.data.UserEntity;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.repository.IUserMongoRepository;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.domain.exception.UserException;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class UserQueryOutputAdapterTest {
    
    @InjectMocks
    private UserQueryOutputAdapter userQueryOutputAdapter;

    @Mock
    private IUserMongoRepository userMongoRepository;

    @Spy
    private UserDboMapper userDboMapper;

    @Test
    void testGetById() {
		UserEntity userMock = new UserEntity("1", "Juan", (byte)25, "Mexico");
		Optional<UserEntity> userOptional = Optional.of(userMock);
		when(userMongoRepository.findById(anyString())).thenReturn(userOptional);
		User userTest = userQueryOutputAdapter.getById(anyString());
		assertNotNull(userTest);
		assertEquals(userMock.id(),userTest.id());
		assertEquals("Juan",userTest.name());
	}

    @Test
	void testGetByIdException() {
		String expectedMessage = "No se encontro un usuario con el id";
		when(userMongoRepository.findById(anyString())).thenReturn(Optional.empty());
		try {
			userQueryOutputAdapter.getById(anyString());
		}catch (UserException e) {
		    String actualMessage = e.getErrorMessage();
		    assertTrue(actualMessage.contains(expectedMessage));
		}
	}

	@Test
	void testGetAll() {
		List<UserEntity> listUser = new ArrayList<>();
		listUser.add(new UserEntity( "Juan", (byte)25, "Mexico"));
		listUser.add(new UserEntity( "Pedro", (byte)25, "Mexico"));
		when(userMongoRepository.findAll()).thenReturn(listUser);
		List<User> listUserTest = userQueryOutputAdapter.getAll();
		assertNotNull(listUserTest);
		assertEquals("Juan",listUserTest.get(0).name());
	}
}