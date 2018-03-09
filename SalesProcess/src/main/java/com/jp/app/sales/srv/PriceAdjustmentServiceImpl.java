package com.jp.app.sales.srv;

import java.util.List;

import com.jp.app.sales.dto.AdjustPriceRequest;
import com.jp.app.sales.dto.AdjustmentOperator;
import com.jp.app.sales.dto.SalesItem;
import com.jp.app.sales.dto.SalesStore;

/**
 * Service to process price adjustment requests to the items that have been
 * recorded by the application.
 * 
 * @author sharon
 *
 */
public class PriceAdjustmentServiceImpl implements RequestProcessingService<AdjustPriceRequest> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jp.app.sales.srv.RequestProcessingService#processRequest(com.jp.app.
	 * sales.dto.SalesRequest, com.jp.app.sales.dto.SalesStore)
	 */
	@Override
	public void processRequest(AdjustPriceRequest adjustPriceRequest, SalesStore saleInventory) {

		List<SalesItem> saleItems = saleInventory.getItemsFromInventoryByType(adjustPriceRequest.getItemType());

		saleItems.forEach(saleItem -> {
			AdjustmentOperator operator = adjustPriceRequest.getAdjustmentOperator();
			saleItem.setPrice(operator.calculatePrice(saleItem.getPrice(), adjustPriceRequest.getPriceValueToAdjust()));
		});

		saleInventory.addToAdjustmentLog(adjustPriceRequest);
	}
}
