package AutomationProjects.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver); // every child page need to serve driver to parent class if the class is inheriting from parent.
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(xpath="//div[contains(@class,'loading-text')]")
	WebElement spinner;
	
	
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By ToastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter
		(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productname)
	{
		WebElement prod=getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(ToastMessage);
		waitForElementToDisappear(spinner);
	}
	
	

}
