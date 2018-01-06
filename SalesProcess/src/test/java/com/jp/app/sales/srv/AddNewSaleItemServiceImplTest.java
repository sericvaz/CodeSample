package com.jp.app.sales.srv;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jp.app.sales.dto.NewSaleRequest;
import com.jp.app.sales.dto.SalesItem;
import com.jp.app.sales.dto.SalesStore;

public class AddNewSaleItemServiceImplTest {
	
	private RequestProcessingService<NewSaleRequest> requestProcessingService;

	@Before
	public void setUp() throws Exception {
		requestProcessingService=new AddNewSaleItemServiceImpl();
	}

	@Test
	public void testProcessSingleRequest() {
		SalesStore salesStore=new SalesStore();
		NewSaleRequest newSaleRequest=mockNewSaleRequest("toy",10,1);
		requestProcessingService.processRequest(newSaleRequest, salesStore);
		assertEquals(1, salesStore.getItemsCountByType("toy"));
	}
	
	@Test
	public void testProcessMultipleRequest() {
		SalesStore salesStore=new SalesStore();
		salesStore.addItemToInventory(new SalesItem("toy", 12));
		NewSaleRequest newSaleRequest=mockNewSaleRequest("toy",10,3);
		NewSaleRequest newSaleRequest1=mockNewSaleRequest("socks",10,1);
		requestProcessingService.processRequest(newSaleRequest, salesStore);
		requestProcessingService.processRequest(newSaleRequest1, salesStore);
		assertEquals(4, salesStore.getItemsCountByType("toy"));
		assertEquals(1, salesStore.getItemsCountByType("socks"));
	}
	
	private NewSaleRequest mockNewSaleRequest(String type, double price,int quantity){
		NewSaleRequest newSaleRequest=new NewSaleRequest();
		newSaleRequest.setPrice(price);
		newSaleRequest.setQuantity(quantity);
		newSaleRequest.setItemType(type);
		return newSaleRequest;
		
	}

}
