package com.green.spring.beans.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.green.spring.beans.di.Car;

/**
 * This main method class is to demonstrate wiring using xml file
 * 
 * @author gaurav
 *
 */
public class BeansMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		
		Car car = applicationContext.getBean(Car.class);
		
		car.drive();
		
		((ConfigurableApplicationContext)applicationContext).close();
	}
}
