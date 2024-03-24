package UIAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.AbstractComponents.AbstractComponent;

public class ProductCataloge extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCataloge(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".card-body")
	List<WebElement> card;
	
	By cards = By.cssSelector(".card-body");
	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");
	
	public List<WebElement> getListOfCards()
	{
		waitForElementToVisible(5, cards);
		return card;
	}
	
	public WebElement getCardElement(String product)
	{
		WebElement productName = getListOfCards().stream().filter(s->
		s.findElement(By.tagName("b")).getText().equals(product)).findFirst().orElse(null);
		return productName;
	}
	
	public void addProductToCart(String product)
	{
		getCardElement(product).findElement(addToCartButton).click();
		waitForElementToVisible(5, toastMsg);
		waitForElementToInvisible(5, spinner);
		
	}
}
