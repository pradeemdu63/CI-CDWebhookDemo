package RSEcommerceSelenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RSEcommerceSelenium.PageObjects.CartPage;
import RSEcommerceSelenium.PageObjects.OrderHistoryPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver){
		
		this.driver = driver;
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartLink;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderLink;
	
	public void waitForElementToAppear(By locatorBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait until all the products are loaded completely
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorBy));
	}
	
	public void waitForwebElementToAppear(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait until all the products are loaded completely
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForElementToDisappear(WebElement locatorElm) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(locatorElm));
	}
	
	public void waitForElementToBeClickable(WebElement locatorElm) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(locatorElm));
	}
	
	
	public void waitForSelectedElement(WebElement locatorElm) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeSelected(locatorElm));
	}
	
	/*
	 * public void validateAssertTrue(boolean match) {
	 * 
	 * Assert.assertTrue(match); }
	 */
	public void ScrollPage() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	
	public void scrollToElement(WebElement ele) {
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}
	
	public void scrollBottom() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public CartPage goToCartPage() {
		cartLink.click();
		return new CartPage(driver);
	}
	
	public OrderHistoryPage goToOrdersPage() {
		orderLink.click();
		return new OrderHistoryPage(driver);
	}
}
