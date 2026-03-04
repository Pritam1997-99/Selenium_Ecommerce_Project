package testsScripts;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AlightWealth.PageObject.FinalPage;
import AlightWealth.PageObject.LandingPage;
import AlightWealth.PageObject.OrdersPage;
import AlightWealth.PageObject.PaymentPage;
import AlightWealth.PageObject.ProductCatalogue;
import AlightWealth.PageObject.ProductCheckOut;
import AlightWealth.TestComponents.BaseTest;

public class TestCase1 extends BaseTest {
	@Test
	public void submitOrder() throws IOException, InterruptedException {
	
		String productName="ADIDAS ORIGINAL";
		ProductCatalogue listOfProducts = page.login("abcselenium@123.com", "Selenium@123");
		List<WebElement> productsList = listOfProducts.getProductList();
		WebElement desiredProduct = listOfProducts.getDesiredProductName(productName);
		listOfProducts.selectDesiredProduct(productName);
		page.goToCart();
		ProductCheckOut checkOut = new ProductCheckOut(driver);
		checkOut.getCartProducts();
		Assert.assertEquals(checkOut.verifyDesiredProduct(productName), productName);
		PaymentPage paymentPage = checkOut.clickAndCheckOut();
		paymentPage.selectCountry("Ind");
		FinalPage finalPage = paymentPage.placeOrder();
		String confirmText = finalPage.orderConfirmation();
		Assert.assertEquals(confirmText, "THANKYOU FOR THE ORDER.");
		
	}
	@Test
	public void verifyOrdersPage() {
		
		
		
	}

}
