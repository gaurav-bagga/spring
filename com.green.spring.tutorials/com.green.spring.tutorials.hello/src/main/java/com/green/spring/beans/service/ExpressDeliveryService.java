package com.green.spring.beans.service;

import org.springframework.stereotype.Service;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

/**
 * Express pizza delivery system logic.
 *  
 * @author gaurav
 *
 */
@Service("ExpressDeliveryService")
public class ExpressDeliveryService implements DeliveryService{

	@Override
	public void deliverPizza(Pizza pizza, Order order) {
		System.out.println(String.format("Express delivery pizza %s to %s",pizza.getName(),order.getAddress()));
	}


}
