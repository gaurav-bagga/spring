package com.green.spring.beans.service;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

public interface DeliveryService {

	public void deliverPizza(Pizza pizza,Order order);
}
