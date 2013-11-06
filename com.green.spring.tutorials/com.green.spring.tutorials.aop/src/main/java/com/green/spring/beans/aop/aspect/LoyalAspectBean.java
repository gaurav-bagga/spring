package com.green.spring.beans.aop.aspect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

/**
 * Bean responsible for handling Loyalty Program aspects
 * The main concern of our pizza shop is to bake pizza
 * 
 * @author gaurav
 *
 */
public class LoyalAspectBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoyalAspectBean.class);
	
	private Set<String> loyal = new HashSet<>(Arrays.asList("Peter","Jim","Roger"));
	
	/**
	 * On the score earned historically we look up database to mark
	 * order for loyal customers.
	 * 
	 * @param joinPoint
	 */
	public void checkIsLoyal(JoinPoint joinPoint){
		LOGGER.info("The arguments passed are {}",Arrays.toString(joinPoint.getArgs()));
		Order order = (Order) joinPoint.getArgs()[0];
		order.setIsLoyal(loyal.contains(order.getCustomerId()));
	}
	
	/**
	 * To loyal customers order over of 300, 10 reward points are given
	 * 
	 * @param joinPoint
	 * @param retVal
	 */
	public void addRewardPoint(JoinPoint joinPoint,Object retVal){
		LOGGER.info("The pizza found as {}",retVal);
		Pizza pizza = (Pizza) retVal;
		Order order = (Order) joinPoint.getArgs()[0];
		if(pizza.getPrice() > 300.0 && order.getIsLoyal()){
			LOGGER.info("You have earned 10 points !!!!!!!");
			order.setRewardPoints(10.0f);
		}else{
			LOGGER.info("You have earned 0 points !!!!!!!");
			order.setRewardPoints(0.0f);
		}
	}
}
