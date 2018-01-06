package com.jp.app.sales.dto;

import com.jp.app.sales.util.MessageParserUtil;

/**
 * POJO for adding new sales requests.
 * 
 * @author sharon
 *
 */
public class NewSaleRequest extends SalesRequest {

	private int quantity;
	
	private double price;
	
	public NewSaleRequest() {
		super(RequestType.ADD_ITEM);
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setPrice(String price) {
		this.price = Double.parseDouble(MessageParserUtil.stripTrailingCurrency(price));
	}


}
