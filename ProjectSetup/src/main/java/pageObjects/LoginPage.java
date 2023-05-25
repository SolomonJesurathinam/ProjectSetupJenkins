package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonFunctions.ActionDriver;
import commonFunctions.CommonFunctions;

public class LoginPage {
	
WebDriver driver;
ActionDriver actionDriver = new ActionDriver(); 
	
	@FindBy(name="uid")
	public WebElement userName;
	
	@FindBy(name="password")
	public WebElement password;
	
	@FindBy(name="btnLogin")
	public WebElement login;

	/**
	 * This method is to login
	 * @param usernameValue Get the value from testData
	 * @param passwordValue Get the value from testData
	 * @throws Exception 
	 */
	public void loginUser(String usernameValue, String passwordValue) throws Exception {
		actionDriver.type(userName, usernameValue, "UserName");
		actionDriver.type(password, passwordValue, "Password");
		//userName.sendKeys(usernameValue);
		//password.sendKeys(passwordValue);
		
		//screenshot
		CommonFunctions.screenShot("Login Page");
		
		actionDriver.click(login, "Login");
		//login.click();
	}
	
	
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
