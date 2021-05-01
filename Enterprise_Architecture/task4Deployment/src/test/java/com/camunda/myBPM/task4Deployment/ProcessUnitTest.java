//
//package com.camunda.myBPM.task4Deployment;
//
//import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
//
//import static org.junit.Assert.assertThat;
////import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
//
//import org.apache.ibatis.logging.LogFactory;
//import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
//import org.camunda.bpm.engine.test.Deployment;
//import org.camunda.bpm.engine.test.ProcessEngineRule;
//import org.camunda.bpm.engine.test.assertions.bpmn.TaskAssert;
//import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
//import org.junit.Before;
//import org.junit.ClassRule;
//import org.junit.Rule;
//import org.junit.Test;
//
///**
// * Test case starting an in-memory database-backed Process Engine.
// */
//public class ProcessUnitTest {
//	@ClassRule
//	@Rule
//	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();
//
//	private static final String PROCESS_DEFINITION_KEY = "task4Deployment";
//
//	static {
//		LogFactory.useSlf4jLogging(); // MyBatis
//	}
//
//	// Set up the Fixture that will run before each test
//	@Before
//	public void setup() {
//		init(rule.getProcessEngine());
//	}
//
//
//	/**
//	 * Just tests if the process definition is deployable.
//	 */
//	@Test
//	@Deployment(resources = "process.bpmn")
//	public void testParsingAndDeployment() {
//		// nothing is done here, as we just want to check for exceptions during
//		// deployment
//	}
//	
//	@Test
//	@Deployment(resources = "process.bpmn")
//	public void testCurrentStatus() {
//			
//		// Obtain test run of BPMN
//		ProcessInstanceWithVariables processInstance = (ProcessInstanceWithVariables)processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
//
//		// Obtain the value of the weatherOK variable
//		boolean deliveryOK = (boolean) processInstance.getVariables().get("deliveryOK");
//		System.out.println("DeliveryOK: " + deliveryOK);
//			
//	    
//		// Obtain a reference to the current task
//		TaskAssert task = assertThat(processInstance).task();
//
//		if (deliveryOK) {
//				assertThat(processInstance).isWaitingAt("Activity_1l6f37h");
//				task.hasName("Fill shelves");
//				task.isNotAssigned();
//			} else {
//				assertThat(processInstance).isWaitingAt("Activity_0830fv5");
//				task.hasName("Create missing trays report");
//				task.isNotAssigned();
//			}
//
//		}
//
//
//}
