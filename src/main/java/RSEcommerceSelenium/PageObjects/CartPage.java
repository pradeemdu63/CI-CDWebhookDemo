package RSEcommerceSelenium.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSEcommerceSelenium.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProductsList;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutElm;
	
	
	public boolean verifyProductTitles(String productName) {
		boolean match = cartProductsList.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutElm.click();
		return new CheckoutPage(driver);
	}
	
}
