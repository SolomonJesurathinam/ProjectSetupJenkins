package commonFunctions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;

public class ActionDriver {
	
	public WebDriver driver;
	
	public ActionDriver() {
		driver = CommonFunctions.driver;
	}
	
	/**
	 * Used to navigate to application
	 * @param url -- URL to navigate to application 
	 */
	public void navigateToApplication(String url) {
		try {
			driver.get(url);
			CommonFunctions.childTest.pass("Successfully navigated to "+url);
		}catch(Exception e) {
			CommonFunctions.childTest.fail("Unable to navigate to "+url);
		}
	}
	
	/**
	 * Used to perform click on links, buttons, checkboxes
	 * @param ElementName -- Get from PageObjects
	 * @param eleName -- Name of the element
	 * @throws Exception 
	 */
	public void click(WebElement ElementName, String eleName) throws Exception {
		try {
			ElementName.click();
			CommonFunctions.childTest.pass("Performed click action on : "+eleName);
		}catch(Exception e) {
			CommonFunctions.childTest.fail("Unable to perform click action on : "+eleName,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot()).build());
			CommonFunctions.childTest.info(e);
			throw e;
		}
	}
	
	/**
	 * Used to perform type action in textbox or text area
	 * @param ElementName -- Get from PageObjects
	 * @param testData -- Get the test data from excel sheet
	 * @param eleName -- Name of the element
	 * @throws Exception 
	 */
	public void type(WebElement ElementName, String testData, String eleName) throws Exception {
		try {
			ElementName.sendKeys(testData);
			CommonFunctions.childTest.pass("Successfully performed type action in : "+eleName+" with test data : "+testData);
		}catch(Exception e) {
			CommonFunctions.childTest.fail("Unable to perform type action on : "+eleName +" with test data "+testData, 
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot()).build());
			CommonFunctions.childTest.info(e);
			throw e;
		}
	}
		
	public String screenshot() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
	
	public void verificationEquals(String actual, WebElement expected) throws Exception {
		try {
			Assert.assertEquals(actual, expected.getText());
			CommonFunctions.childTest.pass("Successfully validated and "+actual+" value is equal to "+expected.getText());
		}catch(Exception e) {
			CommonFunctions.childTest.fail(actual+" value is not equal to "+expected.getText(), 
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot()).build());
			CommonFunctions.childTest.info(e);
			throw e;
		}
	}
	

}
