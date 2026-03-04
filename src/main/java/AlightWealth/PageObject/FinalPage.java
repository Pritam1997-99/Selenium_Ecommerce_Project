package AlightWealth.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AlightWealth.AbstractComponent.AbstractFunctions;

public class FinalPage extends AbstractFunctions {
	WebDriver driver;
	public FinalPage(WebDriver driver ) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement validationText;
	
	public String orderConfirmation() {
		return validationText.getText();
		
	}

}
