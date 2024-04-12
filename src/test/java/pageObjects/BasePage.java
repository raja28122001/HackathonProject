package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;
	
	public BasePage(WebDriver d) {
		driver = d;
		PageFactory.initElements(driver, this);
	}
	
	
}
