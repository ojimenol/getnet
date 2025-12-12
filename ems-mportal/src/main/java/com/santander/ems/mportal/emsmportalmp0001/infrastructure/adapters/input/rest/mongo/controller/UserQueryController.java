package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest.mongo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo.UserQueryUseCase;

/**
 *  {@link UserQueryController} Class that exposes user endpoints to search data from one or more records.
 * 
 * @author Created by Team DCOE Mx
 */
@RestController
@RequestMapping("/ems-mportal-mp0001/users")
public class UserQueryController {

    private final UserQueryUseCase userQueryUseCase;

    public UserQueryController(UserQueryUseCase userQueryUseCase) {
        this.userQueryUseCase = userQueryUseCase;
    }
    /**
     * GET method with id parameter is executed to search for data from a record.
     *
	 * @param id 	record id to search.
	 *
	 * @return {@link User} of the searched record.
	 */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userQueryUseCase.getUserById(id);
    }

    /**
     * GET method is executed to find all records.
     *
	 * @return list of {@link User}.
	 */
    @GetMapping
    public List<User> getAllUsers() {
        return userQueryUseCase.getAllUsers();
    }
}