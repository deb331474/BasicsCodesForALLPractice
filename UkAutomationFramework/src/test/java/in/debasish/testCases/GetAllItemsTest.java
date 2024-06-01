package in.debasish.testCases;

import org.testng.annotations.Test;

import in.debasish.pageObjects.HomePage;
import in.debasish.pageObjects.PrductDetailsPage;

public class GetAllItemsTest extends BaseTest{
	
	@Test
	public void getAlltheContent() {
		HomePage homePage=new HomePage(driver);
		homePage.clickOnSignLink();
		log.info("Clicked on the signIn link");
		homePage.getlandingPage("prusethdebasish@gmail.com", "debudon@211");
		log.info("Successfully logged into the application");
		PrductDetailsPage pdp= homePage.getProductDetailsPage();
		pdp.getAlltheProducts();
	}

}
