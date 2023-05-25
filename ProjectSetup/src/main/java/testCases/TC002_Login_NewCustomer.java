package testCases;

import org.testng.annotations.Test;

import commonFunctions.CommonFunctions;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC002_Login_NewCustomer extends CommonFunctions{
	
	@Test
	public void tc002_Login_NewCustomer() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(data.get("username"), data.get("password"));
		
		HomePage homePage = new HomePage(driver);
		homePage.clickNewcustomer();
		Thread.sleep(5000);
		homePage.logoutUser();
	}

}
