package com.santander.ems.mportal.emsmportalmp0001.domain.command.rest;

import java.io.Serializable;

/**
 * {@link CommercesGetCommand} model class, this class contains the data to create a record.
 * 
 * @author Team DCoE
 * @version 1.0
 */
public record CommercesGetCommand(
    String personCode, String personType, String billingDateFrom, String billingDateTo, String order,
    String listDateFrom, String listDateTo) implements Serializable {
}