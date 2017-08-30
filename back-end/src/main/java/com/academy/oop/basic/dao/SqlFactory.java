package com.academy.oop.basic.dao;


import java.net.URL;

public interface SqlFactory {

    final String URL_PATH  = "jdbc:postgresql://localhost:5432/hotel";
    final String USER_NAME  = "postgres";
    final String DB_PASSWORD = "";
    final String PART_TABLE_NAME = "parts";
    final String CAR_TABLE_NAME = "cars";

    final URL CREATE_TABLE_PARTS = SqlFactory.class
            .getClassLoader().getResource("sql/create_table_parts.sql");

    final URL CREATE_TABLE_CARS = SqlFactory.class
            .getClassLoader().getResource("sql/create_table_cars.sql");


    void connect();
}
