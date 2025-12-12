package com.santander.ems.mportal.emsmportalmp0001.domain.entity;

import java.io.Serializable;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserCreateCommand;

/**
 * {@link User} model record, contains the User data.
 * 
 * @author Team DCoE
 * @version 1.0
 */
public record User(String id, String name, byte age, String country) implements Serializable {

    private static final long serialVersionUID = 1L;

	/* Empty Constructor */
	public User() {
		this(null, null, (byte) 0, null);
	}

    public User requestToCreate(UserCreateCommand userCreateCommand) {
        return new User(this.id, userCreateCommand.name(), userCreateCommand.age(), userCreateCommand.country());
    }
}