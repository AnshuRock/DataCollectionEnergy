package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import selenium.AbstractComponent.AbstractComponent;

public class AccessingDataCollectionForm extends AbstractComponent {

	WebDriver driver;
	
	public AccessingDataCollectionForm(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="convene-data")
	WebElement dataCollection;
	
	@FindBy(css=".dashboard-pages.hide-in-mobile div:nth-child(2)")
	WebElement energyTab;
	
	@FindBy(css="#gridbox-Energy8V-content div button:nth-child(1)[style='display: none;']")
	WebElement energyFormInvisible;
	
	@FindBy(id="monthlyEnergyDataCollectionBtn")
	WebElement energyForm;
	
	@FindBy(xpath="//div[@class='dashboard-header elx-panel']/div[2]/div")
	List<WebElement> dataCollectionTabs;
	
	By waitForDataCollectionTab = By.cssSelector(".dashboard-pages.hide-in-mobile div:nth-child(2)");
	By waitForEnergyForm = By.cssSelector("#gridbox-Energy8V-content div button:nth-child(1)");
	
	public boolean accessingEnergyDataCollect() throws InterruptedException {
		
		dataCollection.click();
		
		waitForElementToAppear(waitForDataCollectionTab);
	
		for (WebElement tab : dataCollectionTabs) {
	    	if (tab.getText().equalsIgnoreCase("Energy")) {
	    		// Accessing Energy data collection
				tab.click();

				// Accessing the new Energy data collection form
				Thread.sleep(8000);
				if (!energyForm.isDisplayed()) {
					if (!energyFormInvisible.isDisplayed()) {
						break;
					}
				}
				//waitForElementToAppear(waitForEnergyForm);
				energyForm.click();

				return true;
	        }
	    }
	       System.out.println("user is not assigned to the topic. or user is BM/User admin");
			return false;
	}
}
// id = "monthlyEnergyDataCollectionBtn"
// #gridbox-Energy8V-content div button:nth-child(1)[style='display: none;']
