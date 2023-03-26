package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import selenium.AbstractComponent.AbstractComponent;

public class DataCollectionForm extends AbstractComponent {

	WebDriver driver;
	
	public DataCollectionForm(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="location")
	WebElement scope;
	
	@FindBy(css="#convene-disclosures")
	WebElement otherTab;
	
	@FindBy(css="#sublocation")
	WebElement subScope;
	
	@FindBy(xpath="//div[@id='row']/div[@class='elx-form-row-body']/div[2]/div[2]/div/div/div/div/div/i")
	WebElement datePicker;
	
	@FindBy(xpath="//div[@class='year-chooser arrow-chooser']/div[3]/select")
	WebElement chooseYear;
	
	@FindBy(xpath="//div[@class='month-chooser arrow-chooser']/div[3]/select")
	WebElement chooseMonth;
	
	@FindBy(xpath="//div[@class='date-chooser']/table/tr/td")
	List<WebElement> chooseDays;
	
	@FindBy(id="electricity.total")
	WebElement selectTotalElectricity;
	
	@FindBy(id="elx-tabId-heating")
	WebElement heating;
	
	@FindBy(id="heating.total")
	WebElement totalHeating;
	
	@FindBy(css="#heatingUpload tr th:nth-child(2) div")
	WebElement attachFileButton;
	
	@FindBy(name="file")
	WebElement attachFile;
	
	@FindBy(xpath="//div[@class='elx-button-holder']/span[2]")
	WebElement fileSelected;
	
	@FindBy(xpath="//table[@id='electricityUpload']/tr/th[1]/span[2]")
	WebElement countFile;
	
	@FindBy(id="btn-submit")
	WebElement submit;
	
	@FindBy(id="btn-review-cancel")
	WebElement backToDashBoard;
	
	public boolean emptyScope() {

		waitForElementToAppear(By.id("location"));

		WebElement ele = scope;

		Select loc = new Select(ele);

		List<WebElement> locations = loc.getOptions();

		if (locations.size() == 1) {
			return true;
		}

		return false;
	}

	public void movingToOtherTabPrompt() throws InterruptedException {

		waitForElementToAppear(By.cssSelector("#location"));
		scope.click();
		Thread.sleep(3000);
		otherTab.click();
		Thread.sleep(3000);
		System.out.println("Prompt message, to stay on the page or leave, is opened and closed by selenium");
		driver.switchTo().alert().dismiss();
	}
	
	public void scopeAndSubLocation() throws InterruptedException {

		waitForElementToAppear(By.id("location"));
		
		Select location = new Select(scope);

//		location.selectByVisibleText("Malaysia");
		location.selectByIndex(1);
		Thread.sleep(2000);

//		waitForElementToAppear(By.cssSelector("#sublocation"));

		if (subScope.isDisplayed()) {

			WebElement subLocations = subScope;

			Select subLocat = new Select(subLocations);

			subLocat.selectByIndex(1);
		}
	}
	
	public void reportingDate() {
		datePicker.click();

		waitForElementToAppear(By.xpath("//div[@class='year-chooser arrow-chooser']/div[3]/select"));

		WebElement years = chooseYear;

		Select year = new Select(years);

		year.selectByVisibleText("2021");

		WebElement months = chooseMonth;

		Select month = new Select(months);

		month.selectByVisibleText("September");

		waitForElementToAppear(By.xpath("//div[@class='date-chooser']/table/tr/td"));

		List<WebElement> days = chooseDays;

		for (WebElement daySelect : days) {

			if (daySelect.getText().equalsIgnoreCase("20")) {
				daySelect.click();
				break;
			}
		}
	}
	
	public WebElement selectTotalElectricity() {
		return selectTotalElectricity;	
	}
	
	public void totalValue(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys("5000")
				.build().perform();
	}
	
	public void inputHeatingTotal() {
		heating.click();
		waitForElementToAppear(By.id("heating.total"));
		totalValue(totalHeating);
	}
	
	public void attachAFile() throws InterruptedException {
		attachFileButton.click();
		waitForElementToBeClicked(By.name("file"));
		attachFile.sendKeys("D:\\IntelliJ Notes\\Basic Project Structure.docx");
		waitForElementToBeClicked(By.xpath("//div[@class='elx-button-holder']/span[2]"));
		fileSelected.click();
		Thread.sleep(2000);
		String count = countFile.getText();
		
		System.out.println(count);

		//Assert.assertEquals(countFile, "[1]");

	}
	
	public void submitForm() {
		waitForElementToBeClicked(By.id("btn-submit"));
		submit.click();
		waitForElementToBeClicked(By.cssSelector("#btn-review-cancel"));
		backToDashBoard.click();
	}
}
