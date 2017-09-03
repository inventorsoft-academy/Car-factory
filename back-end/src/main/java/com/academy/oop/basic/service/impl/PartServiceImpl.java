package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.exception.NotFoundException;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

	private final PartDao partDao;

	@Autowired
	public PartServiceImpl(PartDao partDao) {
		this.partDao = partDao;
	}

	@Override
	public List<Part> getParts() throws SQLException {
		return partDao.getParts();
	}

	@Override
	public Part getPartById(int id) throws SQLException, NotFoundException {
		return partDao.getPartById(id);
	}

	@Override
	public boolean addPart(Part part) throws SQLException, ClassNotFoundException {
		return partDao.addPart(part);
	}

	@Override
	public boolean addParts(List<Part> parts) throws SQLException, ClassNotFoundException {
		for (Part part: parts) {
			partDao.addPart(part);
		}
		return true;
	}

	@Override
	public boolean deletePartById(int id) throws SQLException {
		return partDao.deletePartById(id);
	}

	@Override
	public boolean updatePart(int id, Part part) throws SQLException {
		return partDao.updatePart(id, part);
	}

}
