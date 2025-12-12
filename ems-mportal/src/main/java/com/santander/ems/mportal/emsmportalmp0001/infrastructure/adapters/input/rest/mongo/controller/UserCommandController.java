package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest.mongo.controller;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserCreateCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserEditCommand;
import com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo.UserCommandUseCase;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * UserCommandController class for user commands.
 * 
 * @author Created by Team DCOE Mx
 */
@RestController
@RequestMapping("/ems-mportal-mp0001/users")
public class UserCommandController {

    private final UserCommandUseCase userCommandUseCase;

    /**
     * Constructor of the class.
     *
     * @param userCommandUseCase the user command use case
     */
    public UserCommandController(UserCommandUseCase userCommandUseCase) {
        this.userCommandUseCase = userCommandUseCase;
    }

    /**
     * POST Method used to generate a new record.
     *
	 * @param userCreateCommand	object of type {@link UserCreateCommand}, this object has data about the new record.
	 *
	 * @return {@link User} of the new record.
	 */
    @PostMapping()
    public User create(@RequestBody UserCreateCommand userCreateCommand) {
        return userCommandUseCase.createUser(userCreateCommand);
    }

    /**
     * PUT Method used to update a record.
     *
	 * @param userEditCommand 	object of type {@link UserEditCommand}, this object has the data to update.
	 *
	 * @param id 	ID of the record to update.
	 *
	 * @return {@link User} with the updated record.
	 */
    @PutMapping("/{id}")
    public User edit(@RequestBody UserEditCommand userEditCommand, @PathVariable String id) {
        return userCommandUseCase.editUser(userEditCommand, id);
    }

    /**
     * DELETE Method used to delete a record.
     *
	 * @param id 	record id to delete.
	 */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userCommandUseCase.deleteUser(id);
    }
}