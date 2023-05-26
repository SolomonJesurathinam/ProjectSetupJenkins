package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonFunctions.ActionDriver;

public class NewCustomer {
	
	//testinga
	
	WebDriver driver;
	ActionDriver actionDriver = new ActionDriver();
	
	@FindBy(name="name")
	public WebElement customerName;
	
	@FindBy(name="dob")
	public WebElement dateOfbirth;
	
	@FindBy(name="addr")
	public WebElement address;
	
	@FindBy(name="city")
	public WebElement city;
	
	@FindBy(name="state")
	public WebElement state;
	
	@FindBy(name="pinno")
	public WebElement pinNumber;
	
	@FindBy(name="telephoneno")
	public WebElement telePhoneNumber;
	
	@FindBy(name="emailid")
	public WebElement emailId;
	
	@FindBy(name="password")
	public WebElement password;
	
	@FindBy(name="sub")
	public WebElement submit;
	
	@FindBy(xpath="//p[@class=\"heading3\"]")
	public WebElement customerVerifyText;
	
	public void newCustomerAddDetails(String nameData, String dob, 
			String Address, String City, String State, String PinNo, 
			String mobileNum, String EmailAddress, String passwordValue) throws Exception {
		actionDriver.type(customerName, nameData, "Customer Name");
		actionDriver.type(dateOfbirth, dob, "Date Of Birth");
		actionDriver.type(address, Address, "Address");
		actionDriver.type(city, City, "City");
		actionDriver.type(state, State, "State");
		actionDriver.type(pinNumber, PinNo, "Pin number");
		actionDriver.type(telePhoneNumber, mobileNum, "Mobile Number");
		actionDriver.type(emailId, EmailAddress, "Email Id");
		actionDriver.type(password, passwordValue, "Password");
		
		actionDriver.click(submit, "Submit");
	}

	
	public void verifyText(String actual) throws Exception {
		actionDriver.verificationEquals(actual, customerVerifyText);
	}
	
	public NewCustomer(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
