package com.santander.ems.mportal.emsmportalmp0001.application.ports.input.mongo;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserCreateCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo.UserEditCommand;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserCommandOutputPort;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserQueryOutputPort;
import com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo.UserCommandUseCase;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The input port for user command operations in a Mongo database.
 * 
 * @author Created by Team DCOE Mx
 */
@Component
public class UserCommandInputPort implements UserCommandUseCase {

    private final UserCommandOutputPort commandOutputPort;
    private final UserQueryOutputPort queryOutputPort;

    /**
     * Constructs a new UserCommandInputPort with the specified command output port and query output port.
     *
     * @param commandOutputPort the command output port to be used by the input port
     * @param queryOutputPort the query output port to be used by the input port
     */
    public UserCommandInputPort(UserCommandOutputPort commandOutputPort, UserQueryOutputPort queryOutputPort) {
        this.commandOutputPort = commandOutputPort;
        this.queryOutputPort = queryOutputPort;
    }

    /**
     * Creates a new user.
     *
     * @param userCreateCommand the command to create a user
     * @return the created user
     */
    @Override
    public User createUser(UserCreateCommand userCreateCommand) {
        return Optional.of(userCreateCommand)
            .map(new User()::requestToCreate).map(commandOutputPort::create)
            .orElseThrow(() -> new RuntimeException("Can't create user: " + userCreateCommand));
    }

    /**
     * Edits an existing user.
     *
     * @param userEditCommand the command to edit a user
     * @param id the ID of the user to edit
     * @return the edited user
     */
    @Override
    public User editUser(UserEditCommand userEditCommand, String id) {
        return Optional.of(id)
            .map(queryOutputPort::getById)
            .map(user -> user.requestToEdit(userEditCommand))
            .map(commandOutputPort::update)
            .orElseThrow(() -> new RuntimeException("Can't update user: " + userEditCommand));
    }

    /**
     * Deletes a user.
     *
     * @param id the ID of the user to delete
     */
    @Override
    public void deleteUser(String id) {
        Optional.of(id)
            .map(queryOutputPort::getById)
            .map(User::id)
            .ifPresent(commandOutputPort::deleteById);
    }
}