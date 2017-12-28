package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import util.StringUtil;

public class ExcelUtility {
	
	static HashMap<String, Integer> columnNamesMap;

	
	public HashMap<String, String> getData(String TestCaseName) throws EncryptedDocumentException, InvalidFormatException,IOException, NullPointerException {
		
		HashMap<String, String> getData = new HashMap<String, String>();

		File src = new File("src//test//resources//MyBlue_DataSheet.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh1 = wb.getSheetAt(1);

		int totalrow = sh1.getPhysicalNumberOfRows();
		Row row = sh1.getRow(0);
		System.out.println("Total Row is : " + row);
		int col = row.getPhysicalNumberOfCells();
		System.out.println("Total column is : " + col);
			
		for (int i = 2; i <= totalrow; i++) {
			Row ro=sh1.getRow(i);
			//String ssnExtract = sh1.getRow(i).getCell(0).getStringCellValue();
			/*String planExtract = sh1.getRow(i).getCell(1).getStringCellValue();*/
			//String executionFlag = getCellValue(sh1.getRow(i), "ExecutionFlag", sh1);			
			
			//if (executionFlag.equals("Y")) {
				for (int j = 2; j <= col; j++) {
					String key = sh1.getRow(0).getCell(j).getStringCellValue().trim();
					try {
						String value = sh1.getRow(i).getCell(j).getStringCellValue().trim();
						//StringUtil.clean(value);
						getData.put(key, value);
					} catch (NullPointerException e) {
						// String
						// value=sh1.getRow(i).getCell(j).getStringCellValue().trim();
						getData.put(key, "");
					}
				}

			//}

		}
		return getData;
	}
	
	static HashMap<String, Integer> getColumnNamesHashMapFromSheet(Sheet sheet){
		Row row = sheet.getRow(0);
		HashMap<String, Integer> columnNamesMap = new HashMap<String, Integer>();

		int minColIx = row.getFirstCellNum();
		int maxColIx = row.getLastCellNum();
		for (int colIx = minColIx; colIx < maxColIx; colIx++) {
			Cell cell = row.getCell(colIx);
			if (cell != null) {
				columnNamesMap.put(cell.getStringCellValue().trim(),
						cell.getColumnIndex());

			}
		}
		
		return columnNamesMap;
	}	
	
	public static String getCellValue(Row row, String columnName, Sheet sheet) {
		
		columnNamesMap = getColumnNamesHashMapFromSheet(sheet);
		if (row == null || columnName == null || columnName.trim() == ""
				|| columnNamesMap == null
				|| columnNamesMap.get(columnName) == null
				|| row.getCell(columnNamesMap.get(columnName)) == null)
			return "";

		Cell cell = row.getCell(columnNamesMap.get(columnName));
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:

			return cell.getStringCellValue();
		case Cell.CELL_TYPE_NUMERIC:

			return Double.toString(cell.getNumericCellValue());

		case Cell.CELL_TYPE_BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
			
		
		default:
			return "";
			// some code
		}

	}

	

	public static void setCellValue(Row row, String columnName, String value) {

		Cell cell = row.getCell(columnNamesMap.get(columnName));

		if (cell == null)
			cell = row.createCell(columnNamesMap.get(columnName));

		cell.setCellValue(value);

	}
	
}
