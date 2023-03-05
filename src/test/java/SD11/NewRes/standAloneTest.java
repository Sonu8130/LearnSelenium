package SD11.NewRes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Contata.TestComponents.BaseTest;
import SD11.NewRes.PageObjects.CartPage;
import SD11.NewRes.PageObjects.Checkout;
import SD11.NewRes.PageObjects.ConfirmationPage;
import SD11.NewRes.PageObjects.LandingPage;
import SD11.NewRes.PageObjects.OrderPage;
import SD11.NewRes.PageObjects.ProductCatalogue;

public class standAloneTest extends SD11.NewRes.testComponents.BaseTest {
	// WebDriver driver;
	String productName = "ZARA COAT 3";
	String countryName = "India";

	@Test(dataProvider = "getData")
	public void submitOrderTest(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = LandingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.selectProductByName(input.get("product"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		Checkout checkout = cartPage.goToCheckout();
		checkout.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkout.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

//	@Test(dependsOnMethods = { "submitOrderTest" })
//	public void OrderHistoryTest() {
//		ProductCatalogue productCatalogue = landingPage.loginApplication("mnk@maildrop.cc", "Sonu@098");
//		OrderPage orderPage = productCatalogue.goToOrderPage();
//		Assert.assertTrue(orderPage.verifyOrdersDisplay());
//	}

	@DataProvider
	Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\SD11\\NewRes\\data\\dataReader.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
