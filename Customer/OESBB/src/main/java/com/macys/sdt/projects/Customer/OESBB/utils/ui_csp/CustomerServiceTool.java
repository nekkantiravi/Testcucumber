/**
 * 
 */
package com.macys.sdt.projects.Customer.OESBB.utils.ui_csp;

import com.macys.sdt.framework.runner.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 *
 */


public class CustomerServiceTool {
	
	private WebDriver customerServicePage;
	String baseUrl;
	By usernameLocator = By.name("Username");
	By passwordLocator = By.name("Password");
	By singInButtonLocator = By.name("GO_BUTTON");
	By otherFunctionslLinkLocator = By.linkText("Other Functions");
	By sendMailTestLinkLocator = By.linkText("> Send Mail Test");
	By logoutUser = By.cssSelector("a > font.logintext");

	/**
	 * 
	 */
	public CustomerServiceTool(String server) {
		this.customerServicePage = WebDriverManager.getWebDriver(); //Uses system default browser, create own if different needed
		this.baseUrl = "http://"+ server +":8180/FdsOpsEmail/service/csu/signin.ognc";
		this.customerServicePage.get(baseUrl);
		Dimension d = new Dimension(700, 800);
		WebDriverManager.getWebDriver().manage().window().setSize(d);
	}

	public boolean loginAsUser(String userName, String password) {
		this.customerServicePage.findElement(usernameLocator).clear();
		this.customerServicePage.findElement(usernameLocator).sendKeys(userName);
		this.customerServicePage.findElement(passwordLocator).clear();
		this.customerServicePage.findElement(passwordLocator).sendKeys(password);
		this.customerServicePage.findElement(singInButtonLocator).click();
		this.customerServicePage.findElement(otherFunctionslLinkLocator).click();
		this.customerServicePage.findElement(sendMailTestLinkLocator).click();
		return true;
	}
	
	public boolean logoutUser(){
		this.customerServicePage.findElement(logoutUser).click();
		return true;
	}
	
	public boolean closeBrowser(){
		this.customerServicePage.close();
		this.customerServicePage.quit();
		return true;
	}

}
