package com.academy.oop.basic.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
	private static final String path = "src\\main\\resources\\log\\log.txt";
	public static final String IN_CONSOLE = "console";
	public static final String IN_FILE = "file";
	public static final String IN_CONSOLE_AND_FILE = "console, file";
	private static String level = IN_FILE;


	private String className;
	private static Logger logger;

	public static Logger getLogger(Class clas) {
		logger = new Logger();
		logger.setClassName(clas.getName());
		return logger;
	}

	public void info(String massage) {
		String log = LocalDateTime.now() + " INFO " + className + " - " + massage;
		if (level.equals(IN_CONSOLE) || level.equals(IN_CONSOLE_AND_FILE)) {
			System.out.println(log);
		} else if (level.equals(IN_FILE) || level.equals(IN_CONSOLE_AND_FILE)) {
			write(log);
		}
	}

	public void error(String massage) {
		String log = LocalDateTime.now() + " ERROR " + className + " - " + massage;
		if (level.equals(IN_CONSOLE) || level.equals(IN_CONSOLE_AND_FILE)) {
			System.err.println(log);
		} else if (level.equals(IN_FILE) || level.equals(IN_CONSOLE_AND_FILE)) {
			write(log);
		}
	}

	private void write(String massage) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), true))) {
			writer.append(massage);
			writer.newLine();
			writer.flush();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void setClassName(String clasName) {
		className = clasName;
	}

	public void setLevel(String level) {
		if (level.equals(IN_CONSOLE_AND_FILE)) {
			logger.level = IN_CONSOLE_AND_FILE;
		} else if (level.equals(IN_CONSOLE)) {
			logger.level = IN_CONSOLE;
		} else {
			logger.level = IN_FILE;
		}
	}
}
