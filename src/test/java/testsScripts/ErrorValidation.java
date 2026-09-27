package testsScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import AlightWealth.PageObject.FinalPage;
import AlightWealth.PageObject.PaymentPage;
import AlightWealth.PageObject.ProductCatalogue;
import AlightWealth.PageObject.ProductCheckOut;
import AlightWealth.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest {
	
	@Test(groups="Regression", dataProvider="getInvalidData")
	public void incorrectCredentials(HashMap<String,String> input) throws IOException, InterruptedException {
	
		page.incorrectLogin(input.get("email"), input.get("password"));
		boolean value = page.errorMsgValidation();
		Assert.assertTrue(value);
		
	}
	
	@Test(groups="Regression",dataProvider="getInvalidProductName")
	public void incorrectProductValidation(HashMap<String,String> input) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		String productName=input.get("productName");
		ProductCatalogue listOfProducts = page.login(input.get("email"), input.get("password"));
		WebElement val = listOfProducts.getDesiredProductName(productName);
		//Assert.assertEquals(val, null);
		softAssert.assertEquals(val, null);
		softAssert.assertAll();
		
	}
	
	
	@DataProvider
	public Object[][] getInvalidData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AlightWealth//data//InvalidCredentials.json");
		
		//return new Object[][] {{data.get(0)},{data.get(1)}};
		Object[][] result = new Object[data.size()][];

		for (int i = 0; i < data.size(); i++) {
		    result[i] = new Object[]{data.get(i)};
		}

		return result;
		//return new Object[][] {{"abcselenium@123.com","Selenium@123","ADIDAS ORIGINAL"},{"lalit.123@gmail.com","Demo@123","ADIDAS ORIGINAL"}};
	}
	@DataProvider
	public Object[][] getInvalidProductName() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AlightWealth//data//InvalidProductName.json");
		
		//return new Object[][] {{data.get(0)},{data.get(1)}};
		Object[][] result = new Object[data.size()][];

		for (int i = 0; i < data.size(); i++) {
		    result[i] = new Object[]{data.get(i)};
		}

		return result;
		//return new Object[][] {{"abcselenium@123.com","Selenium@123","ADIDAS ORIGINAL"},{"lalit.123@gmail.com","Demo@123","ADIDAS ORIGINAL"}};
	}

}
