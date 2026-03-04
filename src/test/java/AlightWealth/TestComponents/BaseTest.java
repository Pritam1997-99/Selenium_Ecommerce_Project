package AlightWealth.TestComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AlightWealth.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage page;
	
	public WebDriver InvokeBrowser() throws IOException {
		//Properties class
		Properties prp= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\AlightWealth\\resources\\BaseTest.Properties");
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


