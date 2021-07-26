package TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listeners.TestJiraListener;
import com.util.JiraPolicy;

import Functionality.pageLogin;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


public class LoginTest extends baseTest{
	
	pageLogin obj_log;
	
	@Description("Login to POS app and Verify logo on home page")
	@Feature("Feature: Login")
	@Story("Story: Login and check logo presence")
	@Step("Verify Login functionality and logo presence")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void User_Login() throws InterruptedException {
		
		obj_log = new pageLogin(driver);
		obj_log.Login();
	}
}