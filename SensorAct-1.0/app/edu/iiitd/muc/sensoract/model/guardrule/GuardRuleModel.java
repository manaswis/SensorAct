/*
 * Name: GuardRuleModel.java
 * Project: SensorAct, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-07-23
 * Author: Haksoo Choi
 */

package edu.iiitd.muc.sensoract.model.guardrule;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;

import edu.iiitd.muc.sensoract.api.request.GuardRuleAddFormat;

/**
 * Model class for guard rule management.
 *
 * @author Haksoo Choi
 * @version 1.0
 */
@Entity(value = "GuardRule", noClassnameStored = true)
public class GuardRuleModel extends Model {
	public String secretkey = null;
	public String name = null;
	public String description = null;
	public String targetOperation = null;
	public int priority = -1;
	public String condition = null;
	public String action = null;

	public GuardRuleModel(final GuardRuleAddFormat newRule) {
		if (null == newRule)
			return;
		
		secretkey = newRule.secretkey;
		name = newRule.rule.name;
		description = newRule.rule.description;
		targetOperation = newRule.rule.targetOperation;
		priority = newRule.rule.priority;
		condition = newRule.rule.condition;
		action = newRule.rule.action;
	}
	
	GuardRuleModel() {
	}
}