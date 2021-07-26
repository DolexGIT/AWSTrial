package Functionality;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestCases.*;

public class pageLogin extends baseTest {
	
	WebDriver driver;
	
	By txt_username = By.id("userName");
	By	txt_password = By.id("password");
	By	btn_Login = By.xpath("//button[contains(@class,'login-button')]");
	By img_Logo = By.className("header-logo");
	
	
	public pageLogin(WebDriver driver) {
		this.driver = driver;
	}

	
public void Login() {
	
	System.out.println("I am in Login Function");
	
	WebDriverWait wait = new WebDriverWait(driver, 50);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(txt_username)));
	assertEquals("DolEx POS",driver.getTitle());
	driver.findElement(txt_username).sendKeys(username);
	driver.findElement(txt_password).sendKeys(password);
	driver.findElement(btn_Login).click();
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(img_Logo)));
	
	}
}