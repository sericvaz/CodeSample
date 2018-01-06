package com.jp.app.sales.srv;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jp.app.sales.dto.SalesRequest;
import com.jp.app.sales.dto.SalesStore;
import com.jp.app.sales.util.MessageAppLogger;
/**
 * 
 * @author sharon
 *
 */
public class SalesMessageProcessorImpl implements SalesMessageProcessor {

	private static Logger logger = MessageAppLogger.getLogger(SalesMessageProcessorImpl.class);

	private static final int SALE_ADJUSTMENT_THRESHOLD = 50;

	private static final int SALE_SUMMARY_THRESHOLD = 10;

	private int saleMessageCounter = 0;

	private RequestProcessingLookUp lookup = new RequestProcessingLookUp();

	private SalesStore salesStore = new SalesStore();

	private MessageParser salesMessageParser = new MessageParserImpl();

	private SalesReportService salesReportService = new SalesReportServiceImpl();

	@Override
	public void setSalesStore(SalesStore salesStore) {
		this.salesStore = salesStore;
	}

	@Override
	public void setSalesMessageParser(MessageParser salesMessageParser) {
		this.salesMessageParser = salesMessageParser;
	}

	@Override
	public void setLookup(RequestProcessingLookUp lookup) {
		this.lookup = lookup;
	}

	@Override
	public void setSalesReportService(SalesReportService salesReportService) {
		this.salesReportService = salesReportService;
	}

	/* (non-Javadoc)
	 * @see com.jp.app.sales.srv.SalesMessageProcessor#processMessage(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public void processMessage(String message) {
		
		saleMessageCounter++;
		logger.log(Level.FINEST,String.format("Message %d: %s \n",saleMessageCounter, message));
		
		SalesRequest salesRequest = salesMessageParser.getSalesRequestFromIncomingMessage(message);
		RequestProcessingService<SalesRequest> salesRequestProcessingService = (RequestProcessingService<SalesRequest>) lookup
				.getRequestProcessingService(salesRequest.getRequestType());

		salesRequestProcessingService.processRequest(salesRequest, salesStore);

		if ((saleMessageCounter % SALE_SUMMARY_THRESHOLD) == 0) {
			salesReportService.publisSalesReport(salesStore);
		}

		if ((saleMessageCounter % SALE_ADJUSTMENT_THRESHOLD) == 0) {
			logger.log(Level.FINE, "\n.............Processing paused to generate adjustment report.....\n");
			salesReportService.publishPriceAdjustmentReport(salesStore);
		}
	}

}
