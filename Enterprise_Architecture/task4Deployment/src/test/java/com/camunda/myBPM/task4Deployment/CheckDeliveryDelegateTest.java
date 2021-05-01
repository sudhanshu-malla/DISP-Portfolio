package com.camunda.myBPM.task4Deployment;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.Test;

public class CheckDeliveryDelegateTest {

	@Test
	public void verifyThatTheDelegateSetsVariable() {

		// Class set up under test
		CheckDeliveryDelegate delegate = new CheckDeliveryDelegate();

		// Set up mock of Camunda execution environment
		DelegateExecution mockExecution = mock(DelegateExecution.class);
		{

			// run the delegate behaviour
			try {
				delegate.execute(mockExecution);
			} catch (Exception e) {
				e.printStackTrace();
				fail("throw exception: " + e.getMessage());
			}
			// Now verify that the mock was called.
			verify(mockExecution, times(1)).setVariable(eq("deliveryOK"), any(Boolean.class));
		}
	}
}
