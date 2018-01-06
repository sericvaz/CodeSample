package com.jp.app.sales.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MessageAppLogger {
	
	/**
	 * @param className
	 * @return
	 */
	public static Logger getLogger(Class<?> className){
		
		Logger logger=Logger.getLogger(className.getName());
		logger.setLevel(Level.ALL);
		addHandlerToLogger(logger);
		return logger;
	}

	/**
	 * @param logger
	 */
	private static void addHandlerToLogger(Logger logger) {
		ConsoleHandler handler=new ConsoleHandler();
		handler.setLevel(logger.getLevel());
		handler.setFormatter(new ServiceLoggerFormatter());
		logger.addHandler(handler);
	}
	
	/**
	 * @author sharon
	 *
	 */
	private static class ServiceLoggerFormatter extends Formatter {

	    /* (non-Javadoc)
	     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	     */
	    @Override
	    public String format(LogRecord record) {
	        return  record.getMessage();
	    }
	}
	
}
