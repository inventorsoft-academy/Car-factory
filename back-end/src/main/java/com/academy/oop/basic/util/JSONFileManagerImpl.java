package com.academy.oop.basic.util;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileManagerImpl implements FileManager {
    private static final Logger log = Logger.getLogger(JSONFileManagerImpl.class);

    private static final String PARTS_FILE_PATH = "back-end\\src\\main\\resources\\JSONFiles\\Parts.json";
    private static final String CARS_FILE_PATH = "back-end\\src\\main\\resources\\JSONFiles\\Cars.json";

    private static List<Part> parts;
    private static List<Car> cars;

    private ObjectMapper mapper;

    public JSONFileManagerImpl() {
        parts = new ArrayList<>();
        cars = new ArrayList<>();
        mapper = new ObjectMapper();
    }

    @Override
    public int getNextId(List list) {
        return list.size();
    }

    @Override
    public void refreshCarFile() {
        if (cars != null) {
            saveCars(cars);
        }
    }

    @Override
    public void refreshPartFile() {
        if (parts != null) {
            saveParts(parts);
        }
    }

    @Override
    public List<Part> loadPartList() {
        List<Part> temParts = new ArrayList<>();
        try {
            TypeReference<List<Car>> partRes = new TypeReference<List<Car>>(){};
            temParts = mapper.readValue(new File(
                    PARTS_FILE_PATH), partRes);
            parts.addAll(temParts);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return temParts;
    }

    @Override
    public List<Car> loadCarList() {
        List<Car> temCars = new ArrayList<>();
        try {
            TypeReference<List<Car>> carRef = new TypeReference<List<Car>>(){};
            temCars = mapper.readValue(new File(
                    CARS_FILE_PATH), carRef);
            cars.addAll(temCars);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return temCars;
    }

    private void saveCars(List<Car> cars) {
        save(CARS_FILE_PATH, cars);
    }

    private void saveParts(List<Part> parts) {
        save(PARTS_FILE_PATH, parts);
    }

    private void save(String filePath, List elements) {
        try {
            mapper.writeValue(new File(filePath), elements);
        } catch (JsonGenerationException e) {
            log.error(e.getMessage());
        } catch (JsonMappingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
