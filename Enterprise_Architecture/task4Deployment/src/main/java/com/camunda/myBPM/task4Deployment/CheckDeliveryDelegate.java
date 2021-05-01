package com.camunda.myBPM.task4Deployment;

import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckDeliveryDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Random rando = new Random(); 
		execution.setVariable("deliveryOK", rando.nextBoolean());

	}

}
