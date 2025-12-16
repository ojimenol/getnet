package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo;

import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserCommandOutputPort;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.mapper.UserDboMapper;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.repository.IUserMongoRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This class implements methods to insert records in the Mongo database.
 * 
 * @author Created by Team DCOE Mx
 */
@Repository
public class UserCommandOutputAdapter implements UserCommandOutputPort {

    private final IUserMongoRepository userMongoRepository;

    private final UserDboMapper userDboMapper;

    /**
     * Constructor.
     * 
     * @param userMongoRepository the user repository
     * @param userDboMapper the user mapper
     */
    public UserCommandOutputAdapter(IUserMongoRepository userMongoRepository, UserDboMapper userDboMapper) {
        this.userMongoRepository = userMongoRepository;
        this.userDboMapper = userDboMapper;
    }

    /**
     * Method to create a new record.
     * 
     * @param userDto object of type {@link User}, this object has data about the new record.
     * 
     * @return {@link User} of the new record.
     */
    @Override
    public User create(User userDto) {
        return Optional.of(userDto)
            .map(userDboMapper::toDboCreate)
            .map(userMongoRepository::save)
            .map(userDboMapper::toDomain)
            .orElseThrow();
    }

    /**
     * Method to update a record.
     * 
     * @param userDto object of type {@link User}, this object has the data to update.
     * 
     * @return {@link User} with the updated record.
     */
    @Override
    public User update(User userDto) {
        return Optional.of(userDto)
            .map(userDboMapper::toDboCreate)
            .map(userMongoRepository::save)
            .map(userDboMapper::toDomain)
            .orElseThrow();
    }

    /**
     * Method to delete a record.
     * 
     * @param id ID of the record to delete.
     */
    @Override
    public void deleteById(String id) {
        userMongoRepository.deleteById(id);
    }
}