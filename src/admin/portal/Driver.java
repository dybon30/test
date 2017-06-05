package admin.portal;

import org.testng.annotations.Parameters;

import java.awt.Toolkit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Driver {
	public static WebDriver driver;
	public static SoftAssert ss;
	protected WebDriverWait wait = null;
	
  @Parameters("browser")	
  @BeforeTest
  public void beforeSuite(String browser) {
	  
	  System.out.println(browser);
	  //select which browser to perform the test	  
	  if(browser.equalsIgnoreCase("firefox")) {
		  //System.setProperty("webdriver.gecko.driver","geckodriver");
		  driver = new FirefoxDriver();
	  }else if(browser.equalsIgnoreCase("chrome")){
		  System.setProperty("webdriver.chrome.driver","chromedriver");
		  driver = new ChromeDriver();
	  }else if(browser.equalsIgnoreCase("safari")){
		  driver = new SafariDriver();
	  }else if(browser.equalsIgnoreCase("opera")){
		  System.setProperty("webdriver.opera.driver","operadriver");
		  driver = new OperaDriver();
	  }
	  
	  //time out and maximize the browser
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //driver.manage().window().maximize();
	  maximizeScreen(driver);
  }

  @AfterTest
  public void afterSuite() {
	  driver.quit();
  }
  
  //this to maximize all browsers
  public void maximizeScreen(WebDriver driver) {
	    java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Point position = new Point(0, 0);
	    driver.manage().window().setPosition(position);
	    Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
	    driver.manage().window().setSize(maximizedScreenSize);
  }
  
//method to check if the page loaded completely
  public static void waitForPageToBeReady() 
  {
      JavascriptExecutor js = (JavascriptExecutor)driver;

      //This loop will rotate for 100 times to check If page Is ready after every 1 second.
      //You can replace your if you wants to Increase or decrease wait time.
      for (int i=0; i<400; i++)
      { 
          try 
          {
              Thread.sleep(1000);
          }catch (InterruptedException e) {} 
          //To check page ready state.

          if (js.executeScript("return document.readyState").toString().equals("complete"))
          { 
              break; 
          }   
        }
   }
}
