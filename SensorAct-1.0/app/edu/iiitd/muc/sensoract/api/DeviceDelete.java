/*
 * Name: DeviceDelete.java
 * Project: SensorAct, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-04-14
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.api.request.DeviceDeleteFormat;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.enums.ErrorType;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.profile.DeviceProfile;
import edu.iiitd.muc.sensoract.profile.UserProfile;

/**
 * device/delete API: Deletes an existing device profile from the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceDelete extends SensorActAPI {

	/**
	 * Converts the device/delete request attributes in Json string to object.
	 * 
	 * @param deviceDeleteJson
	 *            Device delete request attributes in Json string
	 * @return Converted device delete request format object
	 * @throws InvalidJsonException
	 *             If the Json string is not valid or not in the required
	 *             request format
	 * @see DeviceDeleteFormat
	 */
	public DeviceDeleteFormat convertToDeviceDeleteFormat(
			final String deviceDeleteJson) throws InvalidJsonException {

		DeviceDeleteFormat deviceDeleteFormat = null;
		try {
			deviceDeleteFormat = gson.fromJson(deviceDeleteJson,
					DeviceDeleteFormat.class);
		} catch (Exception e) {
			throw new InvalidJsonException(e.getMessage());
		}

		if (null == deviceDeleteFormat) {
			throw new InvalidJsonException(Const.EMPTY_JSON);
		}
		return deviceDeleteFormat;
	}

	/**
	 * Validates the device delete request format attributes. If validation
	 * fails, sends corresponding failure message to the caller.
	 * 
	 * @param deviceDeleteRequest
	 *            Delete device request format object
	 */
	private void validateRequest(final DeviceDeleteFormat deviceDeleteRequest) {

		validator.validateSecretKey(deviceDeleteRequest.secretkey);
		validator.validateDeviceName(deviceDeleteRequest.devicename);

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_DEVICE_DELETE,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
		}
	}

	/**
	 * Services the device/delete API.
	 * 
	 * Removes a device profile corresponding to the user's secret key and
	 * device name from the repository. Sends success or failure, in case of any
	 * error, response message in Json to the caller.
	 * 
	 * @param deviceDeleteJson
	 *            Device delete request attributes in Json string
	 */
	public void doProcess(final String deviceDeleteJson) {

		try {

			DeviceDeleteFormat deviceDeleteRequest = convertToDeviceDeleteFormat(deviceDeleteJson);
			validateRequest(deviceDeleteRequest);

			if (!UserProfile
					.isRegisteredSecretkey(deviceDeleteRequest.secretkey)) {
				response.sendFailure(Const.API_DEVICE_DELETE,
						ErrorType.UNREGISTERED_SECRETKEY,
						deviceDeleteRequest.secretkey);
			}

			if (!DeviceProfile.deleteDeviceProfile(
					deviceDeleteRequest.secretkey,
					deviceDeleteRequest.devicename)) {
				response.sendFailure(Const.API_DEVICE_DELETE,
						ErrorType.DEVICE_NOTFOUND,
						deviceDeleteRequest.devicename);
			}
			response.SendSuccess(Const.API_DEVICE_DELETE, Const.DEVICE_DELETED,
					deviceDeleteRequest.devicename);

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_DEVICE_DELETE,
					ErrorType.INVALID_JSON, e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_DEVICE_DELETE,
					ErrorType.SYSTEM_ERROR, e.getMessage());
		}

	}

}
