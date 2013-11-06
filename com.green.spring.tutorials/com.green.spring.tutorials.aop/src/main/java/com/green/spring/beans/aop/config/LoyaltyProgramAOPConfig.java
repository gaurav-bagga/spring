package com.green.spring.beans.aop.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.green.spring.beans.aop.aspect.LoyalAspectBean;
import com.green.spring.beans.entity.Order;
import com.green.spring.beans.entity.Pizza;

@Aspect
@Component
public class LoyaltyProgramAOPConfig {
private static final Logger LOGGER = LoggerFactory.getLogger(LoyalAspectBean.class);
	
	private Set<String> loyal = new HashSet<>(Arrays.asList("Peter","Jim","Roger"));
	
	@Pointcut("execution(* com.green.spring.beans.service.LoyalPizzaShopImpl.orderPizza(..))")
	public void pointcut(){}
	
	/**
	 * On the score earned historically we look up database to mark
	 * order for loyal customers.
	 * 
	 * @param joinPoint
	 */
	@Before("pointcut()")
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
	@AfterReturning(pointcut="pointcut()",returning="retVal")
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
