package SD11.NewRes.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SD11.NewRes.abstractComponent.abstractComponent;

public class Checkout extends abstractComponent {
	WebDriver driver;

	public Checkout(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By ele = By.cssSelector(".ta-results");

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	public void selectCountry(String Name) {
		Actions a = new Actions(driver);
		a.sendKeys(country, Name).build().perform();
		waitForElementToAppear(ele);
		selectCountry.click();
		scrollPage();

	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
}
