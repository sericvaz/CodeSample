package com.jp.app.sales.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum for operations/instructions encountered in the incoming message.
 * 
 * @author sharon
 *
 */
public enum AdjustmentOperator {

	ADD, SUBTRACT, MULTIPLY;
	
	private static List<String> getValuesAsList() {
		return Stream.of(values()).map(Enum::name).collect(Collectors.toList());
	}
	
	public static boolean containsValue(String name) {
		return getValuesAsList().contains(name.toUpperCase());
	}

}
