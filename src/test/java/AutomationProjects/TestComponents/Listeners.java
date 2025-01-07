package AutomationProjects.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import AutomationProjects.Resources.ExtentReporterNG;

import java.io.IOException;

import org.testng.ITestContext;

public class Listeners extends BaseTest implements ITestListener {
	 
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getExtentReportObject();
	ThreadLocal<ExtentTest> threadsafe = new ThreadLocal<ExtentTest>();	
	
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		threadsafe.set(test); // It will create unique thread id
	  }

	public void onTestSuccess(ITestResult result) {
	    // not implemented
		threadsafe.get().log(Status.PASS, "Test is passed");
	  }

	
	public void onTestFailure(ITestResult result) {
		
		//test.fail(result.getThrowable());
		threadsafe.get().fail(result.getThrowable());//unique thread id will be takes place here once tests fail
		// Take screenshot in the method when test is failed
		//take screenshot and attach to report 
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threadsafe.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	public  void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	public void onStart(ITestContext context) {
	    // not implemented
	  }

	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }
	
	
}
