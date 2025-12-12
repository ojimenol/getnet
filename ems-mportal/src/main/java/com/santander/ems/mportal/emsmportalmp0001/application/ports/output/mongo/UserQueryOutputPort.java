package com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo;

import java.util.List;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;

/**
 * UserQueryOutputPort interface for user queries.
 * 
 * @author Created by Team DCOE Mx
 */
public interface UserQueryOutputPort {

    /**
     * Gets a user by ID.
     *
     * @param id the ID of the user to get
     * @return the user
     */
    User getById(String id);

    /**
     * Gets all users.
     *
     * @return list of users
     */
    List<User> getAll();
}