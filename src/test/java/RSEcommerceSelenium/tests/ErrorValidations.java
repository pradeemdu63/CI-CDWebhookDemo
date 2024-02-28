package RSEcommerceSelenium.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import RSEcommerceSelenium.PageObjects.CartPage;
import RSEcommerceSelenium.PageObjects.CheckoutPage;
import RSEcommerceSelenium.PageObjects.ConfirmationPage;
import RSEcommerceSelenium.PageObjects.LandingPage;
import RSEcommerceSelenium.PageObjects.ProductCatalogue;
import RSEcommerceSelenium.TestComponents.BaseTest;
import RSEcommerceSelenium.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = RSEcommerceSelenium.TestComponents.Retry.class)
	public void ValidateSubmitOrders() throws IOException {
		
		String productName = "ZARA COAT 3";
		
		//joshua.sam@gmail.com
		//ProductCatalogue productCataloguePage = landingPage.loginApplication("samJose@gmail.com", "Hello@@123");
		ProductCatalogue productCataloguePage = landingPage.loginApplication("joshua.sam@gmail.com", "Hello@@123");
		String msg = landingPage.getErrorMessage();
		Assert.assertEquals("Incorreccct email or password.",msg);

	}
	
	@Test
	public void productValidations() throws IOException {
		
		String productName = "ZARA COAT 3";
		
		//ProductCatalogue productCataloguePage = landingPage.loginApplication("anishka@gmail.com", "Hello123");
		ProductCatalogue productCataloguePage = landingPage.loginApplication("anishka@gmail.com", "Hello123");
		
		List<WebElement>products = productCataloguePage.getProducts();
		productCataloguePage.addToCartProducts(productName);
		CartPage cartPage = productCataloguePage.goToCartPage();
		
		Boolean match =cartPage.verifyProductTitles("ZARA COAT 33");
		Assert.assertFalse(match);

	}
}
