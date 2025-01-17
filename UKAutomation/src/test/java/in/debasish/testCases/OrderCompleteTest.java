package in.debasish.testCases;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import in.debasish.base.BasePage;
import in.debasish.pageObjects.HomePage;
import in.debasish.pageObjects.OrderFormDelivery;
import in.debasish.pageObjects.OrderFormPayment;
import in.debasish.pageObjects.OrderFormPersInfo;
import in.debasish.pageObjects.OrderFormShippingMethod;
import in.debasish.pageObjects.ShopContentPanel;
import in.debasish.pageObjects.ShopHomePage;
import in.debasish.pageObjects.ShopProductPage;
import in.debasish.pageObjects.ShoppingCart;


@Listeners(in.debasish.resources.Listeners.class)
public class OrderCompleteTest extends BasePage{

	public OrderCompleteTest() throws IOException {
		super();

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
	public void endToEndTest() throws InterruptedException, IOException {

		// creating an object of the automationtesting.co.uk webpage
		HomePage home = new HomePage(driver);

		//handles cookie prompt
		home.getCookie().click();
		
		home.getTestStoreLink().click();

		// creating an object of the test store homepage
		ShopHomePage shopHome = new ShopHomePage(driver);
		shopHome.getProdOne().click();

		// creating an object of the shop products page (when a product has been selected)
		ShopProductPage shopProd = new ShopProductPage(driver);
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();
		
		Thread.sleep(5000);

		// creating an object of the cart content panel (once an item was added)
		ShopContentPanel cPanel = new ShopContentPanel(driver);
		cPanel.getCheckoutBtn().click();
		
		Thread.sleep(5000);

		// creating an object of the shopping cart page (all items selected)
		ShoppingCart cart = new ShoppingCart(driver);
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();

		// creating an object of the order personal information page
		OrderFormPersInfo pInfo = new OrderFormPersInfo(driver);
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("John");
		pInfo.getLastnameField().sendKeys("Smith");
		pInfo.getEmailField().sendKeys("johnsmith@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();

		// creating an object of the order delivery info page
		OrderFormDelivery orderDelivery = new OrderFormDelivery(driver);
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();

		// creating an object of the shipping method page
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod(driver);
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();

		// creating an object of the payment options page
		OrderFormPayment orderPay = new OrderFormPayment(driver);
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
	}

	
	

}
