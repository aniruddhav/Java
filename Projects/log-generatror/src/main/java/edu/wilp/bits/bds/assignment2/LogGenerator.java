package edu.wilp.bits.bds.assignment2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogGenerator {

	private static Logger logger = LoggerFactory.getLogger(LogGenerator.class);

	public static void main(String[] args) {
		logger.debug("Debug log message");
		logger.info("Info log message");
		logger.error("Error log message");

	}

}
