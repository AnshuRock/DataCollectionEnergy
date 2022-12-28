package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Validation {
	
	public void reportingDateMessage(WebDriver driver, WebDriverWait wait) {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("input[id='data_date-visible']")));
		
		WebElement element = driver.findElement(By.cssSelector("input[id='data_date-visible']"));
		
		String value = Keys.chord(Keys.CONTROL, "A");

		element.sendKeys(value, " ");

		driver.findElement(By.id("btn-submit")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".elx-form-error-text")));

		String validationMessage = driver.findElement(By.cssSelector(".elx-form-error-text")).getText();

		// assertion if expected message is showing on screen.
		Assert.assertEquals(validationMessage, "Please enter Reporting Date in DD/MM/YYYY format.");

	}

}
