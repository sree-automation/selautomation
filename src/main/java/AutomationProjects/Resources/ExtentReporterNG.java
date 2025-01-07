package AutomationProjects.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getExtentReportObject()
	{
		//ExtentSparkReporter, ExtentReports - These two class we will user to configure extent reports
				String filePath = System.getProperty("user.dir")+"\\reports\\index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
				reporter.config().setReportName("Web Automation Test Rsults");
				reporter.config().setDocumentTitle("Smoke Test Results");
				
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Sreenath");
				//extent.createTest(filePath);
				return extent;
				
	}

}
