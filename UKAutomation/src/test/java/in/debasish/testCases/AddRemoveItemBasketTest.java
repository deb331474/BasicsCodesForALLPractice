package in.debasish.testCases;

import java.io.IOException;
import java.net.SocketException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import in.debasish.base.BasePage;
import in.debasish.pageObjects.HomePage;
import in.debasish.pageObjects.ShopContentPanel;
import in.debasish.pageObjects.ShopHomePage;
import in.debasish.pageObjects.ShopProductPage;
import in.debasish.pageObjects.ShoppingCart;

public class AddRemoveItemBasketTest extends BasePage{
	
	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void setup() throws IOException {
		driver = getDriver();
		driver.get(getUrl());
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver = null;
	}

	@Test
	public void addRemoveItem() throws Exception {
		// creating an object of the automationtesting.co.uk webpage
		HomePage home = new HomePage(driver);

		//handles cookie prompt
		home.getCookie().click();
		
		home.getTestStoreLink().click();
		

		// creating an object of the test store homepage
		ShopHomePage shopHome = new ShopHomePage(driver);
		shopHome.getProdOne().click();
		// creating an object of the shop products page (when a product has been
		// selected)
		ShopProductPage shopProd = new ShopProductPage(driver);
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();

		// creating an object of the cart content panel (once an item was added)
		ShopContentPanel cPanel = new ShopContentPanel(driver);
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();

		// creating an object for the shopping cart page and deleting item 2
		ShoppingCart cart = new ShoppingCart(driver);
		cart.getDeleteItemTwo().click();

		// using a wait to ensure the deletion has taken place
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));

		// printing the total amount to console
		System.out.println(cart.getTotalAmount().getText());
		
		// using an assertion to make sure the total amount is what we are expecting
		Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");

	}

}
