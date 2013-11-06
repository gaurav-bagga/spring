package com.green.spring.beans.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.green.spring.beans.di.autowire.Singer;

/**
 * This main method class is to demonstrate wiring using xml file
 * 
 * @author gaurav
 *
 */
public class AutowireMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("autowire.xml");
		
		Singer singerByType = applicationContext.getBean("singerByType", Singer.class);
		singerByType.play();
		
		Singer singerByName = applicationContext.getBean("singerByName", Singer.class);
		singerByName.play();
		
		Singer singerByConstructor = applicationContext.getBean("singerByConstructor", Singer.class);
		singerByConstructor.play();
		
		((ConfigurableApplicationContext)applicationContext).close();
	}
}
