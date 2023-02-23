package SD11.NewRes.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class landingPage {
	WebDriver driver;
	
	
	public landingPage(WebDriver driver){
		
		this.driver= driver;
	}

	WebElement userEmail = driver.findElement(By.cssSelector("#userEmail"));
	
	
}
