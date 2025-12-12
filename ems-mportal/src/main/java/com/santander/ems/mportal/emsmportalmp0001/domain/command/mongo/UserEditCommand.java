package com.santander.ems.mportal.emsmportalmp0001.domain.command.mongo;

import java.io.Serializable;

/**
 * {@link UserEditCommand} model class, this class contains the data to create a record.
 * 
 * @author Team DCoE
 * @version 1.0
 */
public record UserEditCommand(String name, byte age, String country) implements Serializable {
}