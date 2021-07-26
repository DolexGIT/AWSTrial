package TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listeners.TestJiraListener;

import Functionality.*;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestJiraListener.class)
public class baseTest extends baseClass{
	
	protected static Properties config = new Properties();
	public static WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	public static String username,password,baseUrl,automaticIssueCreation,jiraUrl,jiraPassword,jiraUsername,Project;
	
		
	
	public baseTest() {
		
		try {

			config.load(new FileInputStream("src/test/resources/config.properties"));
			baseUrl = config.getProperty("baseUrl");
			password = config.getProperty("password");
			username = config.getProperty("username");
			automaticIssueCreation = config.getProperty("automaticIssueCreation");
			jiraUrl = config.getProperty("jiraUrl");
			jiraUsername = config.getProperty("jiraUsername");
			jiraPassword = config.getProperty("jiraPassword");
			Project = config.getProperty("Project");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	@SuppressWarnings("deprecation")
	@BeforeTest(alwaysRun=true)
	public void initTestSuite() throws IOException {
		Properties config = new Properties();
		DesiredCapabilities dr = null;
		
		System.out.println("i am in before test");
		
	//	WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver","E:\\Softwares\\chromedriver_win32\\chromedriver.exe");
		
		ChromeOptions optionsChrome = new ChromeOptions();
		optionsChrome.addArguments("start-maximized");
		dr = new DesiredCapabilities();

		System.out.println("Chrome browser capabilities initialise");
		
		dr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		dr.setCapability(ChromeOptions.CAPABILITY, optionsChrome);
		
		System.out.println("Chrome browser options set");
		
		driver = new ChromeDriver(dr);
		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		tdriver.set(driver);
	}
	
	public static synchronized WebDriver getDriver() {
		
		return tdriver.get();
	}
	
	@AfterTest(alwaysRun=true)
	public void clearSession(){
		System.out.println("Ending the session");
	//	driver.quit();
	}
	
	
}