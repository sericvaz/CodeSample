package com.jp.app.sales.util;

import org.apache.lucene.analysis.en.EnglishMinimalStemmer;

/**
 * Utility class to handle String manipulations.
 * 
 * @author sharon
 *
 */
public class MessageParserUtil {
	
	/**
	 * Checks if a given string is null or empty.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullOrEmpty(String value){
		return value == null || value.trim().isEmpty();
	}
	
	/**
	 * Replaces occurrence of p from the given input.
	 * @param value
	 * @return
	 */
	public static String stripTrailingCurrency(String value){
		return value.replace("p", "");
	}
	
	/**
	 * Determines the singular form a word by using the EnglishMinimalStemmer lib (Apache Lucene)
	 * and return the lowercase representation of the string.
	 * 
	 * @param term
	 * @return
	 */
	public static String getSingluarWordFromPluralForm(String term) {
		EnglishMinimalStemmer stemmer = new EnglishMinimalStemmer();
		char[] termCharArray = term.toCharArray();
		int stemLen = stemmer.stem(termCharArray, term.length());
		String singularWord=String.valueOf(termCharArray).substring(0, stemLen);
		return singularWord.toLowerCase();
	}
}
