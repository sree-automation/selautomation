package AutomationProjects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);//super keyword will pass the driver to parent class from child class
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement btnLogin;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	@FindBy(linkText="Register")
	WebElement btn_Register;
	
	@FindBy(id="firstName")
	WebElement txt_FirstName;
	
	@FindBy(id="lastName")
	WebElement txt_LastName;
	
	@FindBy(id="userEmail")
	WebElement txt_Email;
	
	@FindBy(id="userMobile")
	WebElement txt_PhoneNumber;
	
	@FindBy(xpath="//select[@formcontrolname='occupation']")
	WebElement dropdown_SelectOccupation;
	
	@FindBy(xpath="//input[@value='Male']")
	WebElement btn_GenderMale;
	
	@FindBy(xpath="//input[@value='Female']")
	WebElement btn_GenderFemale;
	
	@FindBy(id="userPassword")
	WebElement txt_Password;
	
	@FindBy(id="confirmPassword")
	WebElement txt_ConfirmPassword;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement chk_AgeConfirmationCheckbox;
	
	@FindBy(xpath="//input[@value='Register']")
	WebElement btn_RegisterConfirm;
	
	@FindBy(xpath="//*[@id='firstName']/../div/div")
	WebElement ErrorTextFirstName;
	
	@FindBy(xpath="//*[@id='userEmail']/../div/div")
	WebElement ErrorTextEmail;
	
	@FindBy(xpath="//*[@id='userMobile']/../div/div")
	WebElement ErrorTextMobile;
	
	@FindBy(xpath="//*[@id='userPassword']/../div/div")
	WebElement ErrorTextPassword;
	
	@FindBy(xpath="//*[@id='confirmPassword']/../div/div")
	WebElement ErrorTextCofirmPassword;
	
	@FindBy(xpath="//input[@type='checkbox']/../../div[3]/div")
	WebElement ErrorTextCheckBox;
	

	public ProductCatalogue loginToApplication(String usermail,String userpassword)
	{
		userEmail.sendKeys(usermail);
		password.sendKeys(userpassword);
		btnLogin.click();
		driver.manage().window().maximize();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public void gotoURL()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void GotoRegPage()
	{
		btn_Register.click();
	}
	
	public void clickRegistrationButton()
	{
		btn_RegisterConfirm.click();  
	}
	
	public String getFirstNameErrorText()
	{
		return ErrorTextFirstName.getText();
	}
}
