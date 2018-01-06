package com.jp.app.sales.srv;

import com.jp.app.sales.dto.SalesStore;

/**
 * Orchestrates calls to different services to process the incoming message.
 * 
 * @author sharon
 *
 */
public interface SalesMessageProcessor {
	
	void processMessage(String message);

	void setSalesReportService(SalesReportService salesReportService);

	void setLookup(RequestProcessingLookUp lookup);

	void setSalesMessageParser(MessageParser salesMessageParser);

	void setSalesStore(SalesStore salesStore);

}
