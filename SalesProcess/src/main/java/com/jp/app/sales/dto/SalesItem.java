package com.jp.app.sales.dto;

/**
 * POJO to store details after a request is succesfully processed.
 * 
 * @author sharon
 *
 */
public class SalesItem {
	
	private String type;
	
	private double price;
	
	public SalesItem(String type,double price){
		this.type=type;
		this.price=price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
