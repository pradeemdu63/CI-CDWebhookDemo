package RSEcommerceSelenium.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RSEcommerceSelenium.PageObjects.CartPage;
import RSEcommerceSelenium.PageObjects.CheckoutPage;
import RSEcommerceSelenium.PageObjects.ConfirmationPage;
import RSEcommerceSelenium.PageObjects.LandingPage;
import RSEcommerceSelenium.PageObjects.OrderHistoryPage;
import RSEcommerceSelenium.PageObjects.ProductCatalogue;
import RSEcommerceSelenium.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class submitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData" , groups = {"Purchase"})
	public void submitOrders(HashMap<String,String> input) throws IOException {
		
		
		//LandingPage landingPage = launchApplication();
		ProductCatalogue productCataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products = productCataloguePage.getProducts();
		productCataloguePage.addToCartProducts(input.get("productName"));
		CartPage cartPage = productCataloguePage.goToCartPage();
		
		Boolean match =cartPage.verifyProductTitles(input.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.enterCountry("india");
		ConfirmationPage confirmPage = checkoutPage.orderSubmission();
	
		String confirmMessage = confirmPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods = {"submitOrders"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCataloguePage = landingPage.loginApplication("dheejose@gmail.com", "Dheejose2@");
				
		OrderHistoryPage ordersPage = productCataloguePage.goToOrdersPage();
		ordersPage.verifyOrderHistory(productName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		 List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//RSEcommerceSelenium//testData//PurchaseOrder.json");
		return new Object[][] {
			{data.get(0)} , {data.get(1)}
			};
	}
	
	
	
	/*
	 * @DataProvider public Object[][] getData() { HashMap<String,String> map = new
	 * HashMap<String,String>(); map.put("email", "samJose@gmail.com");
	 * map.put("password", "Hello@123"); map.put("productName","ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
	 * "anishka@gmail.com"); map1.put("password", "Hello123");
	 * map1.put("productName","ADIDAS ORIGINAL"); return new Object[][] { {map} ,
	 * {map1} }; }
	 */
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][] {
	 * {"samJose@gmail.com", "Hello@123","ZARA COAT 3"}, {"anishka@gmail.com",
	 * "Hello123","ADIDAS ORIGINAL"} }; }
	 */
}
