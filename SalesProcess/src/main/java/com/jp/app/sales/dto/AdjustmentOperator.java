package com.jp.app.sales.dto;

import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum for operations/instructions encountered in the incoming message.
 * 
 * @author sharon
 *
 */
public enum AdjustmentOperator {

	ADD((n1, n2) -> n1 + n2), SUBTRACT((n1, n2) -> n1 - n2), MULTIPLY((n1, n2) -> n1 * n2);

	private DoubleBinaryOperator operator;

	private static List<String> getValuesAsList() {
		return Stream.of(values()).map(Enum::name).collect(Collectors.toList());
	}

	private AdjustmentOperator(DoubleBinaryOperator operator) {
		this.operator = operator;
	}

	public static boolean containsValue(String name) {
		return getValuesAsList().contains(name.toUpperCase());
	}

	public Double calculatePrice(double left, double right) {
		return operator.applyAsDouble(left, right);
	}


}
