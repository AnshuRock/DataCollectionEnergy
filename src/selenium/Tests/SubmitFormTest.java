package selenium.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.TestComponents.BaseTest;
import selenium.pageobjects.AccessingAnnualSummaryForm;
import selenium.pageobjects.AccessingDataCollectionForm;
import selenium.pageobjects.AnnualSummaryForm;
import selenium.pageobjects.DataCollectionForm;
import selenium.pageobjects.LandingPage;
import selenium.pageobjects.Validation;


public class SubmitFormTest extends BaseTest {

	@Test(dataProvider= "getData")
	public void submitForm(HashMap<String, String> input) throws IOException, InterruptedException {

		LandingPage landingPage = launchApplication();
		landingPage.loginApplication(input.get("userName"), input.get("password"));
		
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
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\selenium\\data\\Users.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
	}
	
	@Test(dataProvider= "getData", groups= {"annual Summary"})
	public void submitASForm(HashMap<String, String> input) throws IOException, InterruptedException {

		LandingPage landingPage = launchApplication();
		landingPage.loginApplication(input.get("userName"), input.get("password"));
		
        // accessing Energy Data Collection and new energy data collection form
		AccessingAnnualSummaryForm accessForm = new AccessingAnnualSummaryForm(driver);
		
        if (!accessForm.accessingEnergyAS()) {
        	return;
        }
		
        AnnualSummaryForm form = new AnnualSummaryForm(driver);
        
        // test if location is empty
        if (!form.selectScope()) {
			System.out.println("Collabortor is not assigned to this topic");
			return;
		}
        
        form.writingSummary();
		
		// submitting the form
		form.submittingForm();
		
		form.verifyLatestAS();
		
	}
	
	/*@DataProvider
	public Object[][] getData() {
           return new Object[][] {{"test_collab", "P@ssw0rd"}, {"test_cso", "P@ssw0rd"},
               {"test.user", "P@ssw0rd"}, {"test.board", "P@ssw0rd"}};
	} */
	
	/*
	 HashMap<String, String> map = new HashMap<>();
		map.put("userName", "test_cso");
		map.put("password", "P@ssw0rd");
		
		HashMap<String, String> map1 = new HashMap<>();
		map.put("userName", "test.board");
		map.put("password", "P@ssw0rd");
	 */
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
