package testcase;

import java.util.Properties;

import operation.ReadObject;
import operation.UiOperation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import excel.ReadExcel;

public class ExecuteTest {
	public static void main(String args[]) throws Exception {
		WebDriver driver;

		// TODO Auto-generated method stub
		String workingDir = System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver", workingDir
				+ "\\Resource\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver.get("http://uat.techfetch.com");
		ReadExcel file = new ReadExcel();
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UiOperation operation = new UiOperation(driver);

		// Read keyword sheet
		Sheet guru99Sheet = file.readExcel(System.getProperty("user.dir")
				+ "\\", "\\Resource\\recr.xlsx", "Sheet1");
		// Find number of rows in excel file
		int rowCount = guru99Sheet.getLastRowNum()
				- guru99Sheet.getFirstRowNum();

		for (int i = 0; i < rowCount + 1; i++) {
			// Loop over all the rows

			Row row = guru99Sheet.getRow(i);
			System.out.println(row.getCell(0).toString() + "----"
					+ row.getCell(1).toString() + "----"
					+ row.getCell(2).toString() + "----"
					+ row.getCell(3).toString());

			operation.perform(allObjects, row.getCell(0).toString(), row
					.getCell(1).toString(), row.getCell(2).toString(), row
					.getCell(3).toString());

		}

	}
}
