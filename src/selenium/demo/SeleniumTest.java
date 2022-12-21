package selenium.demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.edge.driver", "D:\\BrowserDriver\\edgedriver_win64\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		driver.get("https://test.esgconvene.com");
		
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		driver.findElement(By.id("username")).sendKeys("test_cso");
		driver.findElement(By.name("password")).sendKeys("P@ssw0rd");
		
		driver.findElement(By.id("login-button")).click();
		
		// Accessing data collection tab
		driver.findElement(By.id("convene-data")).click();
		
		// Accessing Energy data collection 
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector(".dashboard-pages.hide-in-mobile div:nth-child(2)")));
		driver.findElement(By.cssSelector(".dashboard-pages.hide-in-mobile div:nth-child(2)")).click();
		
		// Accessing the new Energy data collection form
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("#gridbox-Energy8V-content div button:nth-child(1)")));
		driver.findElement(By.cssSelector("#gridbox-Energy8V-content div button:nth-child(1)")).click();
		
		// Selecting a scope and inputing value to Electricity total field.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location")));
		WebElement scope = driver.findElement(By.id("location"));
		Select location = new Select(scope);
		
		location.selectByVisibleText("Malaysia");
		
		// choosing a sub-location if any
		subLocation(driver, wait);
		
		// choosing a date
		driver.findElement(
			By.xpath("//div[@id='row41']/div[@class='elx-form-row-body']/div[2]/div[2]/div/div/div/div/div/i")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='year-chooser arrow-chooser']/div[3]/select")));
		
		WebElement years = driver.findElement(By.xpath("//div[@class='year-chooser arrow-chooser']/div[3]/select"));
		
		Select year = new Select(years);
		
		year.selectByVisibleText("2021");
		
		
		WebElement months = driver.findElement(By.xpath("//div[@class='month-chooser arrow-chooser']/div[3]/select"));
		
		Select month = new Select(months);
		
		month.selectByVisibleText("September");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='date-chooser']/table/tr/td")));
		
		List<WebElement>days = driver.findElements(By.xpath("//div[@class='date-chooser']/table/tr/td"));
		
		for (WebElement daySelect : days) {
			
			if (daySelect.getText().equalsIgnoreCase("20")) {
				daySelect.click();
				break;
			}
		}
		
		WebElement totalElectricity = driver.findElement(By.id("electricity.total"));
		
		totalValue(totalElectricity);
		
		// inputing value to heating total field.
		driver.findElement(By.id("elx-tabId-heating")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("heating.total")));
		WebElement totalHeating = driver.findElement(By.id("heating.total"));
		
		totalValue(totalHeating);
		
		// submitting the form
		driver.findElement(By.xpath("//div[@class='input-group']/button[2]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btn-review-cancel")));
		
		driver.findElement(By.id("btn-review-cancel")).click();
			
		
	}
	
	public static void totalValue(WebElement element) {
		
        String value = Keys.chord(Keys.CONTROL, "A");
		
		element.sendKeys(value, "5000");
		
	}
	
	public static void subLocation(WebDriver driver, WebDriverWait wait) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sublocation")));
		
        if (driver.findElement(By.cssSelector("#sublocation")).isDisplayed()){
			
			WebElement subLocations = driver.findElement(By.cssSelector("#sublocation"));
			
			Select subLocat = new Select(subLocations);
			
			subLocat.selectByIndex(1);
		}
		
	}

}
