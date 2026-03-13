package AlightWealth.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AlightWealth.AbstractComponent.AbstractFunctions;

public class PaymentPage extends AbstractFunctions{
	WebDriver driver;
	public  PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement textBox;
	@FindBy(xpath="//section[contains(@class,'ta-results')]/button[2]")
	WebElement ddOptions;
	@FindBy(xpath="//a[text()='Place Order ']/i")
	WebElement placeOrderButton;
	@FindBy(xpath="//section[starts-with(@class,'ta-results')]")
	WebElement dissapear;
	
	By wait=By.xpath("//div[@id=\"toast-container\"]");
	By wait2=By.xpath("//input[@placeholder='Select Country']");
	
	public void selectCountry(String country) {
		waitForElementToAppeare(wait2);
		textBox.sendKeys(country);
		waitForElementToAppeare(By.xpath("//section[contains(@class,'ta-results')]"));
		ddOptions.click();
	}
	public FinalPage placeOrder() throws InterruptedException {
		//waitForElementToClickable(placeOrderButton);
		waitForElementToDissappeare(dissapear);
		//placeOrderButton.click();
		clickAButton(placeOrderButton);
		//waitForElementToAppeare(wait);
		FinalPage finalpage=new FinalPage(driver);
		return finalpage;
	}
	

}
