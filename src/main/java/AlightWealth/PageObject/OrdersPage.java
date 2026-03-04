package AlightWealth.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AlightWealth.AbstractComponent.AbstractFunctions;

public class OrdersPage extends AbstractFunctions {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//thead[@class='thead-dark']/following-sibling::tbody/tr/td[2]")
	List<WebElement> ordersList;

	public boolean verifyProductPresentInOrdersPage() {
		goToOrdersPage();

		for (int i = 0; i < ordersList.size(); i++) {
			if (ordersList.get(i).getText().equalsIgnoreCase("ADIDAS ORIGINAL")) {
				return true;
			}
		}
		return false;
	}

}
