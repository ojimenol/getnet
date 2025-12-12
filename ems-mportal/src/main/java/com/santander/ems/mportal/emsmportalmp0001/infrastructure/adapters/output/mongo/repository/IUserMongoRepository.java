package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.output.mongo.data.UserEntity;

/**
 * {@link IUserMongoRepository} Interface that allows access to CRUD operations on a repository,
 *  this interface consumes the Mongo database.
 * 
 * @author Created by Team DCOE Mx
 */
@Repository
public interface IUserMongoRepository extends MongoRepository<UserEntity, String> {
}
