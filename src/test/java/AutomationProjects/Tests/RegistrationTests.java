package AutomationProjects.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationProjects.TestComponents.BaseTest;

public class RegistrationTests extends BaseTest{
	
	
	@Test
	public void VerifyFirstNameErrorMessage()
	{
		landingPage.GotoRegPage();
		landingPage.clickRegistrationButton();
		Assert.assertEquals(landingPage.getFirstNameErrorText(), "*First Name iss required");
	}
	
	
	

}
