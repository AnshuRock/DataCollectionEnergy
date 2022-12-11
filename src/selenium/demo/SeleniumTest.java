package selenium.demo;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

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

		System.setProperty("webdriver.edge.driver", "D:\\edgedriver_win64\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		driver.get("https://test.esgconvene.com");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
		
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
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("electricity.total")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location")));
		WebElement scope = driver.findElement(By.id("location"));
		Select location = new Select(scope);
		
		location.selectByVisibleText("Malaysia");
		
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

}
