package Functionality;

import java.io.Reader;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSliderUI;

import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;
import org.testng.internal.Systematiser;

import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.util.XLS_Reader;

import TestCases.baseTest;

public class pageSender extends baseTest {
	
	WebDriver driver;
	
	By btn_AddSender = By.xpath("//span[text()='Add Sender']/ancestor::button");
	By txt_FirstName = By.id("addSenderFirstName");
	By txt_MiddleName = By.id("addSenderMiddleName");
	By txt_LastName = By.id("addSenderLastName");
	By txt_SecLastName = By.id("addSenderSecondLastName");
	By txt_DOB = By.xpath("//input[@placeholder = 'MM/DD/YYYY']");
	By dd_BirthCountry = By.xpath("//div[text()='Country Of Birth']/ancestor::div[@class='dropdown']//button[contains(@class,'toggle-dropdown')]");
	By txt_dd_BirthCountry = By.xpath("//div[text()='Country Of Birth']/ancestor::div[contains(@class,'dropdown')]//div[contains(@class,'react-select-inner__value-container')]//input");
	By txt_ALCardNum = By.id("addSenderALCard");
	By txt_SSN = By.id("addSSNNumber");
	By ch_IsSSN = By.xpath("//label[text()='Does not have SSN/ITIN']/ancestor::div[@class='checkbox-component']");
	By txt_TeleNum = By.id("addSenderTelephone");
	By ch_IsMobile = By.xpath("//label[text()='Mobile']/ancestor::div[@class='checkbox-component']");
	By txt_EmailID = By.id("addSenderEmail");
	By txt_Address = By.xpath("//div[text()='Address']/ancestor::div[1]//input[@placeholder = 'Enter Sender Address']");
	By ch_ManualAddress = By.xpath("//label[text()='Enter address manually']/ancestor::div[@class='checkbox-component']");
	By txt_AddressLine1 =By.id("addSenderAddressLine1");
	By txt_AddressLine2 = By.id("addSenderAddressLine2");
	By dd_State = By.xpath("//div[text()='State']/ancestor::div[contains(@class,'dropup dropdown')]//button[contains(@class,'toggle-dropdown')]");
	By txt_dd_State = By.xpath("//div[text()='State']/ancestor::div[contains(@class,'dropup dropdown')]//div[contains(@class,'react-select-inner__value-container')]//input");
	By dd_City = By.xpath("//div[text()='City']/ancestor::div[contains(@class,'dropup dropdown')]//button[contains(@class,'toggle-dropdown')]");
	By txt_dd_City = By.xpath("//div[text()='City']/ancestor::div[contains(@class,'dropup dropdown')]//div[contains(@class,'react-select-inner__value-container')]//input");
	By dd_PostalCode = By.xpath("//div[text()='Postal Code']/ancestor::div[contains(@class,'dropup dropdown')]//button[contains(@class,'toggle-dropdown')]");
	By txt_dd_PostalCode = By.xpath("//div[text()='Postal Code']/ancestor::div[contains(@class,'dropup dropdown')]//div[contains(@class,'react-select-inner__value-container')]//input");
	By btn_CreateProfile = By.xpath("//span[text()='Create Profile']/ancestor::button");
	
	public pageSender(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void CreateSenderProfile() throws FindFailed, InterruptedException {
		
		Screen s = new Screen();
		String filePath = System.getProperty("user.dir");
		
		driver.findElement(btn_AddSender).click();
		XLS_Reader obj_xls = new XLS_Reader("C:\\Users\\manasee.shere\\eclipse-workspace\\POS_Demo\\src\\test\\resources\\TestData.xlsx");
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
		
		java.time.LocalDate randomDate = createRandomDate(1900, 2000);
		String dob = randomDate.toString();
		System.out.println(dob);
		clickOnDate(dob);
		
        String BirthCountry = obj_xls.getCellData("AddSender","CountryOfBirth",2);
        driver.findElement(dd_BirthCountry).click();
        Thread.sleep(2000);
        driver.findElement(txt_dd_BirthCountry).sendKeys(BirthCountry);
        driver.findElement(By.xpath("//span[text()='"+BirthCountry+"']")).click();
        
        String ALCardNumber = generateRandom(8);
        driver.findElement(txt_ALCardNum).sendKeys(ALCardNumber);
        
        String SSNumber = generateRandom(10);
     //   driver.findElement(txt_SSN).sendKeys(SSNumber);
        
        String IsSSN = obj_xls.getCellData("AddSender", "IsSSN/ITIN",2);
        if(IsSSN.equalsIgnoreCase("True")) {
        	driver.findElement(ch_IsSSN).click();
        }
        
        String TelephoneNumber = generateRandom(10);
        driver.findElement(txt_TeleNum).sendKeys(TelephoneNumber);
        
        String IsMobile = obj_xls.getCellData("AddSender", "IsMobile",2);
        if(IsMobile.equalsIgnoreCase("True")) {
        	
        	org.sikuli.script.Pattern ch_Mobile = new org.sikuli.script.Pattern(filePath+"\\lib\\Patterns\\Mobile.png");
        	s.click(ch_Mobile);
        }
        
        String EmailID = obj_xls.getCellData("AddSender", "EmailID", 2);
        driver.findElement(txt_EmailID).sendKeys(EmailID);
        
        
        String Address = obj_xls.getCellData("AddSender","Address", 2);
     //   driver.findElement(txt_Address).sendKeys(Address);
        
        String ManualAddress = obj_xls.getCellData("AddSender", "ManualAddress",2);
        System.out.println(ManualAddress);
        if(ManualAddress.equalsIgnoreCase("TRUE")) {
        	org.sikuli.script.Pattern ch_Address = new org.sikuli.script.Pattern(filePath+"\\lib\\Patterns\\Address.png");
        	s.click(ch_Address);
        
        String hno = generateRandom(3);
        String Adrs = obj_xls.getCellData("AddSender","Address1",2);
        String Address1 = hno.concat(Adrs);
        driver.findElement(txt_AddressLine1).sendKeys(Address1);
        
        String Address2 = obj_xls.getCellData("AddSender","Address2", 2);
        driver.findElement(txt_AddressLine2).sendKeys(Address2);
        
        //Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        Thread.sleep(2000);
        String State = obj_xls.getCellData("AddSender","State",2);
        driver.findElement(dd_State).click();
        Thread.sleep(2000);
        driver.findElement(txt_dd_State).sendKeys(State);
        driver.findElement(By.xpath("//span[text()='"+State+"']")).click();
        
        String City = obj_xls.getCellData("AddSender","City",2);
        driver.findElement(dd_City).click();
        Thread.sleep(2000);
        driver.findElement(txt_dd_City).sendKeys(City);
        driver.findElement(By.xpath("//span[text()='"+City+"']")).click();
        
        String PostalCode = obj_xls.getCellData("AddSender","PostalCode",2);
        driver.findElement(dd_PostalCode).click();
        Thread.sleep(2000);
        driver.findElement(txt_dd_PostalCode).sendKeys(PostalCode);
        driver.findElement(By.xpath("//span[contains(text(),'"+PostalCode+"')]")).click();
        }
        
        driver.findElement(btn_CreateProfile).click();   
	}
	
}
