package sales;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.appium.java_client.ios.IOSDriver;

public class Driver {
	
	public static IOSDriver driver;
	public static SoftAssert ss;
	protected WebDriverWait wait = null;

	@BeforeTest
	public void beforeSuite()throws Exception{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "10.2");
		capabilities.setCapability("deviceName", "iPad Air 2");
		capabilities.setCapability("app", "/Users/dna/Desktop/Mobile Automaton/Asher Sales.app");
		capabilities.setCapability("fullReset", "true");

		driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);		
	}
	
	@AfterTest
	public void afterSuite() {
	  driver.quit();
	}
}
