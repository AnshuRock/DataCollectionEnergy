package selenium.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import selenium.TestComponents.BaseTest;
import selenium.pageobjects.AccessingDataCollectionForm;
import selenium.pageobjects.DataCollectionForm;
import selenium.pageobjects.LandingPage;
import selenium.pageobjects.Validation;


public class SubmitFormTest extends BaseTest {

	@Test(groups= {"Form submit"})
	public void submitForm() throws IOException, InterruptedException {

		LandingPage landingPage = launchApplication();
		landingPage.loginApplication("test_collab2", "P@ssw0rd");
		
        // accessing Energy Data Collection and new energy data collection form
		AccessingDataCollectionForm accessForm = new AccessingDataCollectionForm(driver);
		
        if (!accessForm.accessingEnergyDataCollect()) {
        	return;
        }
		
        DataCollectionForm form = new DataCollectionForm(driver);
        
        // test if location is empty
        if (form.emptyScope()) {
			System.out.println("Collabortor is not assigned to this topic");
			return;
		}
        
        form.movingToOtherTabPrompt();
       
		// choosing scope and a sub-location if any
		form.scopeAndSubLocation();
		
		// choosing a date
	    form.reportingDate();
		
	    // put value in total field of electricity
		form.totalValue(form.selectTotalElectricity());
		
		// inputing value to heating total field.
		form.inputHeatingTotal();
		
		// inputing value to Gas/Fuel Oil
        // gasFuelOil();
        
        // attach a file
        form.attachAFile();
		
		// submitting the form
		form.submitForm();
		
	}
	
	@Test
	public void validationForRDate() throws IOException, InterruptedException {

		LandingPage landingPage = launchApplication();
		landingPage.loginApplication("test_cso", "P@ssw0rd");
		
        // accessing Energy Data Collection and new energy data collection form
		AccessingDataCollectionForm accessForm = new AccessingDataCollectionForm(driver);
		
        if (!accessForm.accessingEnergyDataCollect()) {
        	return;
        }
        
        // performing validation test case
        Validation validation = new Validation(driver);
        validation.reportingDateMessage();
	}
	
	@Test
	public void bardManagementAccess() throws IOException, InterruptedException {

		LandingPage landingPage = launchApplication();
		landingPage.loginApplication("test_board", "P@ssw0rd");
		
        // accessing Energy Data Collection and new energy data collection form
		AccessingDataCollectionForm accessForm = new AccessingDataCollectionForm(driver);
		
        if (!accessForm.accessingEnergyDataCollect()) {
        	return;
        }
	}
	
	@Test
	public void userAdminAccess() throws IOException, InterruptedException {

		LandingPage landingPage = launchApplication();
		landingPage.loginApplication("test_collabAP", "Test1234");
		
        // accessing Energy Data Collection and new energy data collection form
		AccessingDataCollectionForm accessForm = new AccessingDataCollectionForm(driver);
		
        if (!accessForm.accessingEnergyDataCollect()) {
        	return;
        }
	}
//	public static void gasFuelOil() {
//
//		driver.findElement(By.id("elx-tabId-gas_fuel")).click();
//
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("gas_fuel.source_type-0")));
//
//		driver.findElement(By.id("gas_fuel.source_type-0")).click();
//
//		wait.until(ExpectedConditions.elementToBeClickable(
//				By.cssSelector("button[id='gas_fuel.add_renewable_source-gasFuelRenewAddSource']")));
//
//		driver.findElement(By.cssSelector("button[id='gas_fuel.add_renewable_source-gasFuelRenewAddSource']")).click();
//
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.cssSelector("select[id='gas_fuel.renewable_source']")));
//
//		WebElement renewableResources = driver.findElement(By.cssSelector("select[id='gas_fuel.renewable_source']"));
//
//		Select renewables = new Select(renewableResources);
//
//		renewables.selectByIndex(1);
//
//		driver.findElement(By.id("gas_fuel.renewable_quantity")).sendKeys("4000");
//
//		driver.findElement(By.id("btn-save")).click();
//
//		wait.until(ExpectedConditions
//				.elementToBeClickable(By.id("gas_fuel.add_non_renewable_source-gasFuelNonRenewAddSource")));
//
//		driver.findElement(By.id("gas_fuel.add_non_renewable_source-gasFuelNonRenewAddSource")).click();
//
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gas_fuel.non_renewable_source")));
//
//		WebElement nonrenewableRes = driver.findElement(By.name("gas_fuel.non_renewable_source"));
//
//		Select nonRenewable = new Select(nonrenewableRes);
//
//		nonRenewable.selectByIndex(1);
//
//		driver.findElement(By.id("gas_fuel.non_renewable_quantity")).sendKeys("5000");
//
//		driver.findElement(By.id("btn-save")).click();
//	}	
}
