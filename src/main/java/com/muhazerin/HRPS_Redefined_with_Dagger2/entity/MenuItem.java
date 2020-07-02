package com.muhazerin.HRPS_Redefined_with_Dagger2.entity;

import java.io.Serializable;

/**
 * 
 * @author Koh Tong Liang
 * @author https://github.com/KohTongLiang
 *
 */

public class MenuItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private double price;
	
	public MenuItem(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
