package testData;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData {
	
	public Map<String, String> getExcelData(String TestCaseValue) throws EncryptedDocumentException, IOException {
		
		List<String> headers = new LinkedList<String>();
		List<String> LineData = new LinkedList<String>();
		Map<String, String> data = new LinkedMap<String, String>();
		
		
		Workbook workbook = WorkbookFactory.create(new File("./testDataSelenium.xlsx"));
		Sheet sheet = workbook.getSheet("Sheet1");
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		//header
		for(int i=0; i<cols; i++) {
			headers.add(sheet.getRow(0).getCell(i).getStringCellValue());
			}
				
				
		for(int row=0; row<=rows; row++) {
			for(int col=0; col<cols; col++) {
				if(sheet.getRow(row).getCell(0).getStringCellValue().equals(TestCaseValue)) {
					if(sheet.getRow(row).getCell(col) != null) {
						LineData.add(sheet.getRow(row).getCell(col).getStringCellValue());
					}else {
						LineData.add("null");
					}
					
				}
			}
		}
		
		for(int i=0; i<headers.size(); i++) {
			data.put(headers.get(i), LineData.get(i));
		}

		return data;	
	}
	
	//testing
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExcelData exceldata = new ExcelData();
		Map<String, String> testData = exceldata.getExcelData("TC002_Login_NewCustomer");
		
		System.out.println(testData.get("username"));
		System.out.println(testData.get("password"));
		System.out.println(testData.get("firstname"));
		System.out.println(testData.get("lastname"));
	
	
	}
}
