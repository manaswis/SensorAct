/*
 * Name: ErrorType.java
 * Project: SensorAct, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-04-14
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.enums;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * Defines various error types. Each error type contains an error code and its
 * brief description.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public enum ErrorType {

	// Common error types
	SYSTEM_ERROR				(10, Const.SYSTEM_ERROR),
	INVALID_JSON			    (11, Const.INVALID_JSON),
	VALIDATION_FAILED		    (12, Const.VALIDATION_FAILED),
	UNREGISTERED_SECRETKEY		(13, Const.UNREGISTERED_SECRETKEY),
	UNREGISTERED_USERNAME       (14, Const.UNREGISTERED_USERNAME),

	// User profile management
	USER_ALREADYEXISTS	        (20, Const.USER_ALREADYEXISTS),
	LOGIN_FAILED	            (21, Const.LOGIN_FAILED),

	// Repo profile management
	REPOPROFILE_ALREADYEXISTS	(26, null),

	// Device profile management
	DEVICE_ALREADYEXISTS		(30, Const.DEVICE_ALREADYEXISTS),
	DEVICE_NOTFOUND			    (31, Const.DEVICE_NOTFOUND),
	DEVICE_NODEVICE_FOUND		(32, Const.DEVICE_NODEVICE_FOUND),

	END                         (100, null);


	private final int code;
	private final String message;

	private ErrorType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + message;
	}
}
