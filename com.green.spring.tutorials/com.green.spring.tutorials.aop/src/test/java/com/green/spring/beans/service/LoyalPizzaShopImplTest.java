package com.green.spring.beans.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:aop.xml"})
public class LoyalPizzaShopImplTest {
	
	@Autowired private LoyalPizzaShop loyalPizzaShop;
	
	@Test
	public void itShouldMarkOrderAsLoyalForLoyalCustomer(){
		//given an order of loyal customer for pizza peppy paneer
		Order order = new Order("Peter",Pizza.PEPPY_PANEER, "Mumbai");
		//when pizza is ordered to shop
		loyalPizzaShop.orderPizza(order);
		//then order should be marked loyal
		Assert.assertTrue(order.getIsLoyal());
		
	}
	
	@Test
	public void itShouldNotMarkOrderAsLoyalForLoyalCustomer(){
		//given an order of loyal customer for pizza peppy paneer
		Order order = new Order("Nadal",Pizza.PEPPY_PANEER, "Mumbai");
		//when pizza is ordered to shop
		loyalPizzaShop.orderPizza(order);
		//then order should not be marked loyal
		Assert.assertFalse(order.getIsLoyal());
	}
	
	@Test
	public void itShouldRewardLoyalCustomerWith10PointsOnOver300PricePizza(){
		//given an order of loyal customer for pizza peppy paneer
		Order order = new Order("Peter",Pizza.ZIESTY_CHICKEN, "Mumbai");
		//when pizza is ordered to shop
		Pizza pizza = loyalPizzaShop.orderPizza(order);
		//then order should be marked loyal
		Assert.assertTrue(order.getIsLoyal());
		//pizza baked from shop should be ziesty chicken
		Assert.assertEquals(Pizza.ZIESTY_CHICKEN,pizza.getName());
		//10 reward points should be given
		Assert.assertEquals(10.0, order.getRewardPoints(), 1);
	}
	
	@Test
	public void itShouldNotRewardNonLoyalCustomerEvenIfOver300PricePizzaIsOrdered(){
		//given an order of loyal customer for pizza peppy paneer
		Order order = new Order("Nadal",Pizza.ZIESTY_CHICKEN, "Mumbai");
		//when pizza is ordered to shop
		Pizza pizza = loyalPizzaShop.orderPizza(order);
		//then order should not be marked loyal
		Assert.assertFalse(order.getIsLoyal());
		//pizza baked from shop should be ziesty chicken
		Assert.assertEquals(Pizza.ZIESTY_CHICKEN,pizza.getName());
		//0 reward points should be given
		Assert.assertEquals(0.0, order.getRewardPoints(), 1);
	}

}
