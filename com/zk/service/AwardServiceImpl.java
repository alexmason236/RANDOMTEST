package com.zk.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zk.model.Participator;

@Component("awardService")
public class AwardServiceImpl implements AwardService {

	@Autowired
	SessionFactory sf;

	@Transactional
	public Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	@Transactional
	public List<Participator> getAllParticipators() {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		Query<Participator> query = session.createQuery("from Participator");
		List<Participator> list = new ArrayList<>();
		list = query.list();
		return list;
	}

	@Override
	public List<Integer> getRandomNumList(int nums, int start, int end) {
		List<Integer> list = new ArrayList<Integer>();
		Random r = new Random();
		while (list.size() != nums) {
			int num = r.nextInt(end - start) + start;
			if (!list.contains(num)) {
				list.add(num);
			}
		}
		return list;
	}

	@Override
	public List<Integer> sort(List<Integer> list) {
		int temp = 0;
		Integer[] numbers = new Integer[list.size()];
		list.toArray(numbers);
		int size = list.size();
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (numbers[j] < numbers[j + 1]) {
					temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}
		return Arrays.asList(numbers);
	}

	@Override
	public List<Participator> readExcelData(String filePath) {
		List<Participator> list = new ArrayList<Participator>();
		Participator participator = null;
		FileInputStream fis = null;
		try {
			// Create the input stream from the xlsx/xls file
			fis = new FileInputStream(filePath);
			// Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if (filePath.toLowerCase().endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (filePath.toLowerCase().endsWith(".xls")) {
				workbook = new HSSFWorkbook(fis);
			}
			Sheet sheet = null;
			Row row = null;
			if (workbook != null) {
				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 循环表
					sheet = workbook.getSheetAt(i);// 第i张表格
					for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {// 循环行
						// row.getPhysicalNumberOfCells(); //列数
						row = sheet.getRow(j);
						if (row == null) {
							break;
						}
						if (StringUtils.isEmpty(getCellVaule(row.getCell(0)))) {
							break;
						}
						participator = new Participator();
						participator.setParticipatorName(getCellVaule(row.getCell(1)));
						participator.setWordNumber(getCellVaule(row.getCell(0)));
						participator.getBelong2Team().setId(Integer.parseInt(getCellVaule(row.getCell(2))));
						list.add(participator);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public String getCellVaule(Cell cell) {
		String value = null;
		if (cell == null)
			return value;
		switch (cell.getCellType()) {
		case STRING:
			value = cell.getRichStringCellValue().toString();
			break;
		case NUMERIC:
			value = new DecimalFormat("0.######").format(cell.getNumericCellValue());
			break;
		case BOOLEAN:
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA:
			value = cell.getArrayFormulaRange().formatAsString();
			break;
		case BLANK:
			value = "";
			break;
		default:
			value = cell.getRichStringCellValue().toString();
			break;
		}
		if (value == null)
			return null;
		return value;
	}

}
