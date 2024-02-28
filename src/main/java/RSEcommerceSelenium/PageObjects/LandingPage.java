package RSEcommerceSelenium.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSEcommerceSelenium.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordElm;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css = "#toast-container")
	WebElement toastMessage;
	
//Action Methods
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		
		waitForwebElementToAppear(toastMessage);
		String text = toastMessage.getText();
		return text;
	}
	
	public ProductCatalogue loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		passwordElm.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
}
