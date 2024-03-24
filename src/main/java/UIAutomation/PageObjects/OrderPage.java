package UIAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UIAutomation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	public WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody/tr/th/following-sibling::td[2]")
	List<WebElement> orderedProduct;
	
	By orderedList = By.xpath("//tbody/tr/th/following-sibling::td[2]");
	
	public boolean verifyProductInOrderList(String product)
	{
		waitForElementToVisible(5, orderedList);
		boolean flag = orderedProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(product));
		return flag;
	}
	

}
