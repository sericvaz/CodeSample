package com.jp.app.sales.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holder class to store the items successfully processed and record adjustments.
 * 
 * @author sharon
 *
 */
public class SalesStore {

	private Map<String, List<SalesItem>> saleItemCache = new HashMap<>();

	private Map<String, List<AdjustPriceRequest>> itemAdjustmentCache = new HashMap<>();

	public Map<String, List<SalesItem>> getSaleItemCache() {
		return saleItemCache;
	}

	public void setSaleItemCache(Map<String, List<SalesItem>> saleItemCache) {
		this.saleItemCache = saleItemCache;
	}

	public Map<String, List<AdjustPriceRequest>> getItemAdjustmentCache() {
		return itemAdjustmentCache;
	}

	public void setItemAdjustmentCache(Map<String, List<AdjustPriceRequest>> itemAdjustmentCache) {
		this.itemAdjustmentCache = itemAdjustmentCache;
	}

	public List<SalesItem> getItemsFromInventoryByType(String itemType) {
		if (saleItemCache.containsKey(itemType)) {
			return saleItemCache.get(itemType);
		}
		return Collections.emptyList();
	}

	public List<AdjustPriceRequest> getItemsFromAdjustmentLogByType(String itemType) {
		if (itemAdjustmentCache.containsKey(itemType)) {
			return itemAdjustmentCache.get(itemType);
		}
		return Collections.emptyList();
	}

	public int getItemsCountByType(String itemType) {
		if (saleItemCache.containsKey(itemType)) {
			return saleItemCache.get(itemType).size();
		}
		return 0;
	}

	public double getItemsTotalPriceByType(String itemType) {
		if (saleItemCache.containsKey(itemType)) {
			return saleItemCache.get(itemType).stream().mapToDouble(saleItem -> saleItem.getPrice()).sum();
		}
		return 0;
	}

	public void addToAdjustmentLog(AdjustPriceRequest adjustPriceRequest) {

		List<AdjustPriceRequest> adjustPriceRequestList = null;
		if (itemAdjustmentCache.containsKey(adjustPriceRequest.getItemType())) {
			adjustPriceRequestList = itemAdjustmentCache.get(adjustPriceRequest.getItemType());
		} else {
			adjustPriceRequestList = new ArrayList<>();
		}
		adjustPriceRequestList.add(adjustPriceRequest);
		itemAdjustmentCache.put(adjustPriceRequest.getItemType(), adjustPriceRequestList);

	}

	public void addItemToInventory(SalesItem saleItem) {

		List<SalesItem> storedSaleItems = null;
		if (saleItemCache.containsKey(saleItem.getType())) {
			storedSaleItems = saleItemCache.get(saleItem.getType());
		} else {
			storedSaleItems = new ArrayList<>();
		}
		storedSaleItems.add(saleItem);
		saleItemCache.put(saleItem.getType(), storedSaleItems);
	}

}
