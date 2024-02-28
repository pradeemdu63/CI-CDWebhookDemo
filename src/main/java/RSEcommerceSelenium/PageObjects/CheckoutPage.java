package RSEcommerceSelenium.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSEcommerceSelenium.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(css = "[placeholder*='Country']")
	WebElement countryDropdown;
	
	@FindBy(css = ".action__submit")
	WebElement submitOrder;
	
	@FindBy(css ="[class*='ta-results'] button:last-child")
	WebElement selectCountry;
	
	By countriesList = By.cssSelector("[class*='ta-results']");
	
	public void enterCountry(String countryName) {
		
		Actions a = new Actions(driver);
		//through actions sendkeys passing locator and text
		a.sendKeys((countryDropdown),countryName).build().perform();
		waitForElementToAppear(countriesList);
		selectCountry.click();
		//scrollToElement(submitOrder);
		//ScrollPage();
		scrollBottom();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ConfirmationPage orderSubmission() {
		waitForElementToBeClickable(submitOrder);
		submitOrder.click();
		return new ConfirmationPage(driver);
	}
	

	
	
}
