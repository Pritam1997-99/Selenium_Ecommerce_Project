package testsScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AlightWealth.PageObject.FinalPage;
import AlightWealth.PageObject.LandingPage;
import AlightWealth.PageObject.OrdersPage;
import AlightWealth.PageObject.PaymentPage;
import AlightWealth.PageObject.ProductCatalogue;
import AlightWealth.PageObject.ProductCheckOut;
import AlightWealth.TestComponents.BaseTest;

public class TestCase1 extends BaseTest {
	//String productName="ADIDAS ORIGINAL";
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
	
		
		ProductCatalogue listOfProducts = page.login(input.get("email"), input.get("password"));
		List<WebElement> productsList = listOfProducts.getProductList();
		WebElement desiredProduct = listOfProducts.getDesiredProductName(input.get("productName"));
		listOfProducts.selectDesiredProduct(input.get("productName"));
		page.goToCart();
		ProductCheckOut checkOut = new ProductCheckOut(driver);
		checkOut.getCartProducts();
		Assert.assertEquals(checkOut.verifyDesiredProduct(input.get("productName")), input.get("productName"));
		PaymentPage paymentPage = checkOut.clickAndCheckOut();
		paymentPage.selectCountry("Ind");
		FinalPage finalPage = paymentPage.placeOrder();
		String confirmText = finalPage.orderConfirmation();
		Assert.assertEquals(confirmText, "THANKYOU FOR THE ORDER.");
		
	}
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider="getData")
	public void verifyOrdersPage(HashMap<String,String> input) {
		
		page.login(input.get("email"), input.get("password"));
		OrdersPage orders=new OrdersPage(driver);
		boolean result = orders.verifyProductPresentInOrdersPage(input.get("productName"));
		Assert.assertTrue(result);
		
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AlightWealth//data//Credentials.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		//return new Object[][] {{"abcselenium@123.com","Selenium@123","ADIDAS ORIGINAL"},{"lalit.123@gmail.com","Demo@123","ADIDAS ORIGINAL"}};
	}

}
