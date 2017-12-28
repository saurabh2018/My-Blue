package utility;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class AndroidUtility {
	
	public static void swipeVertical(AppiumDriver<WebElement> driver, double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
		   
		Dimension size = driver.manage().window().getSize();
	    int anchor = (int) (size.width * anchorPercentage);
	    int startPoint = (int) (size.height * startPercentage);
	    int endPoint = (int) (size.height * finalPercentage);
	    new TouchAction(driver).press(anchor, startPoint).waitAction(duration).moveTo(anchor, endPoint).release().perform();
	    

	}


}
