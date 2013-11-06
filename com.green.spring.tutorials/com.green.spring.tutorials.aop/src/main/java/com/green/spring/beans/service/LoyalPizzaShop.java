package com.green.spring.beans.service;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

public interface LoyalPizzaShop {

	public Pizza orderPizza(Order order);
}
