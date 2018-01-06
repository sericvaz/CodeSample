package com.jp.app.sales.srv;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jp.app.sales.dto.AdjustPriceRequest;
import com.jp.app.sales.dto.SalesItem;
import com.jp.app.sales.dto.SalesStore;

public class PriceAdjustmentServiceImplTest {
	
	private RequestProcessingService<AdjustPriceRequest> requestProcessingService;
	
	@Before
	public void setUp() throws Exception {
		requestProcessingService=new PriceAdjustmentServiceImpl();
	}

	@Test
	public void testProcessAddRequest() {
		SalesStore salesStore=new SalesStore();
		salesStore.addItemToInventory(new SalesItem("apple", 10));
		salesStore.addItemToInventory(new SalesItem("apple", 40));
		salesStore.addItemToInventory(new SalesItem("apple", 20));
		
		AdjustPriceRequest adjustPriceRequest=mockSaleRequest("apple",20,"ADD");
		requestProcessingService.processRequest(adjustPriceRequest, salesStore);
		
		List<AdjustPriceRequest> saleLog=salesStore.getItemsFromAdjustmentLogByType("apple");
		Assert.assertEquals(1, saleLog.size());
		
		List<Double> saleItems=salesStore.getItemsFromInventoryByType("apple").stream().map(SalesItem::getPrice).collect(Collectors.toList());
		Assert.assertTrue(saleItems.containsAll(Arrays.asList(30d,60d,40d)));
		
	}
	
	@Test
	public void testProcessSubstractRequest() {
		SalesStore salesStore=new SalesStore();
		salesStore.addItemToInventory(new SalesItem("chair", 80));
		salesStore.addItemToInventory(new SalesItem("chair", 40));
		salesStore.addItemToInventory(new SalesItem("chair", 60));
		
		AdjustPriceRequest adjustPriceRequest=mockSaleRequest("chair",20,"Subtract");
		requestProcessingService.processRequest(adjustPriceRequest, salesStore);
		
		List<AdjustPriceRequest> saleLog=salesStore.getItemsFromAdjustmentLogByType("chair");
		Assert.assertEquals(1, saleLog.size());
		
		List<Double> saleItems=salesStore.getItemsFromInventoryByType("chair").stream().map(SalesItem::getPrice).collect(Collectors.toList());
		Assert.assertTrue(saleItems.containsAll(Arrays.asList(20d,60d,40d)));
		
	}
	
	@Test
	public void testProcessMultiplyRequest() {
		SalesStore salesStore=new SalesStore();
		salesStore.addItemToInventory(new SalesItem("shoe", 10));
		salesStore.addItemToInventory(new SalesItem("shoe", 40));
		
		AdjustPriceRequest adjustPriceRequest=mockSaleRequest("shoe",2,"Multiply");
		requestProcessingService.processRequest(adjustPriceRequest, salesStore);
		
		List<AdjustPriceRequest> saleLog=salesStore.getItemsFromAdjustmentLogByType("shoe");
		Assert.assertEquals(1, saleLog.size());
		
		List<Double> saleItems=salesStore.getItemsFromInventoryByType("shoe").stream().map(SalesItem::getPrice).collect(Collectors.toList());
		Assert.assertTrue(saleItems.containsAll(Arrays.asList(20d,80d)));
		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testProcessInvalidRequest() {
		SalesStore salesStore=new SalesStore();
		salesStore.addItemToInventory(new SalesItem("shoe", 10));
		salesStore.addItemToInventory(new SalesItem("shoe", 40));
		
		AdjustPriceRequest adjustPriceRequest=mockSaleRequest("shoe",2,"xdxdxd");
		requestProcessingService.processRequest(adjustPriceRequest, salesStore);
	}
	
	
	private AdjustPriceRequest mockSaleRequest(String type,double priceValueToAdjust,String adjustmentOperator){
		AdjustPriceRequest adjustPriceRequest=new AdjustPriceRequest();
		adjustPriceRequest.setItemType(type);
		adjustPriceRequest.setAdjustmentOperator(adjustmentOperator);
		adjustPriceRequest.setPriceValueToAdjust(priceValueToAdjust);
		return adjustPriceRequest;
		
	}

}
