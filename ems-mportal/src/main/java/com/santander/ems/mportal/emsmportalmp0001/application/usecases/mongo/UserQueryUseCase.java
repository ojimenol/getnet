package com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo;
import java.util.List;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;

/**
 * UserQueryUseCase interface for user queries.
 * 
 * @author Created by Team DCOE Mx
 */
public interface UserQueryUseCase {

    /**
     * Gets a user by ID.
     *
     * @param id the ID of the user to get
     * @return the user
     */
    User getUserById(String id);

    /**
     * Gets all users.
     *
     * @return list of users
     */
    List<User> getAllUsers();
}