package com.green.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.green.spring.beans.service.DeliveryService;
import com.green.spring.beans.service.DominnosPizzaShop;
import com.green.spring.beans.service.ExpressDeliveryService;
import com.green.spring.beans.service.PizzaShop;

/**
 * Spring configuration class, for wiring beans
 * 
 * @author gaurav
 *
 */
@Configuration
public class Config {

	/**
	 * Preparing implementation of pizza shop
	 * 
	 * @return PizzaShop implementation with delivery service set
	 */
	@Bean
	public PizzaShop pizzaShop(){
		DominnosPizzaShop dominnosPizzaShop = new DominnosPizzaShop();
		dominnosPizzaShop.setDeliveryService(deliveryService());
		return dominnosPizzaShop;
	}
	
	/**
	 * Delivery service implementation.
	 * 
	 * @return Delivery service implementation.
	 */
	@Bean
	public DeliveryService deliveryService(){
		return new ExpressDeliveryService();
	}
}
