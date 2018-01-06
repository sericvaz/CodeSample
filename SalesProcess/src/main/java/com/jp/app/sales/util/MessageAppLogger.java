package com.jp.app.sales.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Utility class to maintain logging across the application.
 * This can be extended to redirect the logs to an external file/db etc.
 * TODO: Format of log statements to be fine tuned.
 *  
 * @author sharon
 *
 */
public class MessageAppLogger {
	
	/** Gets the logger for the given class, sets the log level with the handler.
	 * 
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
	 * Added ConsoleHandler to the Logger and setup a custom formatter.
	 * 
	 * @param logger
	 */
	private static void addHandlerToLogger(Logger logger) {
		ConsoleHandler handler=new ConsoleHandler();
		handler.setLevel(logger.getLevel());
		handler.setFormatter(new ServiceLoggerFormatter());
		logger.addHandler(handler);
	}
	
	/**
	 * Custom formatter class to format the logs messages.
	 * 
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
