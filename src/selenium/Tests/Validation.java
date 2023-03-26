package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Validation {
	
	public void reportingDateMessage(WebDriver driver, WebDriverWait wait) throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("input[id='data_date-visible']")));
		
		WebElement element = driver.findElement(By.cssSelector("input[id='data_date-visible']"));
		
		Actions a = new Actions(driver);

		// Test Case 1 : date field is blank.
		a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyDown(Keys.BACK_SPACE).build().perform();

		driver.findElement(By.id("btn-submit")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='elx-form-error elx-form-error-btm']/span[2]")));

		String validationMessage1 = driver.findElement(
				By.xpath("//div[@class='elx-form-error elx-form-error-btm']/span[2]")).getText();

		// assertion if expected message is showing on screen.
		Assert.assertEquals(validationMessage1, "Please enter Reporting Date.");
		
		// Test case 2 : date field is filled with text
		a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys("fdsjd").build().perform();
		
		driver.findElement(By.id("btn-submit")).click();

		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='elx-form-error elx-form-error-btm']/span[2]")));

		String validationMessage2 = driver.findElement(
				By.xpath("//div[@class='elx-form-error elx-form-error-btm']/span[2]")).getText();
		
		// assertion if expected message is showing on screen.
		Assert.assertEquals(validationMessage2, "Please enter Reporting Date in DD/MM/YYYY format.");
		
		// Test Case 3 : date field is filled with wrong date format
        a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys("2022/5/12").build().perform();
		
		driver.findElement(By.id("btn-submit")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='elx-form-error elx-form-error-btm']/span[2]")));

		String validationMessage3 = driver.findElement(
				By.xpath("//div[@class='elx-form-error elx-form-error-btm']/span[2]")).getText();
		
		// assertion if expected message is showing on screen.
		Assert.assertEquals(validationMessage3, "Please enter Reporting Date in DD/MM/YYYY format.");
		
		a.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("A").keyDown(Keys.BACK_SPACE).build().perform();		
	}

}
