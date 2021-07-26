package TestCases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Calender {
	
	WebDriver driver;
	
	int dd,mm,yyyy;
	String mnth;

	HashMap<Integer,String> hm = new HashMap<Integer,String>();
	
	@BeforeTest
	
	public void setUP(){
		
		System.setProperty("webdriver.chrome.driver","E:\\Softwares\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://demos.telerik.com/kendo-ui/datetimepicker/index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	
  @Test(priority = 0)
  public void AcceptCookies() throws InterruptedException {
	  
	  String MainWindow = driver.getWindowHandle();
	  
	  Set<String> s1 = driver.getWindowHandles();
	  Iterator<String> it = s1.iterator();
	  
	  while(it.hasNext()){
		  
		  String ChildWindow = it.next();
		  driver.switchTo().window(ChildWindow);
		  Thread.sleep(2000);
		  WebElement Btn_AcceptCookies = driver.findElement(By.xpath("//*[text()='Accept Cookies']"));
		  Btn_AcceptCookies.click();
		  
	  }
	  
	  driver.switchTo().window(MainWindow);
  }
  
  @Test(priority = 1)
  public void ScrollDownPageAndClickOnCalender() throws InterruptedException{
	  
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  js.executeScript("window.scrollBy(0,100)");
	  
	  Thread.sleep(2000);
	  WebElement  icon_calender = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));
	  icon_calender.click();
	  
  }
  
  
  @Test(priority = 2)
  public String getMonthYear() throws InterruptedException{
	  
	  
	  Thread.sleep(1000);
	  WebElement link_mn_yr = driver.findElement(By.xpath("//a[@class='k-link k-nav-fast']"));
	  String mn_yr = link_mn_yr.getAttribute("text");
	  
	  System.out.println("Month and Year on calender : "+mn_yr);
	  return mn_yr;
  }
  
  
  @Test(priority = 3)
  public void SplitDate(){
	  
	  String dd_mm_yy = "4/11/1991";
	  
	  String date[] = dd_mm_yy.split("/");
	  
	  dd = Integer.valueOf(date[0]);
	  mm = Integer.valueOf(date[1]);
	  yyyy = Integer.valueOf(date[2]);
	  
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
  
  @Test(priority = 3)
  public void clickOnDate() throws InterruptedException{
	  boolean flag = false;
	  String mn_yr = getMonthYear();
	  int year = Integer.valueOf(mn_yr.split(" ")[1]);
	  
	  int year_diff = yyyy - year;
	  
	  
	  if(year_diff!=0){
	  if(year_diff>0){
		  WebElement link_mm_yy = driver.findElement(By.xpath("//a[contains(@class,'k-nav-fast')]"));
			link_mm_yy.click();
			
			link_mm_yy.click();
			
			do{
				
				int end_year = Integer.valueOf(link_mm_yy.getAttribute("text").split("-")[1]);
				if(end_year > yyyy){
					Thread.sleep(1500);
					driver.findElement(By.xpath("//div[@class='k-calendar-view']//a[text()='"+yyyy+"']")).click();
					flag = true;
				}
			
				else{
					driver.findElement(By.xpath("//a[contains(@class,'k-nav-next')]")).click();
				}
			}while(!flag);
	  }
		
	  
	  if(year_diff<0){
		  WebElement link_mm_yy = driver.findElement(By.xpath("//a[contains(@class,'k-nav-fast')]"));
			link_mm_yy.click();
			
			link_mm_yy.click();
			
			do{
				
				int start_year = Integer.valueOf(link_mm_yy.getAttribute("text").split("-")[0]);
				if(start_year < yyyy){
					
					driver.findElement(By.xpath("//div[@class='k-calendar-view']//a[text()='"+yyyy+"']")).click();
					flag = true;
				}
			
				else{
					driver.findElement(By.xpath("//a[contains(@class,'k-nav-prev')]")).click();
				}
			}while(!flag);
			
	  }
	  else
	  {
			  String month = mn_yr.split(" ")[0];
			  
			  if(mnth.equalsIgnoreCase(month)){
				  WebElement link_date = driver.findElement(By.xpath("//a[text()='"+dd+"']"));
				  link_date.click();
			  }
			  else{
				WebElement link_mm_yy = driver.findElement(By.xpath("//a[contains(@class,'k-nav-fast')]"));
				link_mm_yy.click();
			  }
	  }
	  
	  		Thread.sleep(2000);
			mnth = mnth.substring(0,3);
			WebElement link_month = driver.findElement(By.xpath("//a[text()='"+mnth+"']"));
			link_month.click();
			
			Thread.sleep(2000);
			WebElement link_date = driver.findElement(By.xpath("//a[text()='"+dd+"']"));
			link_date.click();
	  
	  }
  }
/*  
  @AfterTest
  public void closeBrowser(){
	  driver.quit();
  }
  */
}
