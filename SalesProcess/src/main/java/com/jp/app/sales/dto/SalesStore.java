package com.jp.app.sales.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holder class to store the items successfully processed and record
 * adjustments.
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
		return saleItemCache.getOrDefault(itemType, Collections.emptyList());
	}

	public List<AdjustPriceRequest> getItemsFromAdjustmentLogByType(String itemType) {

		return itemAdjustmentCache.getOrDefault(itemType, Collections.emptyList());
	}

	public int getItemsCountByType(String itemType) {
		return saleItemCache.getOrDefault(itemType, Collections.emptyList()).size();
	}

	public double getItemsTotalPriceByType(String itemType) {

		return saleItemCache.getOrDefault(itemType, Collections.emptyList()).stream()
				.mapToDouble(saleItem -> saleItem.getPrice()).sum();
	}

	public void addToAdjustmentLog(AdjustPriceRequest adjustPriceRequest) {

		itemAdjustmentCache.putIfAbsent(adjustPriceRequest.getItemType(), new ArrayList<>());
		itemAdjustmentCache.get(adjustPriceRequest.getItemType()).add(adjustPriceRequest);

	}

	public void addItemToInventory(SalesItem saleItem) {
		saleItemCache.putIfAbsent(saleItem.getType(), new ArrayList<>());
		saleItemCache.get(saleItem.getType()).add(saleItem);
	}

}
