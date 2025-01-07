package AutomationProjects.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);//super keyword will pass the driver to parent class from child class
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".user__address input")
	WebElement country;	

	@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement submit;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectcounty;
	
	public void selectCountry(String countyname) throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.sendKeys(country, countyname).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results button"));
		Thread.sleep(5000);
		selectcounty.click();
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException
	{
		Thread.sleep(5000);
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	}
	
	
	

