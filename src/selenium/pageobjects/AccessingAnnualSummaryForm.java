package selenium.pageobjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.AbstractComponent.AbstractComponent;

public class AccessingAnnualSummaryForm extends AbstractComponent {
	
	public WebDriver driver;

	public AccessingAnnualSummaryForm(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="monthlyEnergyAnnualSummaryBtn")
	WebElement energyASForm;
	
	@FindBy(id="convene-data")
	WebElement dataCollection;
	
	@FindBy(xpath="//div[@class='dashboard-header elx-panel']/div[2]/div")
	List<WebElement> dataCollectionTabs;
	
	@FindBy(xpath="//div[@id='gridbox-Energy16V-content']/div/button[1][@style='display:none;']") 
	WebElement aSFormInvisible;
	
	By waitForDataCollectionTab = By.cssSelector(".dashboard-pages.hide-in-mobile div:nth-child(2)");
	
	public boolean accessingEnergyAS() throws InterruptedException {

		dataCollection.click();

		waitForElementToAppear(waitForDataCollectionTab);

		for (WebElement tab : dataCollectionTabs) {
			if (tab.getText().equalsIgnoreCase("Energy")) {
				// Accessing Energy data collection
				tab.click();

				// Accessing the new Energy data collection form
				Thread.sleep(8000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("document.querySelector('#dashboard-scroller').scrollBy(0,5000)");
				Thread.sleep(10000);
				if (!energyASForm.isDisplayed()) {
					if (!aSFormInvisible.isDisplayed()) {
						break;
					}
				}
				
				
				energyASForm.click();
				Set<String> windows = driver.getWindowHandles();
		        Iterator<String> it = windows.iterator();
		        String parentId= it.next();
		        String childId = it.next();
		        driver.switchTo().window(childId);

				return true;
			}
		}
		System.out.println("user is Board Management or User admin");
		return false;
	}

}
