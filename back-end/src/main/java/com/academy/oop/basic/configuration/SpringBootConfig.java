package com.academy.oop.basic.configuration;

import com.academy.oop.basic.model.factory.CarFactory;
import com.academy.oop.basic.model.factory.CarFactoryImpl;
import com.academy.oop.basic.model.factory.PartsStorage;
import com.academy.oop.basic.model.factory.PartsStorageImpl;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.JSONFileManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfig {

	@Bean
	public CarFactory carFactory() {
		return new CarFactoryImpl(fileManager(), partStorage());
	}

	@Bean
	public PartsStorage partStorage() {
		return new PartsStorageImpl();
	}


	@Bean
	public FileManager fileManager() {
		return new JSONFileManagerImpl();
	}
}
