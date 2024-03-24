package UIAutomation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	By message = By.cssSelector("[class*='flyInOut']");
	
	public ProductCataloge loginToApplication(String email, String password)
	{
		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		loginButton.click();
		ProductCataloge productCataloge = new ProductCataloge(driver);
		return productCataloge;
	}
	
	public void openUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String getErrorMsg()
	{
		String msg = errorMsg.getText();
		waitForElementToVisible(5, message);
		return msg;
	}

}
