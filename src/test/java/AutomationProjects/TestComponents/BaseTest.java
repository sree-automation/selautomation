package AutomationProjects.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationProjects.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AutomationProjects\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		//This below statement executes based browser name provided in maven commant in terminal. If user provides browser name in mvn command then 
		//that browser will open otherwise it will take browser value from GlobalData.properties file
		// command: project source> mvn test -PPurchase Dbrowser=firefox
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browsername=prop.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	@BeforeTest (alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.gotoURL();
		driver.manage().window().maximize();
		return landingPage;
	}
	
	@AfterTest(alwaysRun = true)
	public void teardown() throws InterruptedException
	{
		driver.quit();
	}
	
	
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
			//Convert json into one string
			String jsoncontent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			
			//Convert string into Hashmap using Jackson databind (add jackson databind dependancy to pom.xml file)
			ObjectMapper mapper = new ObjectMapper();	
	        List<HashMap<String, String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {});		
	        return data;
			
			
			
		}

	public String getScreenshot(String testcasename, WebDriver driver) throws IOException
		{
	
			TakesScreenshot ts= (TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
		}
	
}
