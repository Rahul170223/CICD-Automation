package UIAutomation.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//input[@placeholder='Select Country']")
	WebElement searchCountryTextBox;
	
	@FindBy(xpath="//section[contains(@class, 'ta-results')]//button[2]")
	WebElement countryOption;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;
	
	public void selectCountry(String country)
	{
		Actions a = new Actions(driver);
		a.sendKeys(searchCountryTextBox, country).build().perform();
		countryOption.click();
	}
	
	public ConfirmationPage clickOnPlaceOrderButton()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", placeOrderButton);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
	}


}
