
package com.camunda.myBPM.task4Deployment;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.junit.Assert.*;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.bpmn.TaskAssert;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class ProcessUnitTest {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	private static final String PROCESS_DEFINITION_KEY = "task4Deployment";

	static {
		LogFactory.useSlf4jLogging(); // MyBatis
	}

	// Set up the Fixture that will run before each test
	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}


	/**
	 * Just tests if the process definition can be deployed.
	 */
	@Test
	@Deployment(resources = "process.bpmn")
	public void testParsingAndDeployment() {
		// nothing is done here, as we just want to check for exceptions during
		// deployment
	}
	
	// Tests the checkout tasks are linked correctly. 
	// This test should fail if wrong activity name is used or processInstance variable is changed. 
	
	@Test
	@Deployment(resources = "process.bpmn")
	public void testCurrentStatus() {
			
		// Obtain test run of BPMN
		ProcessInstanceWithVariables processInstance = (ProcessInstanceWithVariables)processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

		// Obtain the value of the deliveryOK variable
		boolean deliveryOK = (boolean)processInstance.getVariables().get("deliveryOK");
		System.out.println("DeliveryOK: " + deliveryOK);
			
	    
		// Obtain a reference to the current task
		TaskAssert task = assertThat(processInstance).task();

		if (deliveryOK) {
				assertThat(processInstance).isWaitingAt("Activity_066txhw");
				task.hasName("Checkout 1");
				task.isNotAssigned();
			} else {
				assertThat(processInstance).isWaitingAt("Activity_19fouew");
				task.hasName("Checkout 2");
				task.isNotAssigned();
			}

		}
	
	// tests the completion of task. 
	@Test
	@Deployment(resources="process.bpmn")
	public void testCompletionOftask() {
		ProcessInstanceWithVariables processInstance = (ProcessInstanceWithVariables) processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
		
		//Obtain a reference to the current task
		TaskAssert taskAssert = assertThat(processInstance).task();
		TaskEntity task = (TaskEntity) taskAssert.getActual();
		task.delegate("user");
		task.resolve();
	
		
	}


}
