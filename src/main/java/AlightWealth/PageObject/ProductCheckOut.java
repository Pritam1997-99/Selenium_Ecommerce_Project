package AlightWealth.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AlightWealth.AbstractComponent.AbstractFunctions;

public class ProductCheckOut extends AbstractFunctions {
	WebDriver driver;
	public ProductCheckOut(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	@FindBy(xpath="(//button[@class='btn btn-primary'])[3]")
	WebElement checkOutBtn;
	
	By wait3=By.xpath("//div[@class='infoWrap']");
	
	
	public List<WebElement> getCartProducts() {
		waitForElementToAppeare(wait3);
		//List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		return cartProducts;
	}
	public String verifyDesiredProduct(String productName) {
		
		/*Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;*/
		for(WebElement prod:cartProducts) {
			if(prod.getText().equalsIgnoreCase(productName)) {
				return prod.getText();
			}
		}
		return null;
	}
	public PaymentPage clickAndCheckOut() {
		waitForElementToClickable(checkOutBtn);
		clickAButton(checkOutBtn);
		PaymentPage payment=new PaymentPage(driver);
		return payment;
	}

}
