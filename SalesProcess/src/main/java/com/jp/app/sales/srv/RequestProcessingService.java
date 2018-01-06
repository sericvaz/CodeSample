package com.jp.app.sales.srv;

import com.jp.app.sales.dto.SalesStore;
import com.jp.app.sales.dto.SalesRequest;

/**
 * Service to process the incoming message requests which can be either of the
 * below types:
 * <ul>
 * <li>Adding new items to the sales store.</li>
 * <li>Modifying the price of previously stored items in the sales store.</li>
 * </ul>
 * 
 * @author sharon
 *
 * @param <T extends SalesRequest>
 */
public interface RequestProcessingService<T extends SalesRequest> {

	/**
	 * Processes the saleRequest based on the input type of request.
	 * 
	 * @param saleRequest
	 * @param salesStore
	 */
	void processRequest(T saleRequest, SalesStore salesStore);

}
