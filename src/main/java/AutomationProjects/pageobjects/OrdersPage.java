package AutomationProjects.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);//super keyword will pass the driver to parent class from child class
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productnames;
	

	
	public Boolean VerifyOrderDisplay(String productName) 
	{
		Boolean match = productnames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
