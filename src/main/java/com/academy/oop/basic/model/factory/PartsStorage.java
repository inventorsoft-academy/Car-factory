package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Part;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class PartsStorage implements IPartsStorage {

	private static final String FILE_PATH = "src\\main\\resources\\Parts.txt";
	private static final String FILE_PATH_TEMP = "src\\main\\resources\\PartsTemp.txt";
	File file = new File(FILE_PATH);
	File fileTemp = new File(FILE_PATH_TEMP);
	BufferedWriter writer;
	BufferedReader reader;


	@Override
	public void save(Part part) {
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			StringBuilder builder = new StringBuilder();
			builder.append(part.getPartId())
					.append("/")
					.append(part.getName())
					.append("/")
					.append(part.getPrice())
					.append("/")
					.append(part.getType());
			writer.append(builder.toString());
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void update(int partID, Part newPart) {
		remove(partID);
		save(newPart);
	}

	@Override
	public void remove(int partID) {
		try {
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(fileTemp, true));
			String line;
			String[] currenrLines;
			while ((line = reader.readLine()) != null) {
				currenrLines = line.split("/");
				if (partID == Integer.parseInt(currenrLines[0])) {
					continue;
				}
				writer.write(line);
				writer.newLine();
			}
			reader.close();
			writer.flush();
			writer.close();
			file.delete();
			fileTemp.renameTo(file);


		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

	}

	@Override
	public Part getByType(PartsType type) {
		Part part = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			String[] currenrLine;
			while ((line = reader.readLine()) != null) {
				currenrLine = line.split("/");
				if (type.toString().equals(currenrLine[3])) {
					part = new Part(currenrLine[1].toString(), PartsType.valueOf(currenrLine[3].toString()), Double.parseDouble(currenrLine[2]), Integer.parseInt(currenrLine[0]));
					reader.close();
					remove(Integer.parseInt(currenrLine[0]));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return part;
	}

	@Override
	public List<Part> getParts() {
		List<Part> parts = new LinkedList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			String[] currenrLine;
			while ((line = reader.readLine()) != null) {
				currenrLine = line.split("/");
				parts.add(new Part(currenrLine[1].toString(), PartsType.valueOf(currenrLine[3].toString()), Double.parseDouble(currenrLine[2]), Integer.parseInt(currenrLine[0])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parts;
	}

	public static int getNextId() {
		int partId = 0;
		try {
			BufferedReader r = new BufferedReader(new FileReader(new File(FILE_PATH)));
			String line;
			String[] currentLine;
			while ((line = r.readLine()) != null) {
				currentLine = line.split("/");
				partId = Integer.parseInt(currentLine[0]) + 1;
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return partId;
	}
}
