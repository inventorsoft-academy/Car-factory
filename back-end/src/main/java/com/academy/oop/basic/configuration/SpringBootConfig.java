package com.academy.oop.basic.configuration;

import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.dao.impl.PartDaoImpl;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.service.impl.PartServiceImpl;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.impl.JSONFileManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class SpringBootConfig {


	@Bean
	public FileManager fileManager() {
		return new JSONFileManagerImpl();
	}
}
