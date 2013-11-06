package com.green.spring.beans.entity;

/**
 * Bean responsible for encapsulating Pizza order
 * 
 * @author gaurav
 *
 */
public class Order {

	private String pizzaName;
	private String address;
	
	public Order(String pizzaName,String address) {
		this.pizzaName = pizzaName;
		this.address = address;
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
	
	
}
