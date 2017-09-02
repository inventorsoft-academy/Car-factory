package com.academy.oop.basic.configuration;

import com.academy.oop.basic.util.ConnectionManager;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.impl.JSONFileManagerImpl;
import com.academy.oop.basic.util.impl.PostgresSqlConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfig {

	@Bean
	public FileManager fileManager() {
		return new JSONFileManagerImpl();
	}

	@Bean
	public ConnectionManager connectionManager() {
		return new PostgresSqlConnectionManager();
	}
}
