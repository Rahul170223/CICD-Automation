package UIAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import UIAutomation.PageObjects.CartPage;
import UIAutomation.PageObjects.CheckOutPage;
import UIAutomation.PageObjects.ConfirmationPage;
import UIAutomation.PageObjects.LandingPage;
import UIAutomation.PageObjects.OrderPage;
import UIAutomation.PageObjects.ProductCataloge;
import UIAutomation.TestComponenets.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String product = "ADIDAS ORIGINAL";
	@Test
	public void submitOrder() throws IOException {
		
		ProductCataloge productCataloge = landingPage.loginToApplication("rahulSingh@gmail.com", "New1@Life");

		productCataloge.addProductToCart(product);
		CartPage cartPage = productCataloge.goToCartPage();
		boolean flag = cartPage.verifyProductInCart(product);
		Assert.assertTrue(flag);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();

		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.clickOnPlaceOrderButton();
		String successMsg = confirmationPage.confirmationMsg();
		Assert.assertTrue(successMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyProductInOrderList()
	{
		ProductCataloge productCataloge = landingPage.loginToApplication("rahulSingh@gmail.com", "New1@Life");
		OrderPage orderPage = productCataloge.goToOrderPage();
		boolean actualProduct = orderPage.verifyProductInOrderList(product);
		Assert.assertTrue(actualProduct);
		
	}
}