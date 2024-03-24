package UIAutomation.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import UIAutomation.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
//		Properties prop = new Properties();
//		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\UIAutomation\\Resources\\Global.properties");
//		prop.load(fis);
		
//		String browserType = prop.getProperty("browser");
//		if(browserType =="chrome")
//		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
//		}
//		
//		else if(browserType == "fireFox")
//		{
//			
//		}
//		
//		else if(browserType == "edge")
//		{
//			
//		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
		
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException
	{
		initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.openUrl();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	

}
