/*
 * Name: GuardRuleDelete.java
 * Project: SensorAct, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-14
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.api.request.GuardRuleDeleteFormat;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.enums.ErrorType;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.profile.UserProfile;

/**
 * guardrule/delete API: Deletes a guard rule
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class GuardRuleDelete extends SensorActAPI {

	/**
	 * Converts guardrule/delete request attributes in Json string to object.
	 * 
	 * @param guardRuleDeleteJson
	 *            Guard rule delete request attributes in Json string
	 * @return Converted guard rule delete request format object
	 * @throws InvalidJsonException
	 *             If the Json string is not valid or not in the required
	 *             request format
	 * @see GuardRuleDeleteFormat
	 */
	private GuardRuleDeleteFormat convertToGuardRuleDeleteFormat(
			final String guardRuleDeleteJson) throws InvalidJsonException {

		GuardRuleDeleteFormat guardRuleDeleteFormat = null;
		try {
			guardRuleDeleteFormat = gson.fromJson(guardRuleDeleteJson,
					GuardRuleDeleteFormat.class);
		} catch (Exception e) {
			throw new InvalidJsonException(e.getMessage());
		}

		if (null == guardRuleDeleteFormat) {
			throw new InvalidJsonException(Const.EMPTY_JSON);
		}
		return guardRuleDeleteFormat;
	}

	/**
	 * Validates the guard rule delete request format attributes. If validation
	 * fails, sends corresponding failure message to the caller.
	 * 
	 * @param guardRuleDeleteRequest
	 *            Guard rule delete request format object
	 */
	private void validateRequest(final GuardRuleDeleteFormat guardRuleDeleteRequest) {

		validator.validateSecretKey(guardRuleDeleteRequest.secretkey);
		// TODO: add validation for other parameters

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_GUARDRULE_DELETE,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
		}
	}

	/**
	 * Services the guardrule/delete API.
	 * 
	 * @param guardRuleDeleteJson
	 *            Guard rule delete request attributes in Json string
	 */
	public void doProcess(final String guardRuleDeleteJson) {

		try {

			GuardRuleDeleteFormat guardRuleDeleteRequest = convertToGuardRuleDeleteFormat(guardRuleDeleteJson);
			validateRequest(guardRuleDeleteRequest);

			if (!UserProfile
					.isRegisteredSecretkey(guardRuleDeleteRequest.secretkey)) {
				response.sendFailure(Const.API_GUARDRULE_DELETE,
						ErrorType.UNREGISTERED_SECRETKEY,
						guardRuleDeleteRequest.secretkey);
			}

			// TODO: delete guard rule

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_GUARDRULE_DELETE, ErrorType.INVALID_JSON,
					e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_GUARDRULE_DELETE, ErrorType.SYSTEM_ERROR,
					e.getMessage());
		}
	}

	
}