package AlightWealth.TestComponents;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AlightWealth.PageObject.FinalPage;
import AlightWealth.PageObject.LandingPage;
import AlightWealth.PageObject.PaymentPage;
import AlightWealth.PageObject.ProductCatalogue;
import AlightWealth.PageObject.ProductCheckOut;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingWebsiteEndtoEnd {
	public static void main(String[] args) throws InterruptedException {
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\hp\\Downloads\\chromedriver-win64.zip\\chromedriver-win64\\chromedriver.exe");
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Landing page
		LandingPage page = new LandingPage(driver);
		page.goTo();
		ProductCatalogue listOfProducts = page.login("abcselenium@123.com", "Selenium@123");
		// landing page code ends here
		// ProductCatalogue listOfProducts=new ProductCatalogue(driver);
		List<WebElement> productsList = listOfProducts.getProductList();
		WebElement desiredProduct = listOfProducts.getDesiredProductName(productName);
		listOfProducts.selectDesiredProduct(productName);

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));

		page.goToCart();
		ProductCheckOut checkOut = new ProductCheckOut(driver);
		checkOut.getCartProducts();
		Assert.assertEquals(checkOut.verifyDesiredProduct(productName), productName);

		PaymentPage paymentPage = checkOut.clickAndCheckOut();
		paymentPage.selectCountry("Ind");
		FinalPage finalPage = paymentPage.placeOrder();
		String confirmText = finalPage.orderConfirmation();
		Assert.assertEquals(confirmText, "THANKYOU FOR THE ORDER.");

	}

}
