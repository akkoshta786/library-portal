import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumtest {
  private static WebDriver driver;
  
  /*public void setUp() {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nichketa\\Downloads\\seldriver\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("http://localhost:9095/capstone/login");*/
  @Before
  public void initDriver() {
	 // String path = System.getProperty("user.dir");
	  //System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\java\\webdriver\\chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nichketa\\Downloads\\seldriver\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("http://localhost:9095/capstone/login");
  }
  
  @Test
  public void  verifyTitle()
  {
	  System.out.println("In Verify Title Test Case");
	  String title=driver.getTitle();
	  assertFalse(title.contains("Sign In"));
  }
  
  
  
    @After
  public void tearDown() {
	  driver.quit();
  
  }
}
