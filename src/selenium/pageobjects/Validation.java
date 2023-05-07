package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import selenium.AbstractComponent.AbstractComponent;

public class Validation extends AbstractComponent {
	
	WebDriver driver;
	
	public Validation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[id='data_date-visible']")
	WebElement reportingDate;
	
	@FindBy(id="btn-submit")
	WebElement save;
	
	@FindBy(xpath="//div[@class='elx-form-error elx-form-error-btm']/span[2]")
	WebElement message;
	
	By messageEle = By.xpath("//div[@class='elx-form-error elx-form-error-btm']/span[2]");
	
	public void reportingDateMessage() throws InterruptedException {

		waitForElementToAppear(By.id("data_date-visible"));
		
		WebElement element = reportingDate;
		
		Actions a = new Actions(driver);

		// Test Case 1 : date field is blank.
		a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyDown(Keys.BACK_SPACE).build().perform();

		save.click();

		waitForElementToAppear(messageEle);

		String validationMessage1 = message.getText();

		// assertion if expected message is showing on screen.
		Assert.assertEquals(validationMessage1, "Please Reporting Date.");
		//Please enter Reporting Date.
		
		// Test case 2 : date field is filled with text
		a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys("fdsjd").build().perform();
		
		save.click();

		Thread.sleep(4000);
		waitForElementToAppear(messageEle);

		String validationMessage2 = message.getText();
		
		// assertion if expected message is showing on screen.
		Assert.assertEquals(validationMessage2, "Please enter Reporting Date in DD/MM/YYYY format.");
		
		// Test Case 3 : date field is filled with wrong date format
        a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys("2022/5/12").build().perform();
		
		save.click();

		waitForElementToAppear(messageEle);

		String validationMessage3 = message.getText();
		
		// assertion if expected message is showing on screen.
		Assert.assertEquals(validationMessage3, "Please enter Reporting Date in DD/MM/YYYY format.");
		
		a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyDown(Keys.BACK_SPACE).build().perform();		
	}

}
