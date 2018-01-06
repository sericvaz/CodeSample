package com.jp.app.sales.srv;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jp.app.sales.dto.AdjustPriceRequest;
import com.jp.app.sales.dto.SalesItem;
import com.jp.app.sales.dto.SalesStore;
import com.jp.app.sales.util.MessageAppLogger;

/**
 * @author sharon
 *
 */
public class SalesReportServiceImpl implements SalesReportService {

	private static Logger logger = MessageAppLogger.getLogger(SalesReportServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.jp.app.sales.srv.SalesReportService#publishItemSaleReport(com.jp.app.sales.dto.SalesStore)
	 */
	@Override
	public void publishSalesReport(SalesStore saleStore) {
		logger.log(Level.FINE, "\n===================================\n");
		saleStore.getSaleItemCache().forEach((k, v) -> printSalesReportForItemType(k, v, saleStore));
		logger.log(Level.FINE, "\n===================================\n");
	}

	/* (non-Javadoc)
	 * @see com.jp.app.sales.srv.SalesReportService#publishItemAdjustmentReport(com.jp.app.sales.dto.SalesStore)
	 */
	@Override
	public void publishPriceAdjustmentReport(SalesStore saleStore) {
		logger.log(Level.FINE, "\n==== Adjustment Report ============");
		saleStore.getItemAdjustmentCache().forEach((k, v) -> printAdjustmentReportForItemType(k, v, saleStore));
		logger.log(Level.FINE, "\n===================================\n");

	}

	/**
	 * Prints sales details for a particular item type.
	 * 
	 * @param itemType
	 * @param saleItems
	 * @param saleStore
	 */
	private void printSalesReportForItemType(String itemType, List<SalesItem> saleItems, SalesStore saleStore) {
		int totalItemCount = saleStore.getItemsCountByType(itemType);
		double totalPrice = saleStore.getItemsTotalPriceByType(itemType);
		logger.log(Level.FINE,
				String.format("Type:%s ItemCount:%d  Total Price:%f \n", itemType, totalItemCount, totalPrice));
	}

	/**
	 * Prints price adjustment details for a particular item type.
	 * 
	 * @param itemType
	 * @param saleItems
	 * @param saleStore
	 */
	private void printAdjustmentReportForItemType(String itemType, List<AdjustPriceRequest> saleItems,
			SalesStore saleStore) {
		List<AdjustPriceRequest> adjustmentLog = saleStore.getItemsFromAdjustmentLogByType(itemType);
		logger.log(Level.FINE, String.format("\n\nType:%s Adjustments done:", itemType));

		adjustmentLog.stream().filter(adjustLog->!adjustLog.isAdjustmentReported()).forEach(adjustLog -> {
			adjustLog.setAdjustmentReported(true);
			logger.log(Level.FINE, String.format("\nValue changed by %f  after operation performed to '%s'",
					adjustLog.getPriceValueToAdjust(), adjustLog.getAdjustmentOperator().name()));
		});
	}

}
