package in.debasish.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import in.debasish.AbstractComponents.AbstractComponents;

public class PrductDetailsPage extends AbstractComponents{

	public PrductDetailsPage(WebDriver driver) {
		super(driver);
		
		
	}
	
	@FindBy(xpath = "//div[@class='products']//article")
	List<WebElement> AllItems;
	
	public void getAlltheProducts() {
		for(WebElement ws:AllItems) {
			System.out.println(ws.getText());
		}
	}

}
