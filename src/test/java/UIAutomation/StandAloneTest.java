package UIAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String product = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("rahulSingh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("New1@Life");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> card = driver.findElements(By.cssSelector(".card-body"));
		
		WebElement productName = card.stream().filter(s->
		s.findElement(By.tagName("b")).getText().equals(product)).findFirst().orElse(null);
		
		productName.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("button[routerlink*='dashboard/cart']")).click();
		
		List<WebElement> cartProduct = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		boolean flag = cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
		Assert.assertTrue(flag);
		driver.findElement(By.xpath("//div[contains(@class, subtotal)]/ul/li/button")).click();
		
		Actions a = new Actions(driver);
		WebElement searchCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		a.sendKeys(searchCountry, "India").build().perform();
		
		driver.findElement(By.xpath("//section[contains(@class, 'ta-results')]//button[2]")).click();
		
		Thread.sleep(3000);
		WebElement placeOrderButton = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", placeOrderButton);
		
		String successMsg  = driver.findElement(By.tagName("h1")).getText();
		Assert.assertTrue(successMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		
	}

}
