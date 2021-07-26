package com.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.util.JiraServiceProvider;

import TestCases.baseTest;
import io.qameta.allure.Attachment;

public class TestJiraListener extends baseTest implements ITestListener{
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public static byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Inside onStart method :"+context.getName());
		context.setAttribute("WebDriver",baseTest.getDriver());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Inside onFinish method :"+context.getName());
	  }
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Inside onTestStart method :"+getTestMethodName(result)+" Start");
	  }
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Inside onTestSuccess method :"+getTestMethodName(result)+" Succeed");
	  }
	
	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("Inside onTestFailure method :"+getTestMethodName(result)+" Failed");
		WebDriver driver = baseTest.getDriver();
		if(driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case :"+getTestMethodName(result));
			saveFailureScreenShot(driver);
		}
		saveTextLog(getTestMethodName(result)+" failed and screenshot taken !");
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String screenshotPath = System.getProperty("user.dir")+"/IssueScreenshots/"+actualDate+".jpeg";
		System.out.println("Screenshot path = "+screenshotPath);
		File dest = new File(screenshotPath);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		if(automaticIssueCreation.equalsIgnoreCase("true")) {
			//raise JIRA ticket
			System.out.println("Add issue in JIRA : "+automaticIssueCreation);
			JiraServiceProvider obj_jiraSP = new JiraServiceProvider();
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName() + " failed in Automation";
			String issueDescription = result.getThrowable().getMessage() + "\n";
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			try {
				obj_jiraSP.createJiraTicket("Bug", issueSummary, issueDescription,"Manasee Shere","High");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	  }
	
	@Override
	public void onTestSkipped(ITestResult result) {
	    System.out.println("Inside onTestSkipped method :"+getTestMethodName(result)+" skipped");
	  }
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    System.out.println("Test failed but it is in defined success ratio :"+getTestMethodName(result)+" failed within success ratio");
	  }
}


