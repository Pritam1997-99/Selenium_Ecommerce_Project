package AlightWealth.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AlightWealth.AbstractComponent.AbstractFunctions;

public class LandingPage extends AbstractFunctions {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement emailId=driver.findElement(By.id("userEmail"))
	//driver.findElement(By.id("userPassword"))
	//PageFactory
	@FindBy(id="userEmail")	
	WebElement emailId;
	
	@FindBy(id="userPassword")
	WebElement userPswd;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errMsg;
	
	By errWait=By.cssSelector("[class*='flyInOut']");
	
	public ProductCatalogue login(String email,String password) {
		emailId.sendKeys(email);
		userPswd.sendKeys(password);
		loginButton.click();
		ProductCatalogue productList=new ProductCatalogue(driver);
		return productList;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	public void incorrectLogin(String email,String password) {
		emailId.sendKeys(email);
		userPswd.sendKeys(password);
		loginButton.click();
	}
	public boolean errorMsgValidation() {
		waitForElementToAppeare(errWait);
		if(errMsg.getText().equalsIgnoreCase("Incorrect email or Password.")) {
			return true;
		}
		return false;
	}
}
