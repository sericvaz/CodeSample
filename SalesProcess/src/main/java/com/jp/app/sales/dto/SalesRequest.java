package com.jp.app.sales.dto;

/**
 * @author sharon
 *
 */
public abstract class SalesRequest {
	
	private String itemType;
	
	private RequestType requestType;
	
	public SalesRequest(RequestType requestType){
		this.requestType=requestType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public RequestType getRequestType() {
		return requestType;
	}
	
}
