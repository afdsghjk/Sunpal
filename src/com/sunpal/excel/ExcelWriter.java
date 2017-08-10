package com.sunpal.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelWriter {
//	public static void main(String[] args) {
//		generateExcelTestcase(
//				"D:\\Tools\\GraphWalker\\11CMD.txt" ,
//				"D:\\Tools\\GraphWalker\\全民惠农测试用例.xls");
//	}
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Input file and output file should be specified!");
			return;
		}
		
		generateExcelTestcase(args[0], args[1]);
	}
	
	public static void generateExcelTestcase(String inputFile, String outputFile) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("功能交互测试");
		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		Path filepath = Paths.get(inputFile);
		
		List<String> strs = new ArrayList<>();
		try (InputStream in = Files.newInputStream(filepath);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in, "GBK"))) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			        strs.add(line);
			    }
			} 
		catch (IOException x) {
			    System.err.println(x);
		}
		
		data.put(0, new Object[] {"用例编号", "测试模块", "子模块", "用例名称", "前置步骤", "执行步骤", "预期结果"});
		for (int i=0, j=1; i < strs.size(); i=i + 2, j++) {
			data.put(j, new Object[] {Integer.toString(j), "银商优选", "一起来扶贫", "", "", strs.get(i), strs.get(i+1)});
			System.out.println(j);
			System.out.println(strs.get(i));
			System.out.println(strs.get(i+1));
		}
		
		int rownum = 0;
		for(int i = 0; i < data.size(); i++) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(i);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		
		try {
			FileOutputStream out = 
					new FileOutputStream(new File("D:\\Tools\\GraphWalker\\全民惠农测试用例.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void excelWriteDemo() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		data.put("1", new Object[] {"Emp No.", "Name", "Salary"});
		data.put("2", new Object[] {1d, "John", 1500000d});
		data.put("3", new Object[] {2d, "Sam", 800000d});
		data.put("4", new Object[] {3d, "Dean", 700000d});
		
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		
		try {
			FileOutputStream out = 
					new FileOutputStream(new File("C:\\new.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
