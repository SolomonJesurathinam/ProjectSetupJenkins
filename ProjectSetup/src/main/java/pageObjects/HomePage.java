package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonFunctions.ActionDriver;

public class HomePage {
	
	WebDriver driver;
	ActionDriver actionDriver = new ActionDriver(); 
	
	@FindBy(xpath="//a[@href=\"Logout.php\"]")
	public WebElement logout;
	
	@FindBy(xpath="//a[@href=\"addcustomerpage.php\"]")
	public WebElement newCustomer;
	
	public void logoutUser() throws Exception {
		actionDriver.click(logout, "Logout");
		//logout.click();
	}
	
	public void clickNewcustomer() throws Exception {
		actionDriver.click(newCustomer, "New Customer");
		//newCustomer.click();
	}
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
