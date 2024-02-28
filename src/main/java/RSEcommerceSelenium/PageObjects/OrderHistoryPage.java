package RSEcommerceSelenium.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSEcommerceSelenium.AbstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent{

	WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
		
	public boolean verifyOrderHistory(String productName) {
		boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
}
