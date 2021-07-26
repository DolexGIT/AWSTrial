package Functionality;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pageFXRates {

	WebDriver driver;
	
	By FX_Edit_btn = By.id("edit-button");
	By FX_Refresh_btn = By.xpath("//div[@class='d-flex']//img[@id='refresh-button']");
	
	
	public pageFXRates(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void printFXRates(String Country) {
		System.out.println("In Print FX Rates");
		String FXRate = driver.findElement(By.xpath("//div[text()='"+Country+"']/ancestor::div[@class='col-md-4']//div[@class='selected-rate']//span")).getText();
		System.out.println("Current FX rate for "+Country+"is ="+FXRate);
	}
	

}
