package testCases;


import org.testng.annotations.Test;
import commonFunctions.CommonFunctions;
import commonFunctions.RetryListener;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC001_Login_Logout extends CommonFunctions{
	
	@Test()
	public void tc001_Login_Logout() throws Exception {
		CommonFunctions.childTest = CommonFunctions.parentTest.createNode("Login to Application");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(data.get("username"), data.get("password"));
		
		CommonFunctions.childTest = CommonFunctions.parentTest.createNode("Logout of Application");
		HomePage homePage = new HomePage(driver);
		homePage.logoutUser();
	}

}
