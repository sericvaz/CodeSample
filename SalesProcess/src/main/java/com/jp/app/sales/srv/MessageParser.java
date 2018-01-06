package com.jp.app.sales.srv;

import com.jp.app.sales.dto.SalesRequest;

/**
 * Parses the input message string and converts it into appropriate objects of SalesRequest Type.
 * 
 * @author sharon
 *
 */
public interface MessageParser {
	
	/**
	 * Parses the input message string, creates and returns appropriate objects of SalesRequest Type.
	 * 
	 * @param message
	 * @return
	 */
	<T extends SalesRequest> T getSalesRequestFromIncomingMessage(String message) ;

}
