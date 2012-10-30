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
 * Name: DeviceListResponseFormat.java
 * Project: SensorAct-VPDS
 * Version: 1.0
 * Date: 2012-04-14
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.vpds.api.response;

import java.util.ArrayList;

import edu.pc3.sensoract.vpds.api.request.DeviceAddFormat.DeviceActuator;
import edu.pc3.sensoract.vpds.api.request.DeviceAddFormat.DeviceChannel;
import edu.pc3.sensoract.vpds.api.request.DeviceAddFormat.DeviceFormat;
import edu.pc3.sensoract.vpds.api.request.DeviceAddFormat.DeviceSensor;
import edu.pc3.sensoract.vpds.model.DeviceActuatorModel;
import edu.pc3.sensoract.vpds.model.DeviceChannelModel;
import edu.pc3.sensoract.vpds.model.DeviceModel;
import edu.pc3.sensoract.vpds.model.DeviceSensorModel;
import edu.pc3.sensoract.vpds.model.rdbms.DeviceActuatorRModel;
import edu.pc3.sensoract.vpds.model.rdbms.DeviceChannelRModel;
import edu.pc3.sensoract.vpds.model.rdbms.DeviceRModel;
import edu.pc3.sensoract.vpds.model.rdbms.DeviceSensorRModel;

/**
 * Defines the response format for device/list API.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceProfileFormat extends DeviceFormat {

	public DeviceProfileFormat(DeviceModel device) {

		devicename = device.devicename;
		isglobal = device.isglobal;
		IP = device.IP;
		location = device.location;
		tags = device.tags;
		latitude = device.latitude;
		longitude = device.longitude;

		sensors = new ArrayList<DeviceSensor>();
		for (DeviceSensorModel s : device.sensors) {
			DeviceSensor ds = new DeviceSensor();
			ds.name = s.name;
			ds.sid = Integer.parseInt(s.sid);
			ds.channels = new ArrayList<DeviceChannel>();
			for (DeviceChannelModel c : s.channels) {
				DeviceChannel dc = new DeviceChannel();
				dc.name = c.name;
				dc.type = c.type;
				dc.unit = c.unit;
				dc.samplingperiod = c.samplingperiod;
				ds.channels.add(dc);
			}
			sensors.add(ds);
		}

		actuators = new ArrayList<DeviceActuator>();
		for (DeviceActuatorModel a : device.actuators) {
			DeviceActuator da = new DeviceActuator();
			da.name = a.name;
			da.aid = a.aid;
		}
	}

	public DeviceProfileFormat(DeviceRModel device) {
		devicename = device.devicename;
		templatename = device.templatename;
		isglobal = device.isglobal;
		IP = device.IP;
		location = device.location;
		tags = device.tags;
		latitude = device.latitude;
		longitude = device.longitude;

		sensors = new ArrayList<DeviceSensor>();
		for (DeviceSensorRModel s : device.sensors) {
			DeviceSensor ds = new DeviceSensor();
			ds.name = s.name;
			ds.sid = Integer.parseInt(s.sid);
			ds.channels = new ArrayList<DeviceChannel>();
			for (DeviceChannelRModel c : s.channels) {
				DeviceChannel dc = new DeviceChannel();
				dc.name = c.name;
				dc.type = c.type;
				dc.unit = c.unit;
				dc.samplingperiod = c.samplingperiod;
				ds.channels.add(dc);
			}
			sensors.add(ds);
		}

		actuators = new ArrayList<DeviceActuator>();
		for (DeviceActuatorRModel a : device.actuators) {
			DeviceActuator da = new DeviceActuator();
			da.name = a.name;
			da.aid = a.aid;
			actuators.add(da);
		}
	}
}