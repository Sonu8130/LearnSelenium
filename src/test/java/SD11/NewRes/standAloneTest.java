package SD11.NewRes;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SD11.NewRes.PageObjects.CartPage;
import SD11.NewRes.PageObjects.Checkout;
import SD11.NewRes.PageObjects.ConfirmationPage;
import SD11.NewRes.PageObjects.LandingPage;
import SD11.NewRes.PageObjects.ProductCatalogue;
import SD11.NewRes.abstractComponent.abstractComponent;
import SD11.NewRes.testComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest extends BaseTest {
	WebDriver driver;

	@Test
	public void submitOrderTest() throws IOException {
		String productName = "ZARA COAT 3";
		String countryName = "India";

		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("mnk@maildrop.cc", "Sonu@098");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.selectProductByName(productName);
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		Checkout checkout = cartPage.goToCheckout();
		checkout.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkout.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
}
