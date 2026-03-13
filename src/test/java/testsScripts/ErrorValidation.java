package testsScripts;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AlightWealth.PageObject.FinalPage;
import AlightWealth.PageObject.PaymentPage;
import AlightWealth.PageObject.ProductCatalogue;
import AlightWealth.PageObject.ProductCheckOut;
import AlightWealth.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest {
	@Test(groups="Regression")
	public void incorrectCredentials() throws IOException, InterruptedException {
	
		page.incorrectLogin("abcseleniu@123.com", "Selenium@123");
		boolean value = page.errorMsgValidation();
		Assert.assertTrue(value);
		
	}
	
	@Test(groups="Regression")
	public void incorrectProductValidation() throws InterruptedException {
		String productName="ADIDAS DUPLICATE";
		ProductCatalogue listOfProducts = page.login("lalit.123@gmail.com", "Demo@123");
		WebElement val = listOfProducts.getDesiredProductName(productName);
		Assert.assertEquals(val, null);
	}

}
