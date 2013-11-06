package com.green.spring.beans.service;

import org.springframework.stereotype.Service;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

/**
 * Simple quality pizza delivery system logic
 * 
 * @author gaurav
 *
 */
@Service("SimpleQualityDeliveryService")
public class SimpleQualityDeliveryService implements DeliveryService{

	@Override
	public void deliverPizza(Pizza pizza, Order order) {
		System.out.println(String.format("Simple Quality delivery pizza %s to %s",pizza.getName(),order.getAddress()));
	}


}
