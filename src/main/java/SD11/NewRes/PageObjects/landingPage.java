package SD11.NewRes.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SD11.NewRes.abstractComponent.abstractComponent;

public class LandingPage extends abstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#userEmail")
	WebElement userEmail;

	@FindBy(css = "#userPassword")
	WebElement userPassword;

	@FindBy(css = "#login")
	WebElement submit;

	public ProductCatalogue loginApplication(String Email, String password) {
		userEmail.sendKeys(Email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}