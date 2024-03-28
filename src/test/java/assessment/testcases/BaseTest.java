package assessment.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import assessment.pageobjects.DestinationPage;
import assessment.pageobjects.HomePage;
import assessment.pageobjects.PurchasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;
	HomePage homepage;
	DestinationPage destinationpage;
	PurchasePage purchasepage;
	
	public WebDriver browserInvokation() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\user\\eclipse-workspace\\blazedemo\\src\\main\\java\\assessment\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("safari")){
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			
		}
		
		return driver;
	}
	
	public void goTo() {
		driver.get("https://blazedemo.com/index.php");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void launchApplication() throws IOException {
		driver = browserInvokation();
		goTo();
		homepage = new HomePage(driver);
		destinationpage = new DestinationPage(driver);
		purchasepage = new PurchasePage(driver);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
