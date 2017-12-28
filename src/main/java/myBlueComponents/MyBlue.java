package myBlueComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import environment.Synchronization;
import io.appium.java_client.AppiumDriver;

public class MyBlue {
	
	static Synchronization synchronization = new Synchronization();		

	
	public static void ClickHamburgerButton(AppiumDriver<WebElement> driver) {
		
		By HamburgerButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView");
		driver.findElement(HamburgerButton).click();
		
		
	}
	
	public static void ClickMyClaimsUnderHamburgerMenu(AppiumDriver<WebElement> driver) {
		
		By MyClaimsButton = By.xpath("//android.widget.Button[@text = 'My Claims']");
		synchronization.waitForElementToVisible(MyClaimsButton, 60, driver);
		driver.findElement(MyClaimsButton).click();
		
	}
	
	public static void ClickMyCardsUnderHamburgerMenu(AppiumDriver<WebElement> driver) {
		
		By MyCardsButton = By.xpath("//android.widget.Button[@text = 'My Cards']");
		driver.findElement(MyCardsButton).click();
		
	}
	
    public static void ClickHomeUnderHamburgerMenu(AppiumDriver<WebElement> driver) {
		
		By HomeButton = By.xpath("//android.widget.Button[@text = 'Home']");
		driver.findElement(HomeButton).click();
		
	}
    
	
    public static void ClickMyDoctorsUnderHamburgerMenu(AppiumDriver<WebElement> driver) {
		
		By MyDoctorsButton = By.xpath("//android.widget.Button[@text = 'My Doctors']");
		driver.findElement(MyDoctorsButton).click();
		
	}
    
    public static void ClickMyMedicationsUnderHamburgerMenu(AppiumDriver<WebElement> driver) {
		
		By MyMedicationsButton = By.xpath("//android.widget.Button[@text = 'My Medications']");
		driver.findElement(MyMedicationsButton).click();
		
	}
   
    public static void ClickMyPlanUnderHamburgerMenu(AppiumDriver<WebElement> driver) {
		
		By MyPlanButton = By.xpath("//android.widget.Button[@text = 'My Plan']");
		driver.findElement(MyPlanButton).click();
		
	}
    
    public static void ClickSignOutUnderHamburgerMenu(AppiumDriver<WebElement> driver) {
		
		By SignOutButton = By.xpath("//android.widget.TextView[@text = 'Sign Out']");
		driver.findElement(SignOutButton).click();
		
	}
    
    public static void ClickMyBlueBackButton(AppiumDriver<WebElement> driver) {
    	
    	By BackButton = By.xpath("//android.widget.ImageButton");
		driver.findElement(BackButton).click();
		
    	
    }


}
