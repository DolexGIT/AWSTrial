package TestCases;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import Functionality.pageBeneficiary;
import Functionality.pageSender;

public class SendMoney extends baseTest {

	pageSender obj_sender;
	pageBeneficiary obj_bene;
	
	@Test
	public void CreateSender() throws InterruptedException, FindFailed {
		System.out.println("Inside Create Sender Funcionality");
		obj_sender = new pageSender(driver);
		obj_bene = new pageBeneficiary(driver);
		GoToMenu("Send Money");
		obj_sender.CreateSenderProfile();
	}
	
}
