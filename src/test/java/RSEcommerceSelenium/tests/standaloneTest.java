package RSEcommerceSelenium.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RSEcommerceSelenium.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standaloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("samJose@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hello@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait until all the products are loaded completely
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//Using streams and filter ZARA COAT 3
		
		WebElement prod = products.stream().filter(product -> 
				product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//validating the msg product added successfully
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//Click on cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//check if the added product is in cart page
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//click on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Action class to send the text in auto suggestive dropdown
		Actions a = new Actions(driver);
		//through actions sendkeys passing locator and text
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Country']")),"india").build().perform();
		//driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("india");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
		driver.findElement(By.cssSelector("[class*='ta-results'] button:last-child")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*='action__submit']")));
		//placeorder button
		driver.findElement(By.cssSelector(".action__submit")).click();
	
		
		//THANKYOU FOR THE ORDER. msg verification
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();
		

	}
}
