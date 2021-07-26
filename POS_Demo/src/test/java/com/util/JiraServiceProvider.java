package com.util;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import TestCases.baseTest;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;



public class JiraServiceProvider extends baseTest{

	public JiraClient jira;
	public String project;
	
    public JiraServiceProvider() {
		BasicCredentials creds = new BasicCredentials(jiraUsername, jiraPassword);
		jira = new JiraClient(jiraUrl,creds);
		this.project = Project;
	}
    
public void createJiraTicket(String issueType, String Summary, String Description, String Assignee, String Priority) {
		
		try{
			FluentCreate obj_fluentCreate = jira.createIssue(project, issueType);
			obj_fluentCreate.field(Field.SUMMARY, Summary);
			obj_fluentCreate.field(Field.DESCRIPTION,Description);
			obj_fluentCreate.field(Field.ASSIGNEE,Assignee);
			obj_fluentCreate.field(Field.PRIORITY, Priority);
		//	obj_fluentCreate.field(Field.ATTACHMENT, TestJiraListener.saveFailureScreenShot(driver));
			Issue newInssue = obj_fluentCreate.execute();
			System.out.println("New Issue created in JIRA with ID : "+newInssue);
		}
		catch(JiraException e) {
			e.printStackTrace();
		}
		
	}  
}