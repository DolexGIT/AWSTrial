package TestCases;


import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listeners.TestJiraListener;
import com.util.JiraPolicy;

import Functionality.pageFXRates;


public class FXRateTest extends baseTest{

	pageFXRates obj_fx;
	
	public void GoToMenu() throws InterruptedException {
		
		System.out.println("In Go to Menu");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//div[@class='main-content']//div[text()='Send Money']")).click();
		Thread.sleep(20000);
		System.out.println("Done");
	}


	@Test
	public void CheckFXRates() throws InterruptedException {
		System.out.println("In Check FX rates");
		GoToMenu();
		obj_fx = new pageFXRates(driver);
		obj_fx.printFXRates("Mexic");
	}
}
