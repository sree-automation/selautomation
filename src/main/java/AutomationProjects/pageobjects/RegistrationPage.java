package AutomationProjects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.AbstractComponents.AbstractComponent;

public class RegistrationPage extends AbstractComponent{

	WebDriver driver;
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
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
	
	
	
	
	
	
}
