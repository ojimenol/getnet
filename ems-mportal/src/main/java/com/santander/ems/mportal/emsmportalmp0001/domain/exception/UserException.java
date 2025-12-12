package com.santander.ems.mportal.emsmportalmp0001.domain.exception;

/**
 * {@link UserException} model class, this class contains data from an exception.
 * 
 * @author Team DCoE
 * @version 1.0
 */
public class UserException extends RuntimeException {

	/**
	 * @serial serialVersionUID
	 */
    private static final long serialVersionUID = 2L;

	/**
	 * errorMessage attribute.
	 */
    private final String errorMessage;

	/**
	 * Constructor with only required parameters
	 *
	 * @param errorMessage		Error message.
	 */
    public UserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

	/**
	 * Get errorMessage.
	 * 
	 *  @return errorMessage
	 * */
    public String getErrorMessage() {
        return errorMessage;
    }

}