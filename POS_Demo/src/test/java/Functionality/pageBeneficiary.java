package Functionality;

import java.io.Reader;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.internal.Systematiser;

import com.util.XLS_Reader;

import TestCases.baseTest;

public class pageBeneficiary extends baseTest {
	
	WebDriver driver;
	
	By btn_AddSender = By.xpath("//span[text()='Add Sender']/ancestor::button");
	By txt_FirstName = By.id("addSenderFirstName");
	By txt_MiddleName = By.id("addSenderMiddleName");
	By txt_LastName = By.id("addSenderLastName");
	By txt_SecLastName = By.id("addSenderSecondLastName");
	By txt_PhoneNum = By.id("addSenderTelephone");
	By ch_IsMobile = By.xpath("//label[text()='Mobile']/ancestor::div[@class='checkbox-component']");
	By dd_Country = By.xpath("//div[text()='Country Of Birth']/ancestor::div[@class='dropdown']//button[contains(@class,'toggle-dropdown')]");
	By txt_AddressLine1 =By.id("addSenderAddressLine1");
	By txt_AddressLine2 = By.id("addSenderAddressLine2");
	By dd_State = By.xpath("//div[text()='State']/ancestor::div[@class='dropup dropdown']//button[contains(@class,'toggle-dropdown')]");
	By dd_City = By.xpath("//div[text()='City']/ancestor::div[@class='dropup dropdown show']//button[contains(@class,'toggle-dropdown')]");
	By txt_PostalCode = By.xpath("//div[text()='Postal Code']/ancestor::div[@class='dropup dropdown']//button[contains(@class,'toggle-dropdown')]");
	By btn_Save = By.xpath("//span[text()='Create Profile']/ancestor::button");
	By btn_Back = By.xpath("//span[text()='Create Profile']/ancestor::button");
			
	public pageBeneficiary(WebDriver driver) {
				
		this.driver = driver;
	}
			
	public void CreateSenderProfile() {

		driver.findElement(btn_AddSender).click();
		XLS_Reader obj_xls = new XLS_Reader("C:\\Users\\manasee.shere\\eclipse-workspace\\DolEx_POS\\src\\test\\resources\\TestData.xlsx");
		String FirstName = obj_xls.getCellData("AddSender","FirstName", 2);
		String fName = FirstName.concat(getAlphaNumericString());
		driver.findElement(txt_FirstName).sendKeys(fName);
		
		String MiddleName = obj_xls.getCellData("AddSender", "MiddleName",2);
		driver.findElement(txt_MiddleName).sendKeys(MiddleName);
		
		String LastName = obj_xls.getCellData("AddSender","LastName", 2);
		String lName = LastName.concat(getAlphaNumericString());
		driver.findElement(txt_LastName).sendKeys(lName);
		
		String SecLastName = obj_xls.getCellData("AddSender", "SecLastName",2);
		driver.findElement(txt_SecLastName).sendKeys(SecLastName);
		
        String BirthCountry = obj_xls.getCellData("AddSender","CountryOfBirth",2);
        driver.findElement(dd_Country).sendKeys(BirthCountry);
        
        String TelephoneNumber = generateRandom(10);
        driver.findElement(txt_PhoneNum).sendKeys(TelephoneNumber);
        
        String IsMobile = obj_xls.getCellData("AddSender", "IsMobile",2);
        if(IsMobile.equalsIgnoreCase("True")) {
        	driver.findElement(ch_IsMobile).click();
        }
        
        String hno = generateRandom(3);
        String Adrs = obj_xls.getCellData("AddSender","Address1",2);
        String Address1 = hno.concat(Adrs);
        driver.findElement(txt_AddressLine1).sendKeys(Address1);
        
        String Address2 = obj_xls.getCellData("AddSender","Address2", 2);
        driver.findElement(txt_AddressLine2).sendKeys(Address2);
        
        
        String State = obj_xls.getCellData("AddSender","State",2);
        driver.findElement(dd_State).sendKeys(State);
        
        String City = obj_xls.getCellData("AddSender","City",2);
        driver.findElement(dd_City).sendKeys(City);
        
        String PostalCode = obj_xls.getCellData("AddSender","PostalCode",2);
        driver.findElement(txt_PostalCode).sendKeys(PostalCode);
        
        driver.findElement(btn_Save).click();
	}
	
}