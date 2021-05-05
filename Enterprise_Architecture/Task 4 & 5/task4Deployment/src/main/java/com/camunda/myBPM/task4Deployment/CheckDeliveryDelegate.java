package com.camunda.myBPM.task4Deployment;

import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

//Utilises Java Delegate interface
//this class is implemented by Check delivery service task in BPM
public class CheckDeliveryDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// generates a random value
		Random rando = new Random(); 
		// sets a random variable
		execution.setVariable("deliveryOK", rando.nextBoolean());

	}

}
