package AlightWealth.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AlightWealth.AbstractComponent.AbstractFunctions;

public class ProductCatalogue extends AbstractFunctions {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[starts-with(@class,'col-lg-4')]")
	List<WebElement> prod;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By waitBy = By.xpath("//div[starts-with(@class,'col-lg-4')]");
	// WebElement
	// need=prod.findElement(By.xpath("//div[@class='card-body']/button[2]"));
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppeare(waitBy);
		List<WebElement> products = prod;
		return products;
	}

	public WebElement getDesiredProductName(String productName) {

		List<WebElement> products = getProductList();
		for (WebElement prod : products) {
			WebElement ele=prod.findElement(By.xpath("//div[@class='card-body']"));
			String val=ele.findElement(By.xpath("//h5/b")).getText();
			if (val.equalsIgnoreCase(productName)) {
				return ele;
			}

		}
		return null;
		/*WebElement prod=products.stream().filter(p->
		p.findElement(By.xpath("//b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;*/
	}

	public void selectDesiredProduct(String productName) throws InterruptedException {
		WebElement prod = getDesiredProductName(productName);
		prod.findElement(By.xpath("//button[2]")).click();
		waitForElementToAppeare(toastMessage);
		waitForElementToDissappeare(spinner);
		goToCart();

	}

}
