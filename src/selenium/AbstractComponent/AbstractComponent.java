package selenium.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	
	
	public void waitForElementToAppear(By findBy) {
		// Accessing Energy data collection
		wait = new WebDriverWait(driver, Duration.ofSeconds(15)); 
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToBeClicked(By findBy) {
		// Accessing Energy data collection
		wait = new WebDriverWait(driver, Duration.ofSeconds(15)); 
		wait.until(ExpectedConditions
				.elementToBeClickable(findBy));
	}
}
