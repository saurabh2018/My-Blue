package myBlue_Tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.io.FileNotFoundException;

import environment.Driver;
import environment.Synchronization;
import environment.TestBase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.*;

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

import environment.TestBase;
import environment.Driver;


public class MyPlan {
	
	//public static AppiumDriver<WebElement> driver;
	//static AppiumDriver<WebElement> driver;

	static Workbook workbook;
	static HashMap<String, Integer> columnNamesMap;
	static Sheet sheet;
		
	@Test(dataProvider = "MyPlan")
	//public static void main(String[] args) throws InterruptedException {
	public static void MyPlan(int rowNo) throws InterruptedException {	
		
		Synchronization synchronization = new Synchronization();		
		ExcelUtility excelUtility = new ExcelUtility();

		Driver tempdriver = new Driver();
		AppiumDriver<WebElement> driver  = tempdriver.CreateDriver();
				
		System.out.println("Processing My Plan row number:" + rowNo);
		sheet = workbook.getSheet("MyPlan");
		Row row = sheet.getRow(rowNo);
		
		if (excelUtility.getCellValue(row, "ExecutionFlag", sheet).equals("N"))
			throw new SkipException("The test is skipped");
		
			By close = By.xpath("//android.widget.ImageView[@instance ='0']");
			driver.findElement(close).click();
			
			By signIn = By.xpath("//android.widget.TextView[@text = 'Sign In']");
			synchronization.waitForElementToVisible(signIn, 60, driver);
			driver.findElement(signIn).click();
			Thread.sleep(5000);
						
			By userName = By.xpath("//android.widget.EditText[@text = 'Username']");
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
			Thread.sleep(5000);
			//driver.findElement(btnSignIn).sendKeys(Keys.ENTER);
			
			By welcomeLogInMessage = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]");
			By valitionMissingLogin = By.xpath("//android.widget.TextView[@text = '*Required Field']");
			By valitionAuthenticationLogin = By.xpath("//android.widget.TextView[2][@text = 'Authentication failed from API please enter the valid credentials']");
			//waitForElement (valitionMissingLogin, 2);
			SoftAssert softAssert = new SoftAssert();
			if (! driver.findElements(welcomeLogInMessage).isEmpty()){
				System.out.println("VALIDATION POINT : LogIn successful as Welcome to Blue Cross message poped up");
				
			}
			if (! driver.findElements(valitionMissingLogin).isEmpty()) {
				System.out.println("VALIDATION POINT : LogIn failed as either UserName, Password or both are empty");
				//softAssert.fail("LogIn failed as either UserName, Password or both are empty");
				softAssert.assertTrue(false, "LogIn failed as either UserName, Password or both are empty");
				
			}
			//waitForElement (valitionMissingLogin, 2);
			if (! driver.findElements(valitionAuthenticationLogin).isEmpty()) {
				System.out.println("VALIDATION POINT : LogIn failed as either UserName, Password or both are wrongly populated");
				//softAssert.fail("LogIn failed as either UserName, Password or both are wrongly populated");
				softAssert.assertTrue(false, "LogIn failed as either UserName, Password or both are wrongly populated");
			}
			
			
			By MyPlanTab = By.xpath("//android.widget.TextView[@text = 'My Plan']");
			By PlanName = By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView");
			By SubsciberID = By.xpath("//android.view.ViewGroup[2]/android.widget.TextView[1]");
			By GroupID = By.xpath("//android.view.ViewGroup[2]/android.widget.TextView[2]");
			By InEffectDate = By.xpath("//android.view.ViewGroup[2]/android.widget.TextView[3]");

			driver.findElement(MyPlanTab).click();	
			Thread.sleep(5000);
			softAssert.assertEquals(driver.findElement(PlanName).getText(), excelUtility.getCellValue(row, "PlanName", sheet), "message");
			softAssert.assertEquals(driver.findElement(SubsciberID).getText(), excelUtility.getCellValue(row, "SubsciberID", sheet) + " suffix " + excelUtility.getCellValue(row, "Suffix", sheet), "message");
			softAssert.assertEquals(driver.findElement(GroupID).getText(), excelUtility.getCellValue(row, "GroupID", sheet), "message");
			softAssert.assertEquals(driver.findElement(InEffectDate).getText(), excelUtility.getCellValue(row, "InEffectDate", sheet), "message");

			By WhosCoveredArrow = By.xpath("//android.widget.ImageView[@instance = '3']");
			By CoPaysArrow = By.xpath("//android.widget.ImageView[@instance = '4']");
			By DeductibleArrow = By.xpath("//android.widget.ImageView[@instance = '5']");
			By OutOfPocketMaximumArrow = By.xpath("//android.widget.ImageView[@instance = '6']");
			By OverallBenefitMaximumArrow = By.xpath("//android.widget.ImageView[@instance = '7']");
			
			By WhosCoveredTable = By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup[1]");
			By CoPayTable = 	  By.xpath("//android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]");
			By DeductibleTable = By.xpath("//android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]");
			By OutOfPocketMaximumTable = By.xpath("//android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup[2]");
			By OverallBenefitMaximumTable = By.xpath("//android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup[2]");

			
			driver.findElement(WhosCoveredArrow).click();	
			WebElement element = driver.findElement(WhosCoveredTable);
			for (int i = 0; i< element.findElements(By.className("android.widget.TextView")).size() ; i++) {
				System.out.println(element.findElements(By.className("android.widget.TextView")).get(i).getText());
			}
			driver.findElement(WhosCoveredArrow).click();	

			
			driver.findElement(CoPaysArrow).click();	
			element = driver.findElement(CoPayTable);
			for (int i = 0; i< element.findElements(By.className("android.widget.TextView")).size() ; i++) {
				System.out.println(element.findElements(By.className("android.widget.TextView")).get(i).getText());
			}
			driver.findElement(CoPaysArrow).click();	

			
			driver.findElement(DeductibleArrow).click();	
			element = driver.findElement(DeductibleTable);
			for (int i = 0; i< element.findElements(By.className("android.widget.TextView")).size() ; i++) {
				System.out.println(element.findElements(By.className("android.widget.TextView")).get(i).getText());
			}
			driver.findElement(DeductibleArrow).click();	

		
			driver.findElement(OutOfPocketMaximumArrow).click();	
			element = driver.findElement(OutOfPocketMaximumTable);
			for (int i = 0; i< element.findElements(By.className("android.widget.TextView")).size() ; i++) {
				System.out.println(element.findElements(By.className("android.widget.TextView")).get(i).getText());
			}
			driver.findElement(OutOfPocketMaximumArrow).click();	
			

			driver.findElement(OverallBenefitMaximumArrow).click();	
			element = driver.findElement(OverallBenefitMaximumTable);
			for (int i = 0; i< element.findElements(By.className("android.widget.TextView")).size() ; i++) {
				System.out.println(element.findElements(By.className("android.widget.TextView")).get(i).getText());
			}
			driver.findElement(OverallBenefitMaximumArrow).click();	

			

			softAssert.assertAll();

		
		
		//} catch (MalformedURLException e) {
			//System.out.println(e.getMessage());
		//}
	}
	
	@DataProvider(name = "MyPlan")
	public Object[][] data() {
		
		//logger.info("setting up data provider for My Blue App");
		setUpInputData();
		Sheet sheet = workbook.getSheet("MyPlan");

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
	
	//public void waitForElement(By by, int time) {
		//WebDriverWait wait = new WebDriverWait(driver, time);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	//}
	
	public static void setUpInputData() {
		//logger.info("setting up input data for My Blue automation");

		try {
			workbook = WorkbookFactory.create(MyPlan.class.getResourceAsStream("/MyBlue_DataSheet.xlsx"));

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
	
	
	
	

	

}