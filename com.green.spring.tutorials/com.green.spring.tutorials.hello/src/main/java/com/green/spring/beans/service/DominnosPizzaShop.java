package com.green.spring.beans.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

/**
 * The pizza shop responsible for baking pizza as per order given
 * and deliver it to customer
 * 
 * @author gaurav
 *
 */
@Service
public class DominnosPizzaShop implements PizzaShop{

	@Autowired
	//@Qualifier("SimpleQualityDeliveryService")
	private DeliveryService deliveryService;
	
	@PostConstruct
	public void init(){
		System.out.println("In Init");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("In Destroy");
	}
	
	private Pizza bakePizza(String pizzaName){
		return new Pizza(pizzaName);
	}


	@Override
	public void orderPizza(Order order) {
		Pizza pizza = bakePizza(order.getPizzaName());
		deliveryService.deliverPizza(pizza, order);
	}


	public DeliveryService getDeliveryService() {
		return deliveryService;
	}


	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
	
	

}
