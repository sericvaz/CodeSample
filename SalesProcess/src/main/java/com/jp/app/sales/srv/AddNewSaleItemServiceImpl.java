package com.jp.app.sales.srv;

import com.jp.app.sales.dto.NewSaleRequest;
import com.jp.app.sales.dto.SalesStore;
import com.jp.app.sales.dto.SalesItem;

/**
 * Service to process the new sales requests, transform them to salesitems and
 * add them to the sale store.
 * 
 * @author sharon
 *
 */
public class AddNewSaleItemServiceImpl implements RequestProcessingService<NewSaleRequest> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jp.app.sales.srv.RequestProcessingService#processRequest(com.jp.app.
	 * sales.dto.SalesRequest, com.jp.app.sales.dto.SalesStore)
	 */
	@Override
	public void processRequest(NewSaleRequest addNewItemRequest, SalesStore saleInventory) {

		for (int i = 0; i < addNewItemRequest.getQuantity(); i++) {
			saleInventory
					.addItemToInventory(new SalesItem(addNewItemRequest.getItemType(), addNewItemRequest.getPrice()));
		}
	}

}
