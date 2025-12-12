package com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo;

import java.io.Serializable;

/**
 * {@link UserCreateCommand} model class, this class contains the data to create a record.
 * 
 * @author Team DCoE
 * @version 1.0
 */
public record UserCreateCommand(String name, byte age, String country) implements Serializable {
}