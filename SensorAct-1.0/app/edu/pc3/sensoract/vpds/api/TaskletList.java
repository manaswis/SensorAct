/*
 * Copyright (c) 2012, Indraprastha Institute of Information Technology,
 * Delhi (IIIT-D) and The Regents of the University of California.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 * 3. Neither the names of the Indraprastha Institute of Information
 *    Technology, Delhi and the University of California nor the names
 *    of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE IIIT-D, THE REGENTS, AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE IIITD-D, THE REGENTS
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */
/*
 * Name: TaskletList.java
 * Project: SensorAct-VPDS
 * Version: 1.0
 * Date: 2012-05-14
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.vpds.api;

import edu.pc3.sensoract.vpds.api.request.TaskletListFormat;
import edu.pc3.sensoract.vpds.constants.Const;
import edu.pc3.sensoract.vpds.enums.ErrorType;
import edu.pc3.sensoract.vpds.exceptions.InvalidJsonException;

/**
 * tasklet/list API: Lists tasklet
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class TaskletList extends SensorActAPI {

	/**
	 * Validates the tasklet list request format attributes. If validation fails,
	 * sends corresponding failure message to the caller.
	 * 
	 * @param taskListRequest
	 *            Task list request format object
	 */
	private void validateRequest(final TaskletListFormat taskListRequest) {

		validator.validateSecretKey(taskListRequest.secretkey);
		// TODO: add validation for other parameters

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_TASKLET_LIST,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
		}
	}

	/**
	 * Services the tasklet/list API.
	 * 
	 * @param taskListJson
	 *            Task list request attributes in Json string
	 */
	public void doProcess(final String taskListJson) {

		try {

			TaskletListFormat taskListRequest = convertToRequestFormat(
					taskListJson, TaskletListFormat.class);
			validateRequest(taskListRequest);

			if (!userProfile.isRegisteredSecretkey(taskListRequest.secretkey)) {
				response.sendFailure(Const.API_TASKLET_LIST,
						ErrorType.UNREGISTERED_SECRETKEY,
						taskListRequest.secretkey);
			}

			// TODO: List a tasklet
			response.SendSuccess(Const.API_TASKLET_LIST, Const.TODO);

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_TASKLET_LIST, ErrorType.INVALID_JSON,
					e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_TASKLET_LIST, ErrorType.SYSTEM_ERROR,
					e.getMessage());
		}
	}

}
