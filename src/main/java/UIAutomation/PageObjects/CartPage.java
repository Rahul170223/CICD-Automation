package UIAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProduct;
	
	@FindBy(xpath="//div[contains(@class, subtotal)]/ul/li/button")
	WebElement checkOutButton;
	
	
	public boolean verifyProductInCart(String product)
	{
		boolean flag = cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
		return flag;
	}
	
	public CheckOutPage goToCheckOutPage()
	{
		checkOutButton.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
