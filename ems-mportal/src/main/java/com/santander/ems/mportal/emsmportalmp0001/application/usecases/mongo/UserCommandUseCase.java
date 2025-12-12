package com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserCreateCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserEditCommand;

/**
 * UserCommandUseCase interface for user commands.
 * 
 * @author Created by Team DCOE Mx
 */
public interface UserCommandUseCase {

    /**
     * Creates a new user.
     *
     * @param userCreateCommand the user to create
     * @return the created user
     */
    User createUser(UserCreateCommand userCreateCommand);

    /**
     * Updates an existing user.
     *
     * @param userEditCommand the user to update
     * @param id the ID of the user to update
     * @return the updated user
     */
    User editUser(UserEditCommand userEditCommand, String id);

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(String id);
}