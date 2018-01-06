package com.jp.app.sales.dto;

import com.jp.app.sales.util.MessageParserUtil;

/**
 * POJO for sales requests of type price adjustment.
 * 
 * @author sharon
 *
 */
public class AdjustPriceRequest extends SalesRequest {

	private double priceValueToAdjust;

	private AdjustmentOperator adjustmentOperator;
	
	private boolean adjustmentReported;

	public AdjustPriceRequest() {
		super(RequestType.ADJUST_PRICE);
	}

	public double getPriceValueToAdjust() {
		return priceValueToAdjust;
	}

	public void setPriceValueToAdjust(double priceValueToAdjust) {
		this.priceValueToAdjust = priceValueToAdjust;
	}

	public void setPriceValueToAdjust(String priceValueToAdjust) {
		this.priceValueToAdjust = Double.parseDouble(MessageParserUtil.stripTrailingCurrency(priceValueToAdjust));
	}

	public AdjustmentOperator getAdjustmentOperator() {
		return adjustmentOperator;
	}

	public void setAdjustmentOperator(String adjustmentOperator) {
		if (AdjustmentOperator.containsValue(adjustmentOperator.toUpperCase())) {
			this.adjustmentOperator = AdjustmentOperator.valueOf(adjustmentOperator.toUpperCase());
		}else{
			throw new UnsupportedOperationException("Adjustment Operator not supported");
		}
	}

	public boolean isAdjustmentReported() {
		return adjustmentReported;
	}

	public void setAdjustmentReported(boolean adjustmentReported) {
		this.adjustmentReported = adjustmentReported;
	}

}
