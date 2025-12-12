package com.santander.ems.mportal.emsmportalmp0001.application.ports.input.mongo;

import org.springframework.stereotype.Component;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserQueryOutputPort;
import com.santander.ems.mportal.emsmportalmp0001.application.usecases.mongo.UserQueryUseCase;
import java.util.List;

/**
 * This class implements methods to search for records in the Mongo database.
 * 
 * @author Team DCoE
 */
@Component
public class UserQueryInputPort implements UserQueryUseCase {

    private final UserQueryOutputPort queryOutputPort;

    /**
     * Constructs a new UserQueryInputPort with the specified UserQueryOutputPort.
     *
     * @param queryOutputPort the UserQueryOutputPort to be used by the UserQueryInputPort
     */
    public UserQueryInputPort(UserQueryOutputPort queryOutputPort) {
        this.queryOutputPort = queryOutputPort;
    }
    /**
     * This method is executed to search for a record in the Mongo database.
     * 
     * @param id record id to search for.
     * @return {@link User}.
     */
    @Override
    public User getUserById(String id) {
        return queryOutputPort.getById(id);
    }

    /**
     * This method is executed to search all records in the Mongo database.
     * 
     * @return list of {@link User}.
     */
    @Override
    public List<User> getAllUsers() {
        return queryOutputPort.getAll();
    }
}