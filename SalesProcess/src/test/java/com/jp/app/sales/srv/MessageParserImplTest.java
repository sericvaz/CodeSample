package com.jp.app.sales.srv;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jp.app.sales.dto.AdjustPriceRequest;
import com.jp.app.sales.dto.NewSaleRequest;
import com.jp.app.sales.srv.MessageParser;
import com.jp.app.sales.srv.MessageParserImpl;

public class MessageParserImplTest {

	private MessageParser messageParser;

	@Before
	public void setUp() throws Exception {
		messageParser = new MessageParserImpl();
	}

	@Test
	public void testSingleSaleRequestMessage() {
		NewSaleRequest newSaleRequest = null;
		newSaleRequest = messageParser.getSalesRequestFromIncomingMessage("apple at 10p");
		Assert.assertNotNull(newSaleRequest);
		Assert.assertSame(newSaleRequest.getClass(), NewSaleRequest.class);

		newSaleRequest = messageParser.getSalesRequestFromIncomingMessage("orange at 10p");
		Assert.assertNotNull(newSaleRequest);
		Assert.assertSame(newSaleRequest.getClass(), NewSaleRequest.class);

		newSaleRequest = messageParser.getSalesRequestFromIncomingMessage("table at 10p");
		Assert.assertNotNull(newSaleRequest);
		Assert.assertSame(newSaleRequest.getClass(), NewSaleRequest.class);

	}

	@Test
	public void testSingleSaleRequestMultipleMessage() {
		NewSaleRequest newSaleRequest = null;

		newSaleRequest = messageParser.getSalesRequestFromIncomingMessage("20 sales of bananas at 10p each");
		Assert.assertNotNull(newSaleRequest);
		Assert.assertSame(newSaleRequest.getClass(), NewSaleRequest.class);
		
		newSaleRequest = messageParser.getSalesRequestFromIncomingMessage("50 sales of chairs at 10p each");
		Assert.assertNotNull(newSaleRequest);
		Assert.assertSame(newSaleRequest.getClass(), NewSaleRequest.class);
	}

	@Test(expected = RuntimeException.class)
	public void testSingleSaleRequestInvalidMessage() {
		messageParser.getSalesRequestFromIncomingMessage("Invalid Message");
	}

	@Test
	public void testAdjustPriceRequestMessage() {
		AdjustPriceRequest adjustPriceRequest = messageParser.getSalesRequestFromIncomingMessage("Add 5p pineapples");
		Assert.assertNotNull(adjustPriceRequest);
		Assert.assertSame(adjustPriceRequest.getClass(), AdjustPriceRequest.class);
	}

}
