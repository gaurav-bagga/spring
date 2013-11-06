package com.green.spring.beans.entity;

/**
 * Bean responsible for encapsulating Pizza order
 * 
 * @author gaurav
 *
 */
public class Order {

	private String customerId;
	private String pizzaName;
	private String address;
	private Boolean isLoyal;
	private Float rewardPoints;
	
	public Order(String customerId,String pizzaName,String address) {
		this.customerId = customerId;
		this.pizzaName = pizzaName;
		this.address = address;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getIsLoyal() {
		return isLoyal;
	}
	public void setIsLoyal(Boolean isLoyal) {
		this.isLoyal = isLoyal;
	}
	public Float getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Float rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
}
