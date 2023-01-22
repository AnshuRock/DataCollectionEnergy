package selenium.demo;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class SeleniumTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static void main(String[] args) throws InterruptedException {


		Validation validation = new Validation();
		
		// login
        login();
		
        // accessing Energy Data Collection and new energy data collection form
        accessingEnergyDataCollect();
		
        // test if location is empty
        if (emptyScope()) {
			System.out.println("Collabortor is not assigned to this topic");
			
			return;
		}
        
        // performing validation test case
       validation.reportingDateMessage(driver, wait);
        
       movingToOtherTabPrompt();
       
		// choosing scope and a sub-location if any
		scopeAndSubLocation();
		
		// choosing a date
	    reportingDate();
		
		WebElement totalElectricity = driver.findElement(By.id("electricity.total"));
		
		totalValue(totalElectricity);
		
		// inputing value to heating total field.
		driver.findElement(By.id("elx-tabId-heating")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("heating.total")));
		WebElement totalHeating = driver.findElement(By.id("heating.total"));
		
		totalValue(totalHeating);
		
		// inputing value to Gas/Fuel Oil
         gasFuelOil();
        
        // attach a file
        attachAFile();
		
		// submitting the form
		submitForm();
		
	}
	
	public static void totalValue(WebElement element) {
		
        String value = Keys.chord(Keys.CONTROL, "A");
		
		element.sendKeys(value, "5000");
		
	}
	
	public static void scopeAndSubLocation() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location")));
		WebElement scope = driver.findElement(By.id("location"));
		Select location = new Select(scope);
		
		location.selectByVisibleText("Malaysia");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sublocation")));
		
        if (driver.findElement(By.cssSelector("#sublocation")).isDisplayed()){
			
			WebElement subLocations = driver.findElement(By.cssSelector("#sublocation"));
			
			Select subLocat = new Select(subLocations);
			
			subLocat.selectByIndex(1);
		}
		
	}
	
	public static void reportingDate() {
		driver.findElement(
				By.xpath("//div[@id='row41']/div[@class='elx-form-row-body']/div[2]/div[2]/div/div/div/div/div/i"))
				.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='year-chooser arrow-chooser']/div[3]/select")));

		WebElement years = driver.findElement(By.xpath("//div[@class='year-chooser arrow-chooser']/div[3]/select"));

		Select year = new Select(years);

		year.selectByVisibleText("2021");

		WebElement months = driver.findElement(By.xpath("//div[@class='month-chooser arrow-chooser']/div[3]/select"));

		Select month = new Select(months);

		month.selectByVisibleText("September");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='date-chooser']/table/tr/td")));

		List<WebElement> days = driver.findElements(By.xpath("//div[@class='date-chooser']/table/tr/td"));

		for (WebElement daySelect : days) {

			if (daySelect.getText().equalsIgnoreCase("20")) {
				daySelect.click();
				break;
			}
		}
	}
	
	public static void gasFuelOil() {

		driver.findElement(By.id("elx-tabId-gas_fuel")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("gas_fuel.source_type-0")));

		driver.findElement(By.id("gas_fuel.source_type-0")).click();

		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("button[id='gas_fuel.add_renewable_source-gasFuelRenewAddSource']")));

		driver.findElement(By.cssSelector("button[id='gas_fuel.add_renewable_source-gasFuelRenewAddSource']")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("select[id='gas_fuel.renewable_source']")));

		WebElement renewableResources = driver.findElement(By.cssSelector("select[id='gas_fuel.renewable_source']"));

		Select renewables = new Select(renewableResources);

		renewables.selectByIndex(1);

		driver.findElement(By.id("gas_fuel.renewable_quantity")).sendKeys("4000");

		driver.findElement(By.id("btn-save")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.id("gas_fuel.add_non_renewable_source-gasFuelNonRenewAddSource")));

		driver.findElement(By.id("gas_fuel.add_non_renewable_source-gasFuelNonRenewAddSource")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gas_fuel.non_renewable_source")));

		WebElement nonrenewableRes = driver.findElement(By.name("gas_fuel.non_renewable_source"));

		Select nonRenewable = new Select(nonrenewableRes);

		nonRenewable.selectByIndex(1);

		driver.findElement(By.id("gas_fuel.non_renewable_quantity")).sendKeys("5000");

		driver.findElement(By.id("btn-save")).click();
	}
	
	public static void submitForm() {

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit")));

		driver.findElement(By.id("btn-submit")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn-review-cancel")));

		driver.findElement(By.id("btn-review-cancel")).click();
	}
	
	public static void login() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
		
		driver  = new ChromeDriver();
		
		driver.get("https://test.esgconvene.com");

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		driver.manage().window().maximize();

		System.setProperty("webdriver.edge.driver", "D:\\BrowserDriver\\edgedriver_win64\\msedgedriver.exe");

		driver.get("https://test.esgconvene.com");

		driver.manage().window().maximize();

		driver.findElement(By.id("username")).sendKeys("test_collab2");
		driver.findElement(By.name("password")).sendKeys("P@ssw0rd");

		driver.findElement(By.id("login-button")).click();

	}
	
	public static void accessingEnergyDataCollect() {
		
		// Accessing data collection tab
		driver.findElement(By.id("convene-data")).click();

		// Accessing Energy data collection
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".dashboard-pages.hide-in-mobile div:nth-child(2)")));
		driver.findElement(By.cssSelector(".dashboard-pages.hide-in-mobile div:nth-child(2)")).click();

		// Accessing the new Energy data collection form
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#gridbox-Energy8V-content div button:nth-child(1)")));
		driver.findElement(By.cssSelector("#gridbox-Energy8V-content div button:nth-child(1)")).click();

		
	}
	
	public static void attachAFile() throws InterruptedException {

		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("#electricityUpload tr th:nth-child(2) div")));
		driver.findElement(By.cssSelector("#electricityUpload tr th:nth-child(2) div")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("file")));
		driver.findElement(By.name("file")).sendKeys("D:\\IntelliJ Notes\\Basic Project Structure.docx");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='elx-button-holder']/span[2]")));
		driver.findElement(By.xpath("//div[@class='elx-button-holder']/span[2]")).click();

		Thread.sleep(2000);
		String countFile = driver.findElement(By.cssSelector("#electricityUpload tr th:nth-child(1) span:nth-child(2)"))
				.getText();

		Assert.assertEquals(countFile, "[1]");

	}
	
	public static boolean emptyScope() {
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.id("location")));
		
		WebElement ele = driver.findElement(By.id("location"));
		
		Select loc = new Select(ele);
		
		List <WebElement> locations = loc.getOptions();
		
		if (locations.size() == 1) {
			return true;
		}
		
		return false;
	}
	
	public static void movingToOtherTabPrompt() throws InterruptedException {
		
		Thread.sleep(3000);		
		driver.findElement(By.cssSelector("#convene-disclosures")).click();		
		Thread.sleep(5000);	
		System.out.println("Prompt message, to stay on the page or leave, is opened and closed by selenium");
		driver.switchTo().alert().dismiss();		
	}

}
