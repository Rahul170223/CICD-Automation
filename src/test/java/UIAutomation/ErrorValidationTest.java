package UIAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import UIAutomation.PageObjects.CartPage;
import UIAutomation.PageObjects.CheckOutPage;
import UIAutomation.PageObjects.ConfirmationPage;
import UIAutomation.PageObjects.LandingPage;
import UIAutomation.PageObjects.ProductCataloge;
import UIAutomation.TestComponenets.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	@Test
	public void loginErrorValidation() throws IOException {
		
		landingPage.loginToApplication("rahulSingh@gmail.com", "new life");
		String errorMsg = landingPage.getErrorMsg();
		Assert.assertEquals("Incorrect email or password.", errorMsg);
	}
	
	@Test(retryAnalyzer = UIAutomation.TestComponenets.Retry.class)
	public void productErrorValidation()
	{
		String product = "ADIDAS ORIGINAL";

		ProductCataloge productCataloge = landingPage.loginToApplication("shalinisingh@gmail.com", "New1@Life");

		productCataloge.addProductToCart(product);
		CartPage cartPage = productCataloge.goToCartPage();
		boolean flag = cartPage.verifyProductInCart("ADIDAS ORIGINAlll");
		Assert.assertTrue(flag);
	}
}