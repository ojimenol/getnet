package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.repository.IUserMongoRepository;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.mapper.UserDboMapper;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.data.UserEntity;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class UserCommandOutputAdapterTest {

    @InjectMocks
    private UserCommandOutputAdapter userCommandOutputAdapter;

    @Mock
    private IUserMongoRepository userMongoRepository;

    @Spy
    private UserDboMapper userDboMapper;

    @Test
    void testCreate() {
        User user = new User("1", "Juan", (byte)25, "Mexico");
		UserEntity userEntity = new UserEntity("1", "Juan", (byte)25, "Mexico");

		when(userMongoRepository.save(any())).thenReturn(userEntity);
		User userCreate = this.userCommandOutputAdapter.create(user);
		assertNotNull(userCreate);
    }

    @Test
    void testUpdate() {
        User user = new User("2", "Pedro", (byte)28, "Mexico");
		UserEntity userEntity = new UserEntity("2", "Pedro", (byte)28, "Mexico");

		when(userMongoRepository.save(any())).thenReturn(userEntity);
		User userCreate = this.userCommandOutputAdapter.update(user);
		assertNotNull(userCreate);
    }

    @Test
    void testDeleteById() {
        doAnswer((i) -> {
			assertEquals("1", i.getArgument(0));
			return null;
		}).when(userMongoRepository).deleteById("1");

		userCommandOutputAdapter.deleteById("1");        
    }
}