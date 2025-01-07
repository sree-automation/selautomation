package AutomationProjects.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationProjects.TestComponents.BaseTest;
import AutomationProjects.TestComponents.RetryFlakyTests;
import AutomationProjects.pageobjects.CartPage;

import AutomationProjects.pageobjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest{
	
	@Test(dataProvider ="setData", groups={"ErrorHandling"}, retryAnalyzer=RetryFlakyTests.class)
	public void LoginErrorValidation(String email, String password) throws IOException, InterruptedException
	{
		landingPage.loginToApplication(email, password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingPage.loginToApplication("sreenath.msc@gmail.com", "Vinnu@828");	
		productcatalogue.addProductToCart(productName);
		Thread.sleep(5000);		
		CartPage cartPage = productcatalogue.gotoCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] setData()
	{
		return new Object[][] {{"sreenath.msc@gmail.com","password"},{"sreenath.msc@gmail.com","Vinnu@828"}};
	}
	
}
