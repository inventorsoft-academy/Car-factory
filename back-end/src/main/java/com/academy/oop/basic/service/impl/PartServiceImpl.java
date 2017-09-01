package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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

	@Override
	public boolean updatePart(int id, Part part) {
		return false;
	}
}
