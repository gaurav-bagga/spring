package com.green.spring.beans.service;

import java.util.HashMap;
import java.util.Map;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

/**
 * The pizza shop, which processes pizza orders 
 * 
 * @author gaurav
 *
 */
public class LoyalPizzaShopImpl implements LoyalPizzaShop{

	private static Map<String, Pizza> pizzas = new HashMap<>();
	
	static {
		pizzas.put(Pizza.PEPPY_PANEER, new Pizza(Pizza.PEPPY_PANEER,245.0f));
		pizzas.put(Pizza.ZIESTY_CHICKEN, new Pizza(Pizza.ZIESTY_CHICKEN,345.0f));
	}
	
	/**
	 * Bakes Pizza out of orders sent
	 */
	@Override
	public Pizza orderPizza(Order order) {
		//before
		return pizzas.get(order.getPizzaName());
		//after re
	}


}
