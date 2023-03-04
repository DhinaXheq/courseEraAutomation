package Test_Project;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcelData {
			
	public String Excel(int ColNum) throws Exception{ //Declaring a method accepting column number of the excel as integer 
		
		File file = new File("TestSample.xlsx"); //Getting excel file from the project 
		
		String absPath = file.getAbsolutePath(); //Storing the absolute path of the excel file in a string

        FileInputStream excelFile = new FileInputStream(absPath); //Inserting the file into the compiler 

        XSSFWorkbook workbook = new XSSFWorkbook(excelFile); //Declaring work book object for the inserted excel file

        XSSFSheet sheet= workbook.getSheet("Data");  //Storing the sheet named 'Data' the sheet object

        XSSFRow row = sheet.getRow(0);    //getting first row from the sheet

        XSSFCell cell = row.getCell(ColNum);	//getting cell value and storing it in cell object

        DataFormatter format = new DataFormatter(); //Using 'DataFormatter' to format cell value as String 

        String excelData = format.formatCellValue(cell);

        workbook.close();

       return excelData;  //returning the excel data to the called method

        
    }

}
