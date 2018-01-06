package com.jp.app.sales.srv;

import com.jp.app.sales.dto.SalesStore;

/**
 * Reporting service to publish the sales and price adjustment reports.
 * 
 * @author sharon
 *
 */
public interface SalesReportService {

	/**
	 * Publishes the sales reports for each item type recorded in the
	 * SalesStore.
	 * 
	 * @param saleStore
	 */
	void publisSalesReport(SalesStore saleStore);

	/**
	 * Publishes the price adjustments reports for each item type recorded in
	 * the SalesStore.
	 * 
	 * @param saleStore
	 */
	void publishPriceAdjustmentReport(SalesStore saleStore);
}
