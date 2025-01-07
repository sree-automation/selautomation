package AutomationProjects.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationProjects.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2 {
	
	public static void main(String[] args) throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage login=new LandingPage(driver);
		login.gotoURL();
		login.loginToApplication("sreenath.msc@gmail.com", "Vinnu@828");			
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Thread.sleep(5000);		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".subtotal button")).click();
		//driver.findElement(By.cssSelector(".user__address input")).sendKeys("ind");
		/*List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results button")); 
		WebElement country = countryList.stream().filter(countryName->countryName.findElement(By.cssSelector("button")).getText().equals(" India")).findFirst().orElse(null);*/
		Actions act=new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector(".user__address input")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results button")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".btnn")).click();
		//driver.findElement(By.cssSelector(".btnn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		//driver.quit();
	}

	
	
	
	

}
