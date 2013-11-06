package com.green.spring.beans.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;
import com.green.spring.beans.service.PizzaShop;

/**
 * This main method class is to demonstrate wiring using annotations
 * 
 * @author gaurav
 *
 */
public class AnnotationMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation.xml");
		
		Order order = new Order(Pizza.PEPPY_PANEER,"Mumbai");
		
		PizzaShop pizzaShop = applicationContext.getBean(PizzaShop.class);
		pizzaShop.orderPizza(order);
		
		((ConfigurableApplicationContext)applicationContext).close();
	}
}
