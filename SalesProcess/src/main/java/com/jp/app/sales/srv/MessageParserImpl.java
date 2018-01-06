package com.jp.app.sales.srv;

import com.jp.app.sales.dto.AdjustPriceRequest;
import com.jp.app.sales.dto.AdjustmentOperator;
import com.jp.app.sales.dto.NewSaleRequest;
import com.jp.app.sales.dto.SalesRequest;
import com.jp.app.sales.util.MessageParserUtil;

/**
 * @author sharon
 *
 */
public class MessageParserImpl implements MessageParser {

	private static final String PATTERN_SPACE = "\\s+";
	private static final String EACH = "each";
	private static final String OF = "of";
	private static final String SALES = "sales";
	private static final int ONE = 1;
	private static final String AT = "at";
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jp.app.sales.srv.MessageParser#getSalesRequestFromIncomingMessage(
	 * java.lang.String)
	 */
	@Override
	public <T extends SalesRequest> T getSalesRequestFromIncomingMessage(String message) {

		if (MessageParserUtil.isNullOrEmpty(message)) {
			throw new RuntimeException("Empty input message..." + message);
		}

		String[] messagetokens = message.split(PATTERN_SPACE);
		if (isAdjustmentRequest(messagetokens)) {
			return createAdjustPriceRequestObject(messagetokens[2], messagetokens[1], messagetokens[0]);
		} else {
			if (isSingleSalesRequest(messagetokens)) {
				return createNewSalesRequestObject(messagetokens[0], messagetokens[2], ONE);
			} else if (isMultipleSalesRequest(messagetokens)) {
				return createNewSalesRequestObject(messagetokens[3], messagetokens[5],
						Integer.parseInt(messagetokens[0]));
			} else {
				throw new RuntimeException("Unable to process input message, format not supported:" + message);
			}
		}
	}

	/**
	 * Determines if message is of an adjustment request type <br>
	 * Format: <operation> <objectPrice>p <objectType></br>
	 * 
	 * Message Type 3 – contains the details of a sale and an adjustment
	 * operation to be applied to all stored sales of this product type.
	 * Operations can be add, subtract, or multiply e.g Add 20p apples would
	 * instruct your application to add 20p to each sale of apples you have
	 * recorded.
	 * 
	 * for e.g. 'Add 20p apples'
	 * 
	 * @param messagetokens
	 * @return
	 */
	private boolean isAdjustmentRequest(String[] messagetokens) {
		return AdjustmentOperator.containsValue(messagetokens[0]);
	}

	/**
	 * Determines if message is of a single request type <br>
	 * Format: <objectType> at <objectPrice>p </br>
	 * 
	 * Message Type 1 – contains the details of 1 sale E.g apple at 10p
	 * 
	 * for e.g. 'apple at 10p'
	 * 
	 * @param messagetokens
	 * @return
	 */
	private boolean isSingleSalesRequest(String[] messagetokens) {
		return messagetokens.length == 3 && messagetokens[1].equals(AT);
	}

	/**
	 * Determines if message is of a multiple request type <br>
	 * Format: <quantity> sales of <objectTypes> at <objectPrice>p each. </br>
	 * 
	 * Message Type 2 – contains the details of a sale and the number of
	 * occurrences of that sale. E.g 20 sales of apples at 10p each.
	 * 
	 * for e.g. '20 sales of apples at 10p each'
	 * 
	 * @param messagetokens
	 * @return
	 */
	private boolean isMultipleSalesRequest(String[] messagetokens) {
		return messagetokens.length == 7 && SALES.equals(messagetokens[1]) && OF.equals(messagetokens[2])
				&& AT.equals(messagetokens[4]) && EACH.equals(messagetokens[6]);
	}

	/**
	 * Creates NewSalesRequest Object based on the input.
	 * 
	 * @param type
	 * @param amount
	 * @param quantity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T extends SalesRequest> T createNewSalesRequestObject(String type, String amount, int quantity) {
		NewSaleRequest salesRequest = new NewSaleRequest();
		salesRequest.setItemType(MessageParserUtil.getSingluarWordFromPluralForm(type));
		salesRequest.setQuantity(quantity);
		salesRequest.setPrice(amount);
		return (T) salesRequest;
	}

	/**
	 * Creates AdjustPriceRequest Object based on the input.
	 * 
	 * @param type
	 * @param amount
	 * @param adjustmentOperation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T extends SalesRequest> T createAdjustPriceRequestObject(String type, String amount,
			String adjustmentOperation) {
		AdjustPriceRequest salesRequest = new AdjustPriceRequest();
		salesRequest.setItemType(MessageParserUtil.getSingluarWordFromPluralForm(type));
		salesRequest.setAdjustmentOperator(adjustmentOperation);
		salesRequest.setPriceValueToAdjust(amount);
		return (T) salesRequest;
	}

}
