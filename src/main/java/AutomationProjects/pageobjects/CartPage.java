package AutomationProjects.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);//super keyword will pass the driver to parent class from child class
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	private List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkoutbutton;
	
	
	public Boolean VerifyProductDisplay(String productName) throws InterruptedException
	{
		Thread.sleep(5000);
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}


	public CheckoutPage goToCheckoutPage() {
		// TODO Auto-generated method stub
		checkoutbutton.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
	
	
	
}
