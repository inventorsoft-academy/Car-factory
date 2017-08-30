package com.academy.oop.basic.configuration;

import com.academy.oop.basic.service.CarService;
import com.academy.oop.basic.service.impl.CarServiceImpl;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.service.impl.PartServiceImpl;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.JSONFileManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfig {

	@Bean
	public CarService carFactory() {
		return new CarServiceImpl(fileManager(), partStorage());
	}

	@Bean
	public PartService partStorage() {
		return new PartServiceImpl();
	}


	@Bean
	public FileManager fileManager() {
		return new JSONFileManagerImpl();
	}
}
