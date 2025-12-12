package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo;
import java.util.List;
import java.util.stream.Collectors;
import com.santander.ems.mportal.emsmportalmp0001.domain.entity.User;
import com.santander.ems.mportal.emsmportalmp0001.application.ports.output.mongo.UserQueryOutputPort;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.repository.IUserMongoRepository;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.mapper.UserDboMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.exception.UserException;
import com.santander.ems.mportal.emsmportalmp0001.domain.constant.UserConstant;

import org.springframework.stereotype.Repository;

/**
 * This class implements methods to insert records in the Mongo database.
 * 
 * @author Created by Team DCOE Mx
 */
@Repository
public class UserQueryOutputAdapter implements UserQueryOutputPort {

    private final IUserMongoRepository userMongoRepository;

    private final UserDboMapper userDboMapper;

    /**
     * Constructor.
     * 
     * @param userMongoRepository the user repository
     * @param userDboMapper the user mapper
     */
    public UserQueryOutputAdapter(IUserMongoRepository userMongoRepository, UserDboMapper userDboMapper) {
        this.userMongoRepository = userMongoRepository;
        this.userDboMapper = userDboMapper;
    }
    /**
     * This method is executed to search for a record in the Mongo database.
     *
     * @param id record id to search for.
     *
	 * @return {@link User}.
	 */
    @Override
    public User getById(String id) {
        return userMongoRepository.findById(id)
            .map(userDboMapper::toDomain)
            .orElseThrow(() ->
                new UserException(String.format(UserConstant.USER_NOT_FOUND_MESSAGE_ERROR, id)));
    }

    /**
     * This method is executed to search all records in the Mongo database.
     *
	 * @return list of {@link User}.
	 */
    @Override
    public List<User> getAll() {
        return userMongoRepository.findAll()
            .stream()
            .map(userDboMapper::toDomain)
            .collect(Collectors.toList());
    }
}