package AlightWealth.AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AlightWealth.PageObject.OrdersPage;

public class AbstractFunctions {
	WebDriver driver;
	public AbstractFunctions(WebDriver driver) {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver=driver;
		
	}
	@FindBy(css=".ng-animating")
	WebElement cartWait;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement clckCart;
	
	@FindBy(xpath="(//button[contains(@class,'btn-custom')])[2]")
	WebElement orders;
	
	By cartWait2=By.id("toast-container");
	

	public void waitForElementToAppeare(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToDissappeare(WebElement ele) throws InterruptedException {
		Thread.sleep(3000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitForElementToClickable(WebElement clc) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(clc));
	}
	public void goToCart() throws InterruptedException {
		waitForElementToDissappeare(cartWait);
		//waitForElementToAppeare(cartWait2);
		//waitForElementToClickable(clckCart);
		clckCart.click();
		
	}
	public void clickAButton(WebElement elementToClick) {
		Actions a= new Actions(driver);
		a.click(elementToClick).build().perform();;
	}
	public void goToOrdersPage() {
		orders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		
	}

}
