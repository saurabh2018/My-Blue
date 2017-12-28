package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;

public class EnvironmentUtility {
	
	
	public static void tap(WebElement el) throws InterruptedException {
		
		int leftX = el.getLocation().getX();
		System.out.println(leftX);
		int rightX = leftX + el.getSize().getWidth();
		System.out.println(rightX);
		int middleX = (rightX + leftX) / 2;
		System.out.println(middleX);
		int upperY = el.getLocation().getY();
		System.out.println(upperY);
		int lowerY = upperY + el.getSize().getHeight();
		System.out.println(lowerY);
		int middleY = (upperY + lowerY) / 2;
		System.out.println(middleY);
		//TouchAction touchAction=new TouchAction(driver);
		//touchAction.tap(705, 2384).perform();
		//touchAction.tap(middleX, middleY).perform();
		Thread.sleep(5000);
		//driver.findElement(skip).click();
		//driver.findElement(skip).sendKeys(Keys.ENTER);
		//if (! driver.findElements(skip).isEmpty()) {
			//driver.findElement(skip).click();
		//}
		
	}
	
}
