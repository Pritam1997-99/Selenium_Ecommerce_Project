package AlightWealth.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AlightWealth.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage page;
	
	public WebDriver InvokeBrowser() throws IOException {
		//Properties class
		Properties prp= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\AlightWealth\\data\\BaseTest.Properties");
		prp.load(fis);
		String browser= prp.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.out.println("Firefox Browser");
		}else if(browser.equalsIgnoreCase("edge")) {
			System.out.println("Edge browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
		
		//String content to HashMap Jackson Databind
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
	return data;
	}
	
	@BeforeMethod
	public LandingPage landingPage() throws IOException {
		driver=InvokeBrowser();
		page= new LandingPage(driver);
		page.goTo();
		return page;
	}
	
	@AfterMethod
	public void closeDriver() {
		driver.close();
	}
	
	}


