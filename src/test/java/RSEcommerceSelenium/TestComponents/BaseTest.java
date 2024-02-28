package RSEcommerceSelenium.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RSEcommerceSelenium.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDrivers() throws IOException {
			
		//Properties class
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\RSEcommerceSelenium\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName =System.getProperty("browser")!= null ?  System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		return driver;
	}
	
	//Json File Reader 

	public List<HashMap<String,String>> getJsonData(String filePath) throws IOException {
		
		//String filePath = "src\\test\\java\\RSEcommerceSelenium\\testData\\PurchaseOrder.json";
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = 
				mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		
	}
	
	//screenshot
	public String getScreenshot(String testCaseName, WebDriver driver ) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile	= ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	//Launch Application
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDrivers();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
