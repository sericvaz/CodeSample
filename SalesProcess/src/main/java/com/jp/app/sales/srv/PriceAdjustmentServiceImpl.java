package com.jp.app.sales.srv;

import java.util.List;

import com.jp.app.sales.dto.AdjustPriceRequest;
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
			saleItem.setPrice(getAdjustedPrice(saleItem.getPrice(), adjustPriceRequest));
		});

		saleInventory.addToAdjustmentLog(adjustPriceRequest);
	}

	/**
	 * Gets the adjusted price based on the adjustment operation to be
	 * performed.
	 * 
	 * @param currentItemPrice
	 * @param adjustPriceRequest
	 * @return
	 */
	private double getAdjustedPrice(double currentItemPrice, AdjustPriceRequest adjustPriceRequest) {

		double adjustedPrice = 0;
		switch (adjustPriceRequest.getAdjustmentOperator()) {
		case ADD:
			adjustedPrice = currentItemPrice + adjustPriceRequest.getPriceValueToAdjust();
			break;
		case SUBTRACT:
			adjustedPrice = currentItemPrice - adjustPriceRequest.getPriceValueToAdjust();
			break;
		case MULTIPLY:
			adjustedPrice = currentItemPrice * adjustPriceRequest.getPriceValueToAdjust();
			break;
		default:
			throw new UnsupportedOperationException("Adjustment Operator not supported");
		}
		return adjustedPrice;
	}

}
