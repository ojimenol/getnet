package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.mapper;

import org.springframework.stereotype.Component;

import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.data.UserEntity;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;

/**
 * This class declares methods to map to business objects to entity objects.
 * 
 * @author Created by Team DCOE Mx
 */
@Component
public class UserDboMapper {

	/**
	 * This method converts business objects to entity objects.
	 *
	 * @param domain object of type {@link User}.
	 *
	 * @return {@link UserEntity}.
	 */
	public UserEntity toDbo(User domain) {
		if (domain == null) {
			return null;
		}

		return new UserEntity(domain.id(), domain.name(), domain.age(), domain.country());
	}

	/**
	 * This method converts business objects to entity objects.
	 *
	 * @param domain object of type {@link User}.
	 *
	 * @return {@link UserEntity}.
	 */
	public UserEntity toDboCreate(User domain) {
		if (domain == null) {
			return null;
		}
		return new UserEntity(domain.name(), domain.age(), domain.country());
	}

	/**
	 * This method converts entity objects to business objects.
	 *
	 * @param entity object of type {@link UserEntity}.
	 *
	 * @return {@link User}.
	 */
	public User toDomain(UserEntity entity) {
		if (entity == null) {
			return null;
		}

		return new User(entity.id(), entity.name(), entity.age(), entity.country());
	}

}