package TestCases;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Trial {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
    
		System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.MICROSECONDS);
	    
	    driver.get("http://tutorialsninja.com/demo/index.php");
	    
	   // Actions act=new Actions(driver);
	   
	    Thread.sleep(5000);
	  //  WebElement account=driver.findElement(By.xpath("//li[@class='dropdown']//a[@title='My Account']"));
	   WebElement list=driver.findElement(By.xpath("//a[@title='My Account']"));
	    
	    //System.out.println(list.size());
	    
	   // account.getAttribute("href");
	    	   // act.moveToElement(account).click().perform();
	   
	    list.click();
	    
	 
        WebElement login=driver.findElement(By.xpath(" //a[text()='Login']"));
	    
	    login.click();
	    
	  
	    WebElement email=driver.findElement(By.xpath("//input[@id='input-email']"));
	    email.sendKeys("sctqatest@grr.la");
	    
	    WebElement password=driver.findElement(By.xpath("//input[@id='input-password']"));
	    password.sendKeys("Spring123$");
	    
	 
	    WebElement logincust=driver.findElement(By.xpath(" //input[@value='Login']"));
	    logincust.click();
	    
	    JavascriptExecutor js=(JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(1,1000)");
	    
	   // JavascriptExecutor js=(JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(1000,0)");
	    
	 
	    WebElement lapcomp=driver.findElement(By.xpath(" //a[text()='Laptops & Notebooks']"));
	    
	    lapcomp.click();
	    
	 
        WebElement showlapcomp=driver.findElement(By.xpath("  //a[text()='Show All Laptops & Notebooks']"));
	    
	    showlapcomp.click();
	    
	    WebElement sortby=driver.findElement(By.xpath(" //select[@id='input-sort']"));
	    
	    Select sc=new Select(sortby);
	    
	   sc.selectByIndex(0);
	    
	  //a[text()='MacBook Pro']
	    
	    WebElement mac=driver.findElement(By.xpath("//a[text()='MacBook Pro']"));
	    
	    mac.click();
	    
	  //input[name='quantity']
       WebElement quan=driver.findElement(By.xpath("//a[text()='MacBook Pro']"));
	    
	   quan.sendKeys("2");
	    
	
	   WebElement addtocart=driver.findElement(By.xpath(" //button[@text()='Add to Cart']"));
	    
	   addtocart.click();

	 //*[@id="content"]/form/div/table/thead/tr/td[2]
	   
	   WebElement productname=driver.findElement(By.xpath("  //*[@id=\"content\"]/form/div/table/thead/tr/td[2]"));
	    
	   if(productname.equals("MacBook"))
	   {
		   System.out.println("valid");
	   }
	   System.out.println(productname);
	   
	   WebElement model=driver.findElement(By.xpath("  //*[@id=\"content\"]/form/div/table/thead/tr/td[3]"));
	    
	   if(model.equals("Product16"))
	   {
		   System.out.println("valid");
	   }
	   System.out.println(model);
	   
	   WebElement quantity=driver.findElement(By.xpath("  //*[@id=\"content\"]/form/div/table/thead/tr/td[4]"));
	    
	   if(quantity.equals("2"))
	   {
		   System.out.println("valid");
	   }
	   System.out.println(quantity);
	   
	   WebElement unitprice=driver.findElement(By.xpath(" //*[@id=\"content\"]/form/div/table/thead/tr/td[5]"));
	    
	   if(unitprice.equals("$602.00"))
	   {
		   System.out.println("valid");
	   }
	   System.out.println(unitprice);
	   WebElement total=driver.findElement(By.xpath(" //*[@id=\"content\"]/form/div/table/thead/tr/td[6]"));
	    
	   if(productname.equals("$1,204.00"))
	   {
		   System.out.println("valid");
	   }
	   System.out.println(total);
	   
	}
}
	    