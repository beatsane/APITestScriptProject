package com.apitestscript.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author CHIDUSD
 */
public class ExcelUtility {

	/**
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {
		FileInputStream input = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(input);
		String data = workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		workbook.close();
		return data;
	}

	/**
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream input = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(input);
		int rowCount = workbook.getSheet(sheetName).getLastRowNum();
		workbook.close();
		return rowCount;
	}

	/**
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream input = new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(input);
		workbook.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		FileOutputStream output = new FileOutputStream("./testdata/testScriptdata.xlsx");
		workbook.write(output);
		workbook.close();
	}

	/**
	 * @param excelPath
	 * @param sheetName
	 * @param testcaseName
	 * @param requiredKey
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getDataFromExcel(String excelPath, String sheetName, String testcaseName, String requiredKey)
			throws EncryptedDocumentException, IOException {
		FileInputStream input = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(input);
		Sheet sheet = workbook.getSheet(sheetName);
		String value = "";
		String actualTestCaseName = "";
		String actualKey = "";
		boolean flag = false;

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			try {
				actualTestCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
			} catch (Exception e) {
			}
			if (actualTestCaseName.equalsIgnoreCase(testcaseName)) {
				for (int j = 1; j <= sheet.getRow(i).getLastCellNum(); j++) {
					try {
						actualKey = sheet.getRow(i - 1).getCell(j).getStringCellValue();
					} catch (Exception e) {
					}
					if (actualKey.equalsIgnoreCase(requiredKey)) {
						try {
							value = sheet.getRow(i).getCell(j).toString();
						} catch (Exception e) {
						}
						flag = true;
						break;
					}
				}
			}
			if (flag) {
				break;
			}
		}
		workbook.close();
		return value;
	}
}
