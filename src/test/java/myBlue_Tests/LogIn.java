package myBlue_Tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.reporters.ExitCodeListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;

import environment.Driver;
import environment.Synchronization;
import environment.TestBase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import utility.ExcelUtility;

public class LogIn{
	
	public static AppiumDriver<WebElement> driver;
	//static AppiumDriver<WebElement> driver;

	static Workbook workbook;
	static HashMap<String, Integer> columnNamesMap;
	static Sheet sheet;
		
	@Test(dataProvider = "LogIn")
	//public static void main(String[] args) throws InterruptedException {
	public static void Login(int rowNo) throws InterruptedException {	
		
		Synchronization synchronization = new Synchronization();		
		ExcelUtility excelUtility = new ExcelUtility();
		Driver tempdriver = new Driver();
		AppiumDriver<WebElement> driver  = tempdriver.CreateDriver();
				
		System.out.println("Processing LogIn row number: " + rowNo);
		sheet = workbook.getSheet("LogIn");
		Row row = sheet.getRow(rowNo);

		if (excelUtility.getCellValue(row, "ExecutionFlag", sheet).equals("N"))
			throw new SkipException("The test is skipped");

			//By skip = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]");
			//WebElement el = driver.findElement(skip);
			By close = By.xpath("//android.widget.ImageView[@instance ='0']");
			driver.findElement(close).click();

			By signIn = By.xpath("//android.widget.TextView[@text = 'Sign In']");
			synchronization.waitForElementToVisible(signIn, 60, driver);
			driver.findElement(signIn).click();
			//Thread.sleep(5000);
					
			By userName = By.xpath("//android.widget.EditText[@text = 'Username']");
			synchronization.waitForElementToVisible(userName, 60, driver);
			driver.findElement(userName).sendKeys(excelUtility.getCellValue(row, "UserName", sheet));

			By password = By.xpath("//android.widget.EditText[@NAF = 'true']");
			//By password = By.xpath("//android.widget.TextView[not(text())]");
			driver.findElement(password).sendKeys(excelUtility.getCellValue(row, "Password", sheet));
			//driver.findElement(password).sendKeys(Keys.TAB);
			//Thread.sleep(5000);
			
			
			//By btnSignIn = By.xpath("//android.widget.Button[@text = 'Sign In']");
			//By btnSignIn = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.Button");
			By btnSignIn = By.xpath("//android.widget.Button");
			driver.findElement(btnSignIn).click();;
			//driver.findElement(btnSignIn).sendKeys(Keys.ENTER);
			
			By welcomeLogInMessage = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]");
			By valitionMissingLogin = By.xpath("//android.widget.TextView[@text = '*Required Field']");
			By valitionAuthenticationLogin = By.xpath("//android.widget.TextView[2][@text = 'Authentication failed from API please enter the valid credentials']");
			//waitForElement (valitionMissingLogin, 2);
			SoftAssert softAssert = new SoftAssert();
			if (! driver.findElements(welcomeLogInMessage).isEmpty()){
				System.out.println("VALIDATION POINT : LogIn successful as Welcome to Blue Cross message poped up");
				
			} else if (! driver.findElements(valitionMissingLogin).isEmpty()) {
				System.out.println("VALIDATION POINT : LogIn failed as either UserName, Password or both are empty");
				//softAssert.fail("LogIn failed as either UserName, Password or both are empty");
				softAssert.assertTrue(false, "LogIn failed as either UserName, Password or both are empty");
				throw new SkipException("Test Skipped");
				
			} else if (! driver.findElements(valitionAuthenticationLogin).isEmpty()) {
				System.out.println("VALIDATION POINT : LogIn failed as either UserName, Password or both are wrongly populated");
				//softAssert.fail("LogIn failed as either UserName, Password or both are wrongly populated");
				softAssert.assertTrue(false, "LogIn failed as either UserName, Password or both are wrongly populated");
				throw new SkipException("Test Skipped");
			}
			
			
			By HamburgerButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView");
			driver.findElement(HamburgerButton).click();
			
			By btnSignOut =  By.xpath("//android.widget.TextView[@text = 'Sign Out']");
			synchronization.waitForElementToVisible(btnSignOut, 60, driver);
			driver.findElement(btnSignOut).click();
			if (driver.findElements(btnSignIn).isEmpty()) {
				System.out.println("VALIDATION POINT : SignOut Failed");
				softAssert.assertTrue(false, "SignOut Failed");
			
			} else {
				System.out.println("VALIDATION POINT : SignOut Passed");
				
			}
	
			softAssert.assertAll();

		
		
		//} catch (MalformedURLException e) {
			//System.out.println(e.getMessage());
		//}
	}
	
	@DataProvider(name = "LogIn")
	public Object[][] data() {
		
		//logger.info("setting up data provider for My Blue App");
		setUpInputData();
		Sheet sheet = workbook.getSheet("LogIn");

		System.out.println("sheet.getLastRowNum():" + sheet.getLastRowNum());

		Object[][] params = new Object[sheet.getLastRowNum()][1];
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			System.out.println("adding row:" + i);
			params[i - 1] = new Object[] { i };

		}

		//logger.info("data:" + params);

		//logger.info("Exiting data provider set up");
		System.out.println(params);
		return params;
	}
	

	
	public static void setUpInputData() {
		//logger.info("setting up input data for My Blue automation");

		try {
			workbook = WorkbookFactory.create(LogIn.class.getResourceAsStream("/MyBlue_DataSheet.xlsx"));

			columnNamesMap = new HashMap<String, Integer>();

			sheet = workbook.getSheetAt(0);
			System.out.println("sheet: " + sheet.getSheetName());

			// System.out.println("sheet size :"+sheetNamesMap.size());

			// System.out.println("keys: "+sheetNamesMap.keySet());

			// System.out.println("keys "+sheetNamesMap.get);
			Row row = sheet.getRow(0);

			int minColIx = row.getFirstCellNum();
			System.out.println("Minimum Col Index: " + minColIx);
			int maxColIx = row.getLastCellNum();
			System.out.println("Maximum Col Index: " + maxColIx);
			for (int colIx = minColIx; colIx < maxColIx; colIx++) {
				Cell cell = row.getCell(colIx);
				if (cell != null) {
					columnNamesMap.put(cell.getStringCellValue().trim(),
							cell.getColumnIndex());

				}
			}
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	  @AfterTest
	  public void tearDown() {
	    driver.quit();
	  }
	
	
	
	

	

}