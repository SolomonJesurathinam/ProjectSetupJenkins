package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testData.ExcelData;

public class CommonFunctions {
	
	public static WebDriver driver;
	public static Properties properties = null;
	public static ExtentReports extentReports;
	public static ExtentSparkReporter reporter;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	public static String testName;
	ActionDriver actionDriver;
	public static ExcelData excelData;
	public static Map<String, String> data;
	
	
	@BeforeClass
	public void launchBrowser() throws IOException {
		
		loadPropertyfile();
		
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			//write log to report
		}
		
		driver.manage().window().maximize();
		actionDriver = new ActionDriver();
		childTest = parentTest.createNode("Login");
		actionDriver.navigateToApplication(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	//Extent Reports
	@BeforeSuite
	public void beforeSuite() {
		extentReports = new ExtentReports();
		reporter = new ExtentSparkReporter("./target/spark"+dateFormat()+".html");
		extentReports.attachReporter(reporter);
		
		//data configuration
		excelData = new ExcelData();
	}
	
	@BeforeTest
	public void beforeTest() throws EncryptedDocumentException, IOException {
		parentTest = extentReports.createTest(this.getClass().getName());
		testName = this.getClass().getName();
		
		//get Data values for each test
		data = excelData.getExcelData(this.getClass().getSimpleName());
		System.out.println(this.getClass().getSimpleName());
	}
	
	@AfterTest
	public void afterTest() {
		extentReports.flush();
	}
	
	public Properties loadPropertyfile() throws IOException {
		FileInputStream file = new FileInputStream(new File("./Configuration/config.properties"));
		properties = new Properties();
		properties.load(file);
		return properties;
	}
	
	
	public static String dateFormat() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	
	//method for screenshot
	public static void screenShot(String screenShotName) {
		
		try {
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File target = new File("./Screenshots//"+testName+"//"+screenShotName+dateFormat()+".png");
			FileUtils.copyFile(src, target);
		}catch(Exception e) {
			if(childTest!=null) {
				childTest.fail("Unable to take screenshots");
				childTest.info(e);
			}
		}
		
	}
	
	
}
