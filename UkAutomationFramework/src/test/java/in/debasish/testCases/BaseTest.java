package in.debasish.testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import in.debasish.extentListeners.ExtentListeners;
import in.debasish.utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest{
	
	public static WebDriver driver;
	private static Properties or = new Properties();
	private static Properties config = new Properties();
	private static FileInputStream fis;
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static ExcelReader excel = new ExcelReader(".//src//test//resources//excel//testdata.xlsx");
	public static WebDriverWait wait;
	@BeforeSuite
	public void setUp() {
		
		PropertyConfigurator.configure(".//src//test//resources//properties//log4j.properties");

		
		try {
			fis = new FileInputStream(".//src//test//resources//properties//config.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.info("Config properties loaded !!!");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			or.load(fis);
			log.info("OR properties loaded !!!");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		if (config.getProperty("browser").equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Launching Chrome !!!");

		} else if (config.getProperty("browser").equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Launching Firefox !!!");

		}

		driver.get(config.getProperty("testsiteurl"));
		log.info("Navigated to : " + config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
		log.info("Test Execution completed !!!");
	}

}
