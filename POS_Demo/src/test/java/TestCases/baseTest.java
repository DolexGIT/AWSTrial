package TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	int dd,mm,yyyy;
	String mnth;

	HashMap<Integer,String> hm = new HashMap<Integer,String>();	
	
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
		
	//	WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
	//	WebDriver driver = new ChromeDriver();
	//	System.setProperty("webdriver.chrome.driver","E:\\Softwares\\chromedriver_win32\\chromedriver");
		
		String path = System.getProperty("user.dir");
		System.out.println("Chromedriver path = "+path);
		System.setProperty("webdriver.chrome.driver",path+"//lib//chromedriver.exe");
		
		ChromeOptions optionsChrome = new ChromeOptions();
		optionsChrome.addArguments("start-maximized");
		optionsChrome.addArguments("disable-infobars"); // disabling infobars
		optionsChrome.addArguments("--disable-extensions"); // disabling extensions
		optionsChrome.addArguments("--disable-gpu"); // applicable to windows os only
		optionsChrome.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		optionsChrome.addArguments("--no-sandbox"); // Bypass OS security model
	//	optionsChrome.addArguments("--headless");
	//	optionsChrome.addArguments("--disable-setuid-sandbox"); 
	//	optionsChrome.addArguments("--remote-debugging-port=9222");


		dr = new DesiredCapabilities();

		System.out.println("Chrome browser capabilities initialise");
		
		dr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		dr.setCapability(ChromeOptions.CAPABILITY, optionsChrome);
		
		System.out.println("Chrome browser options set");
		
		driver = new ChromeDriver(dr);
		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		tdriver.set(driver);
	}
	
	public static synchronized WebDriver getDriver() {
		
		return tdriver.get();
	}
	
	public void GoToMenu(String Menu) throws InterruptedException {
		
		System.out.println("In Go to Menu");
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='main-content']//div[text()='"+Menu+"']"))));
		driver.findElement(By.xpath("//div[@class='main-content']//div[text()='"+Menu+"']")).click();
		Thread.sleep(20000);
		System.out.println("Done");
	}
	
	public String generateRandom(int length) {
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length); // bound is exclusive

        Random random = new Random();

        return Integer.toString(random.nextInt(max - min) + min);
    }

		  
	    // function to generate a random string of length n
	    public String getAlphaNumericString()
	    {
	    	int n = 6;
	        // chose a Character random from this String
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz";
	  
	        // create StringBuffer size of AlphaNumericString
	        StringBuilder sb = new StringBuilder(n);
	  
	        for (int i = 0; i < n; i++) {
	  
	            // generate a random number between
	            // 0 to AlphaNumericString variable length
	            int index
	                = (int)(AlphaNumericString.length()
	                        * Math.random());
	  
	            // add Character one by one in end of sb
	            sb.append(AlphaNumericString
	                          .charAt(index));
	        }
	  
	        return sb.toString();
	    }
	    
	    public static int createRandomIntBetween(int start, int end) {
	        return start + (int) Math.round(Math.random() * (end - start));
	    }

	    public static LocalDate createRandomDate(int startYear, int endYear) {
	        int day = createRandomIntBetween(1, 28);
	        int month = createRandomIntBetween(1, 12);
	        int year = createRandomIntBetween(startYear, endYear);
	        return LocalDate.of(year, month, day);
	    }
	    
	    public String getMonthYear() throws InterruptedException{
	  	  
	  	  
	  	  Thread.sleep(1000);
	  	  WebElement link_mn_yr = driver.findElement(By.xpath("//th[@class='rdtSwitch']"));
	  	  String mn_yr = link_mn_yr.getText();
	  	  
	  	  System.out.println("Month and Year on calender : "+mn_yr);
	  	  return mn_yr;
	    }
	    
	    
	    public void SplitDate(String dob){
	  	  
	  	  String dd_mm_yy = dob;
	  	  
	  	  String date[] = dd_mm_yy.split("-");
	  	  
	  	  dd = Integer.valueOf(date[2]);
	  	  mm = Integer.valueOf(date[1]);
	  	  yyyy = Integer.valueOf(date[0]);
	  	  
	  	  hm.put(1, "January");
	  	  hm.put(2, "February");
	  	  hm.put(3, "March");
	  	  hm.put(4, "April");
	  	  hm.put(5, "May");
	  	  hm.put(6, "June");
	  	  hm.put(7, "July");
	  	  hm.put(8, "August");
	  	  hm.put(9, "September");
	  	  hm.put(10,"October");
	  	  hm.put(11,"November");
	  	  hm.put(12,"December");
	  	  
	  	  
	  	  for (Entry<Integer, String> entry : hm.entrySet()) {
	            if (entry.getKey().equals(mm)) {
	                System.out.println("Month in string : "+entry.getValue());
	                mnth = entry.getValue();
	            }
	  	  }
	  	  
	  	  
	    }
	    
	    public void clickOnDate(String dob) throws InterruptedException{
	  	  boolean flag = false;
	  	  
	  	  driver.findElement(By.xpath("//input[@placeholder='MM/DD/YYYY']")).click();
	  	  SplitDate(dob);
	  	  String mn_yr = getMonthYear();
	  	  int year = Integer.valueOf(mn_yr.split(" ")[1]);
	  	  int year_diff = yyyy - year;
	  	  
	 	  if(year_diff!=0){
	  	  if(year_diff<0){
	  		 WebElement link_mm_yy =  driver.findElement(By.xpath("//th[@class='rdtSwitch']"));
	  			link_mm_yy.click();
	  			Thread.sleep(2000);
	  			
	  			 WebElement link_mm_yy1 =  driver.findElement(By.xpath("//th[@class='rdtSwitch']"));
	  			link_mm_yy1.click();
	  			do{
	  				
	  				 WebElement link_mm_yy2 =  driver.findElement(By.xpath("//th[@class='rdtSwitch']"));
	  				int start_year = Integer.valueOf(link_mm_yy2.getText().split("-")[0]);
	  				if(start_year < yyyy){
	  					
	  					driver.findElement(By.xpath("//div[@class='rdtYears']//td[text()='"+yyyy+"']")).click();
	  					flag = true;
	  				}
	  			
	  				else{
	  					driver.findElement(By.className("rdtPrev")).click();
	  				}
	  			}while(!flag);
	  			
	  	  }
	  	  else
	  	  {
	  			  String month = mn_yr.split(" ")[0];
	  			  
	  			  if(mnth.equalsIgnoreCase(month)){
	  				  WebElement link_date = driver.findElement(By.xpath("//td[text()='"+dd+"']"));
	  				  link_date.click();
	  			  }
	  			  else{
	  				 WebElement link_mm_yy =  driver.findElement(By.className("rdtSwitch"));
	  				link_mm_yy.click();
	  			  }
	  	  }
	  	  
	  	  		Thread.sleep(2000);
	  			mnth = mnth.substring(0,3);
	  			WebElement link_month = driver.findElement(By.xpath("//td[text()='"+mnth+"']"));
	  			link_month.click();
	  			
	  			Thread.sleep(2000);
	  			WebElement link_date = driver.findElement(By.xpath("//td[text()='"+dd+"']"));
	  			link_date.click();
	  	  
	  	  }
	    }
	    
	@AfterTest(alwaysRun=true)
	public void clearSession(){
		System.out.println("Ending the session");
	//	driver.quit();
	}
	
	
}
