package environment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class Synchronization {
	
	public void waitForElementToVisible(final By by, int waitTime, AppiumDriver<WebElement> driver ) {
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    for (int attempt = 0; attempt < waitTime; attempt++) {
	        try {
	            driver.findElement(by);
	            break;
	        } catch (Exception e) {
	            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	        }
	    }
	    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

}
