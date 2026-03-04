package AlightWealth.TestComponents;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AlightWealth.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EndToEndTestScriptUsedInPageObject extends BaseTest {
	@Test
	public void testCase() throws IOException {
		
		String productName = "Automation 8";
		
		//Landing page
		WebDriver driver= InvokeBrowser();
		LandingPage page=landingPage();
		page.login("abcselenium@123.com", "Selenium@123");
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("abcselenium@123.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@123");
		driver.findElement(By.id("login")).click();
		//landing page code ends here
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		for (int i = 0; i < products.size(); i++) {
			WebElement prod = products.get(i);
			if (prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)) {

				prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();

				break;
			}
		}

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary'] /parent::li")));
		driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']/ul/li/button")).click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btnn
		// action__submit ng-star-inserted']")));
		Actions a = new Actions(driver);
		a.click(driver.findElement(By.xpath("//*[@class='input txt text-validated']/parent::div[@class='form-group']")))
				.build().perform();
		a.sendKeys("India").build().perform();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		// List<WebElement>
		// options=driver.findElements(By.xpath("//section[@class='ta-results list-group
		// ng-star-inserted']"));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		// driver.findElement(By.xpath("//*[@class='input txt
		// text-validated']/parent::div[@class='form-group']")).click();
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'btnn')]")));
		a.moveToElement(driver.findElement(By.xpath("//a[contains(@class,'btnn')]"))).click().build().perform();
		//driver.findElement(By.xpath("//a[contains(@class,'btnn')]")).click();
		String orderConfirm = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirm.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}

}
