package com.academy.oop.basic.dao;


import com.academy.oop.basic.model.Part;

import java.net.URL;
import java.util.List;

public interface SqlManager {

    String URL_PATH  = "jdbc:postgresql://localhost:5432/car_factory";
    String USER_NAME  = "postgres";
    String DB_PASSWORD = "";
    String JDBC_DRIVER_NAME = "org.postgresql.Driver";

    String CREATE_TABLE_PART = "CREATE TABLE IF NOT EXISTS public.parts\n" +
            "(\n" +
            "    _id serial NOT NULL,\n" +
            "    type text NOT NULL,\n" +
            "    price money NOT NULL,\n" +
            "    used boolean NOT NULL DEFAULT false,\n" +
            "    PRIMARY KEY (_id)\n" +
            ")";

    String CREATE_TABLE_CAR = "CREATE TABLE IF NOT EXISTS public.cars\n" +
            "(\n" +
            "    _id serial NOT NULL,\n" +
            "    brand text NOT NULL,\n" +
            "    model text NOT NULL,\n" +
            "    color text,\n" +
            "CONSTRAINT cars_pkey PRIMARY KEY (_id),\n" +
            "    CONSTRAINT part_id FOREIGN KEY (_id)\n" +
            "        REFERENCES public.parts (_id) MATCH SIMPLE\n" +
            "        ON UPDATE NO ACTION\n" +
            "        ON DELETE NO ACTION" +
            ")";

    String GET_PARTS = "SELECT _id, type, price, used\n" +
                        "\tFROM public.parts;";

    void createTableCar();

    void createTablePart();

    List<Part> getParts();

    Part getPartById(int id);

    void addPart(Part part);

    void updatePart(Part part);

    void deletePartById(int id);
}
