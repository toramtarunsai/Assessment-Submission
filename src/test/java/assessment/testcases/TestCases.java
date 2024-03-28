package assessment.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestCases extends BaseTest{

	@Test
	public void checkIfTitleDisplayed() throws IOException {
		
		
		String actualTitle = homepage.getTitle();
		Assert.assertEquals("Welcome to the Simple Travel Agency!", actualTitle);
		
		
	}
	
	@Test
	public void checkNewTabWithVacationURL() throws IOException {

		homepage.destinationOfTheWeekLink();
		try {
			Boolean newTab = destinationpage.isNewTabOpened();
			Assert.assertTrue(newTab);
			Boolean vacationInURL = destinationpage.checkIfURLHasVacation();
			Assert.assertTrue(vacationInURL);
		}
		catch(AssertionError e){
			 System.out.println("Assertion failed: " + e.getMessage());
		}
		finally {
			destinationpage.navigateToHomePage();
		}
		
	}
	
	@Test(dataProvider = "DepartureDestinationData")
	public void checkPurchaseTicket(String departureCity, String destinationCity) throws IOException {
		purchasepage.selectDepartureAndDestinationCities(departureCity,destinationCity);
		purchasepage.findFlights();
		String lowestPrice = purchasepage.chooseFlightWithLowestPrice();
		purchasepage.clickOnChooseFlight(lowestPrice);
		boolean totalCostFormat = purchasepage.checkTotalCostFormat();
		Assert.assertTrue(totalCostFormat);
		purchasepage.clickPurchaseFlightButton();
		String actualconfirmationText = purchasepage.purchaseConfirmation();
		Assert.assertEquals(actualconfirmationText, "Thank you for your purchase today!");
		purchasepage.getId();
	}
	
	 @DataProvider(name = "DepartureDestinationData")
	    public Object[][] getDepartureDestinationData() {
	        
	        return new Object[][] {
	            {"Mexico City", "London"},
	            {"Boston", "New York"},
	            
	        };
	}
}
