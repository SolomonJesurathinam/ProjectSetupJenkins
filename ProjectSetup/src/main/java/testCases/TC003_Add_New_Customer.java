package testCases;

import org.testng.annotations.Test;

import commonFunctions.CommonFunctions;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.NewCustomer;

public class TC003_Add_New_Customer extends CommonFunctions{
	
	@Test
	public void tc003_Add_New_Customer() throws Exception {
		CommonFunctions.childTest = CommonFunctions.parentTest.createNode("Login to Application");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(data.get("username"), data.get("password"));
		
		CommonFunctions.childTest = CommonFunctions.parentTest.createNode("Create New Customer");
		HomePage homePage = new HomePage(driver);
		homePage.clickNewcustomer();
		
		NewCustomer customer = new NewCustomer(driver);
		customer.newCustomerAddDetails(data.get("customername"), data.get("dob"), 
				data.get("address"), data.get("city"), data.get("State"), 
				data.get("pinNumber"), data.get("mobileNumber"), 
				data.get("mailID"), data.get("passwordvalue"));
		
		Thread.sleep(2000);
		
		customer.verifyText("Customer Registered Successfully!!!");
		
		homePage.logoutUser();
		
		
	}

}
