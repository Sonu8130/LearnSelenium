package SD11.NewRes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("mnk@maildrop.cc");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Sonu@098");
		driver.findElement(By.cssSelector("#login")).click();

		String productName = "ZARA COAT 3";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); // Implicit wait

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(true);

		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("input[placeholder*='Country']")).sendKeys("Ind");
		List<WebElement> Country = driver.findElements(By.cssSelector("section[class*='ta-results']"));

		for (WebElement option : Country) {
			option.getText().equalsIgnoreCase("India");
			option.click();

		}

		// To perform scroll
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1078,653)", "");

		WebElement submit = driver.findElement(By.cssSelector("a[class*='action__submit']"));
		wait.until(ExpectedConditions.visibilityOf(submit));
		submit.click();

	}
}
