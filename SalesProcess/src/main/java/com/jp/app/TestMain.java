package com.jp.app;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.jp.app.sales.srv.SalesMessageProcessor;
import com.jp.app.sales.srv.SalesMessageProcessorImpl;
import com.jp.app.sales.util.MessageAppLogger;

/**
 * @author sharon
 *
 */
public class TestMain {
	
	private static Logger logger=MessageAppLogger.getLogger(TestMain.class);

	public static void main(String[] args) {

		SalesMessageProcessor sale = new SalesMessageProcessorImpl();
		try (Stream<String> stream = Files.lines(Paths.get("test.txt"))) {
			stream.forEach(str->{
				try{
					sale.processMessage(str);	
				}catch(Exception e){
					logger.severe("\nMessage not parsed:"+str+"\nError Details:"+e.getMessage());
				}
			});
		} catch (java.io.IOException e) {
			logger.severe(e.getMessage());
		}
	}

}
