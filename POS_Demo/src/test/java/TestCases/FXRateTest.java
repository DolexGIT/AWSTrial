package TestCases;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Functionality.pageFXRates;


public class FXRateTest extends baseTest{

	pageFXRates obj_fx;
	
	


	@Test
	public void CheckFXRates() throws InterruptedException {
		System.out.println("In Check FX rates");
		GoToMenu("Send Money");
		obj_fx = new pageFXRates(driver);
		obj_fx.printFXRates("Mexico");
	}
}
