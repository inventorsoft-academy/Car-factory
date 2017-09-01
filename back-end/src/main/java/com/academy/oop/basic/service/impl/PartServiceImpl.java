package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.configuration.CustomConnection;
import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.dao.SqlManager;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.impl.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartDao partDao;

	@Override
	public List<Part> getParts() {
		return partDao.getParts();
	}

	@Override
	public Part getPartById(int id) {
		return null;
	}

	@Override
	public boolean addPart(Part part) throws SQLException, ClassNotFoundException {
		partDao.addPart(part);
		return true;
	}

	@Override
	public boolean addParts(List<Part> parts) {
		return false;
	}

	@Override
	public boolean editPart(int id, Part newPart) {
		return false;
	}

	@Override
	public boolean deletePartById(int id) {
		return false;
	}
}
