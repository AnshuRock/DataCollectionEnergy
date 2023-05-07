package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import selenium.AbstractComponent.AbstractComponent;

public class AnnualSummaryForm extends AbstractComponent {

	public WebDriver driver;
	public AnnualSummaryForm(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(id="esg.location")
	WebElement scope;
	
	@FindBy(id="esg.summary")
	WebElement summaryText;
	
	@FindBy(id="btn-submit")
	WebElement forSave;
	
	@FindBy(css="span[title='Open Filter Panel']")
	WebElement sidePanel;
	
	@FindBy(css="div[id='gridbox-Energy15V-content'] div:nth-child(1) table tbody tr:last-child td:nth-child(2)")
	WebElement summarytTextMatch;
	
	@FindBy(css=".contents select")
	WebElement yearFilter;
	
	public String textForSummary = "annual summary";
	
	public boolean selectScope() throws InterruptedException {
		
		waitForElementToAppear(By.id("esg.location"));
		WebElement ele = scope;
		Select select = new Select(ele);
		
		List<WebElement> locations = select.getOptions();

		if (locations.size() == 0) {
			return false;
		}

		select.selectByIndex(2);
		return true;	
	}
	
	public void writingSummary() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('#elx-form-runtime').scrollBy(0,5000)");
		summaryText.sendKeys(textForSummary);
	}

	public void submittingForm() throws InterruptedException {
		waitForElementToAppear(By.cssSelector(".btn.positive"));
		forSave.click();
	}
	
	public void verifyLatestAS() throws InterruptedException {
		waitForElementToBeClicked(By.cssSelector("span[title='Open Filter Panel']"));
		sidePanel.click();
//		waitForElementToAppear(By.cssSelector(".contents select"));
		Select year = new Select(yearFilter);
		List<WebElement> years = year.getOptions();
		year.selectByIndex(years.size()-1);
	
		Thread.sleep(5000);
		waitForElementToAppear(By.id("dashboard-scroller"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('#dashboard-scroller').scrollBy(0,5000)");
		Thread.sleep(8000);
		js.
		executeScript("document.querySelector(\"div[id='gridbox-Energy15V-content'] div:nth-child(1) div:nth-child(1) div\").scrollTop=5000");
		
		Assert.assertEquals(summarytTextMatch.getText(),textForSummary);
	}
}
