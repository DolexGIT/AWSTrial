package TestCases;

import java.awt.Window;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.interactions.touch.Scroll;
import org.openqa.selenium.support.ui.Select;

public class seleniumpractice {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("http://tutorialsninja.com/demo/index.php");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sctqatest@grr.la");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Spring123$");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//i[@class='fa fa-home']")).click();
		
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,250)");
		
		String MacLable = driver.findElement(By.xpath("//a[text()='MacBook']")).getText();
		System.out.println(MacLable);
		String macBookPrice = driver.findElement(By.xpath("//p[@class='price']")).getText();
		System.out.println(macBookPrice);
		
		String iPhoneLable = driver.findElement(By.xpath("//a[text()='iPhone']")).getText();
		System.out.println(iPhoneLable);
		String iphonePrice = driver.findElement(By.xpath("(//p[@class='price'])[2]")).getText();
		System.out.println(iphonePrice);
		
		String AppleCinemaLable=driver.findElement(By.xpath("//a[text()='Apple Cinema 30\"']")).getText();
		System.out.println(AppleCinemaLable);
		String AppleCinemaPrice=driver.findElement(By.xpath("(//p[@class='price'])[3]")).getText();
		System.out.println(AppleCinemaPrice);
		
		String CanonEosLable=driver.findElement(By.xpath("driver.findElement")).getText();
		System.out.println(CanonEosLable);
		String CanonEosPrice=driver.findElement(By.xpath("(//span[@class='price-new'])[2]")).getText();
		System.out.println(CanonEosPrice);
		
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-250)");
		WebElement LaptopNotebook = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[3]"));
		
		Actions act=new Actions(driver);

		WebElement AllOpt = driver.findElement(By.xpath("//a[text()='Show All Laptops & Notebooks']"));
		act.moveToElement(AllOpt).click().perform();
		
		//driver.findElement(By.xpath("(//a[text()='Macs (0)'])[2]")).click();
		
		   WebElement Sort = driver.findElement(By.xpath("input-sort"));
		   Select sel=new Select(Sort);
		   
		   sel.deselectByVisibleText("Price(High>Low");
		   
		   driver.findElement(By.xpath("(//img[@alt='MacBook Pro'])[2]")).click();
		   
		   driver.findElement(By.xpath("//input[@id='input-quantity']")).sendKeys("2");
          driver.findElement(By.xpath("button-cart")).click();
          
          driver.findElement(By.xpath("shopping cart")).click();
         String ActQTY = driver.findElement(By.xpath("//input[@value='16']")).getText();
       System.out.println("Act QTY" +ActQTY);
       
       String expqty = "2";
       if(ActQTY.equals(expqty)) {
    	   System.out.println("Match");
       }
       else {
    	   System.out.println("Not Matched");
       }
       
       String ActunitPrice = driver.findElement(By.xpath("(//td[@class='text-right'])[9]")).getText();
      System.out.println("Actual Unit Price"+ActunitPrice);
       if(ActunitPrice.equals(macBookPrice)) {
    	   System.out.println("Unit Price Matched");
       }
       else {
    	   System.out.println("Unit Price Not Matched");
       }
	
       String ActTotalCost=driver.findElement(By.xpath("(//td[@class='text-right'])[10]")).getText();
   System.out.println("Actual Total Cost"+ActTotalCost);
       String expTotalCost = "4000";
	if(ActTotalCost.equals(expTotalCost)) {
		
		System.out.println("Total cost is ok");
	}
	else {
		System.out.println("Total cost not ok");
	}
	String model = driver.findElement(By.xpath("//td[text()='Product 18']")).getText();
	System.out.println(model);
	
	}

}
