package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.data;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * {@link UserEntity} entity record, contains the User data for mongo
 * 
 * @author Created by Team DCOE Mx
 */
@Document("users")
public record UserEntity(
    @Id String id,
    String name,
    byte age,
    String country
) implements Serializable {
    private static final long serialVersionUID = 1L;

	public UserEntity(String name, byte age, String country) {
		this(null, name, age, country);
	}
}