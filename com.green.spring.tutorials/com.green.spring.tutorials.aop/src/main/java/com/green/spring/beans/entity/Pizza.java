package com.green.spring.beans.entity;

/**
 * The pizza pojo
 * 
 * @author gaurav
 *
 */
public class Pizza {
	
	public static final String PEPPY_PANEER = "Peppy Paneer";
	public static final String ZIESTY_CHICKEN = "Ziesty Chicken";

	private String name;
	private Float price;
	
	public Pizza(String name,Float price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	
}
