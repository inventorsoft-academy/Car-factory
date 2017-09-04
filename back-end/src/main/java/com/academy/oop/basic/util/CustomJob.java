package com.academy.oop.basic.util;

import com.academy.oop.basic.util.impl.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class CustomJob implements ApplicationListener<ApplicationEvent> {

    private Logger logger = Logger.getLogger(CustomJob.class);

    private String db =
            "CREATE DATABASE test_factory\n" +
                    "    WITH \n" +
                    "    OWNER = postgres\n" +
                    "    ENCODING = 'UTF8'\n" +
                    "    LC_COLLATE = 'Russian_Russia.1251'\n" +
                    "    LC_CTYPE = 'Russian_Russia.1251'\n" +
                    "    TABLESPACE = pg_default\n" +
                    "    CONNECTION LIMIT = -1;";

    private String cars = "CREATE TABLE IF NOT EXISTS cars (" +
            "  id    SERIAL NOT NULL," +
            "  brand TEXT   NOT NULL," +
            "  model TEXT   NOT NULL," +
            "  color TEXT," +
            "  price DOUBLE PRECISION," +
            "  PRIMARY KEY (id)" +
            ");";

    private String parts = "CREATE TABLE IF NOT EXISTS parts (" +
            "  id    SERIAL           NOT NULL," +
            "  type  TEXT             NOT NULL," +
            "  price DOUBLE PRECISION NOT NULL," +
            "  used  BOOLEAN          NOT NULL DEFAULT FALSE," +
            "  PRIMARY KEY (id)" +
            ");";

    @Autowired
    private ConnectionManager connectionManager;
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        create(db);
        create(cars);
        create(parts);
    }

    private void create(String sql) {
        logger.error("Job is working");
        try {
            connectionManager.getStatement().executeQuery(sql);
        } catch (SQLException e) {
            logger.error(e.getMessage() + "table not created.");
        }
    }
}
