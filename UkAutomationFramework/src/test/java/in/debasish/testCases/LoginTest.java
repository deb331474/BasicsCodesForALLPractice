package in.debasish.testCases;

import org.testng.annotations.Test;

import in.debasish.pageObjects.HomePage;

public class LoginTest extends BaseTest{
	
	@Test
	public void loginToApp() {
		
		HomePage hp=new HomePage(driver);
		hp.clickOnSignLink();
		log.info("Clicked on the signIn link");
		hp.getlandingPage("prusethdebasish@gmail.com", "debudon@211");
		log.info("Successfully logged into the application");
	}

}
