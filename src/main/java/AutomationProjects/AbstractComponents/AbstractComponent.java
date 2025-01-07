package AutomationProjects.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationProjects.pageobjects.CartPage;
import AutomationProjects.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(@routerlink,'cart')]")
	WebElement cartbutton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersbutton;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementToBeClicable(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public CartPage gotoCartPage() throws InterruptedException
	{
		Thread.sleep(10000);
		waitForElementToBeClicable(cartbutton);
		cartbutton.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
		
	}
	
	public OrdersPage gotoOrdersPage()
	{
		ordersbutton.click();
		OrdersPage orderpage=new OrdersPage(driver);
		return orderpage;
	}
	
}
