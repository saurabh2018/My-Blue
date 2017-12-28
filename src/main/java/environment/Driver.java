package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import myBlue_Tests.MyPlan;
import utility.ExcelUtility;


public class Driver {
	
	static AppiumDriver<WebElement> driver = null;


	public AppiumDriver<WebElement> CreateDriver()  {
		
			int rowNo = 1;
			//System.out.println("Processing row number:" + rowNo);
			
			File src = new File("src//test//resources//MyBlue_DataSheet.xlsx");
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(src);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			XSSFWorkbook WrkBk = null;
			try {
				WrkBk = new XSSFWorkbook(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			XSSFSheet Sht = WrkBk.getSheet("Devices");
			Row row = Sht.getRow(1);		

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", row.getCell(0).getStringCellValue());
			caps.setCapability("udid", row.getCell(1).getStringCellValue()); //Give Device ID of your mobile phone
			caps.setCapability("platformName", row.getCell(2).getStringCellValue());
			caps.setCapability("platformVersion", row.getCell(3).getStringCellValue());
			//caps.setCapability("appPackage", row.getCell(4).getStringCellValue()); 
			//caps.setCapability("appActivity", row.	getCell(5).getStringCellValue());
			
			//Android Clock		
			//caps.setCapability("appPackage", "com.google.android.deskclock"); 
			//caps.setCapability("appActivity", "com.android.deskclock.DeskClock");
			
			//Open Stream My Blue
			caps.setCapability("appPackage", "com.openstream.bcbsma.eva.myblue"); 
			caps.setCapability("appActivity", "com.openstream.mmi.gui.CuemeActivityDelegate");
			
			caps.setCapability("fastReset", true);
			caps.setCapability("resetKeyboard", true);
			caps.setCapability("unicodeKeyboard", true);

			
			try {
				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
				

		return driver;	

		
	}
	


}
