package com.academy.oop.basic.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileCleaner {
	private static final Logger log = Logger.getLogger(FileCleaner.class);
	public static void crear(File file) {
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.print("");
			log.info("file: " + file.getName() + " clean");
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
