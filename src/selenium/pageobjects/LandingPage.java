package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement passwordEle;
	
	@FindBy(id="login-button")
	WebElement submit;
	
	public void loginApplication(String userName, String password) {
		username.sendKeys(userName);
		passwordEle.sendKeys(password);
		submit.click();
	}
	
	public void goTo() {
		driver.get("https://test.esgconvene.com");
	}
}
