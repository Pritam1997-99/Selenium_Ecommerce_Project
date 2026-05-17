package testsScripts;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;

public class WaitPractice {
	WebDriver driver=new ChromeDriver();
	@Test
	public void fluentWaitPractice() {
	
	Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(10))
			.pollingEvery(Duration.ofSeconds(2))
			.ignoring(NoSuchElementException.class);
	wait.until(ExpectedConditions.visibilityOf(null));
	
	}
	@Test
	public void explicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.pollingEvery(Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(null));
	}
}
