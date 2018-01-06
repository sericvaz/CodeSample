package com.jp.app.sales.srv;

import com.jp.app.sales.dto.RequestType;

/**
 * Business Lookup service to get the instance based on the request type.
 * 
 * @author sharon
 *
 */
public class RequestProcessingLookUp {

	public RequestProcessingService<?> getRequestProcessingService(RequestType requestType) {

		if (RequestType.ADD_ITEM.equals(requestType)) {
			return new AddNewSaleItemServiceImpl();
		} else if (RequestType.ADJUST_PRICE.equals(requestType)) {
			return new PriceAdjustmentServiceImpl();
		}
		return null;
	}

}
