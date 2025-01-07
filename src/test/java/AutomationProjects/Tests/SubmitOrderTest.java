package AutomationProjects.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationProjects.TestComponents.BaseTest;
import AutomationProjects.pageobjects.CartPage;
import AutomationProjects.pageobjects.CheckoutPage;
import AutomationProjects.pageobjects.ConfirmationPage;
import AutomationProjects.pageobjects.OrdersPage;
import AutomationProjects.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrder(String email, String password, String productName, String country) throws IOException, InterruptedException
	{
		
		ProductCatalogue productcatalogue = landingPage.loginToApplication(email,password);	
		productcatalogue.addProductToCart(productName);
		CartPage cartPage = productcatalogue.gotoCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Thread.sleep(5000);
		String confirmMessage = confirmationPage.getConfirmationMessage();		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(" THANKYOU FOR THE ORDER. "));
		
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyOrderHistory()
	{
		ProductCatalogue productcatalogue = landingPage.loginToApplication("sreenath.msc@gmail.com", "Vinnu@828");
		OrdersPage orderpage = productcatalogue.gotoOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
		
		
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"sreenath.msc@gmail.com","Vinnu@828","ZARA COAT 3","india"}};
		//,{"sreenath.msc@gmail.com","Vinnu@828","ADIDAS ORIGINAL","india"}
	}
	
	
	@Test(dataProvider="feedData",groups="Purchase1")
	public void submitOrder2(HashMap<String, String> input) throws IOException, InterruptedException
	{
		
		ProductCatalogue productcatalogue = landingPage.loginToApplication(input.get("email"),input.get("password"));	
		productcatalogue.addProductToCart(input.get("productname"));
		CartPage cartPage = productcatalogue.gotoCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry(input.get("country"));
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	@DataProvider
	public Object[][] feedData() throws IOException
	{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//AutomationProjects//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	/*HashMap<String , String> map1 = new HashMap<String, String>();
	map1.put("email", "sreenath.msc@gmail.com");
	map1.put("password", "Vinnu@828");
	map1.put("productname", "ZARA COAT 3");
	map1.put("country", "india");
	
	HashMap<String , String> map2 = new HashMap<String, String>();
	map2.put("email", "sreenath.msc@gmail.com");
	map2.put("password", "Vinnu@828");
	map2.put("productname", "ADIDAS ORIGINAL");
	map2.put("country", "india");*/
}
