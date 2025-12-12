package com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;

/**
 * UserCommandOutputPort interface for user commands.
 * 
 * @author Created by Team DCOE Mx
 */
public interface UserCommandOutputPort {
    /**
     * Creates a new user.
     *
     * @param userDto the user to create
     * @return the created user
     */
    User create(User userDto);

    /**
     * Updates an existing user.
     *
     * @param userDto the user to update
     * @return the updated user
     */
    User update(User userDto);

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteById(String id);
}