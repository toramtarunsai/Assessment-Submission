package assessment.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {
	
	WebDriver driver;
	ArrayList<Double> pricedoubleList = new ArrayList<>();

	
	public PurchasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="fromPort")
	WebElement departure;
	
	@FindBy(name="toPort")
	WebElement destination;
	
	@FindBy(css="input[value='Find Flights']")
	WebElement findFlightsButton;
	
	@FindBy(css="tbody tr td:nth-child(7)")
	List<WebElement> priceList;
	
	@FindBy(xpath="//div[@class='container']/p[5]")
	WebElement totalCost;
	
	@FindBy(css="input[value='Purchase Flight']")
	WebElement purchaseFlightButton;
	
	@FindBy(tagName="h1")
	WebElement purchaseConfirmationHeader;
	
	@FindBy(xpath="//tbody/tr[1]/td[2]")
	WebElement id;
	
	public void selectDepartureAndDestinationCities(String depatureCity, String destinationCity) {
		Select departureDropdown = new Select(departure);
		departureDropdown.selectByVisibleText(depatureCity);
		
		Select destinationDropdown = new Select(destination);
		destinationDropdown.selectByVisibleText(destinationCity);
	}
	
	public void findFlights() {
		findFlightsButton.click();
	}
	
	public String chooseFlightWithLowestPrice() {
		for(int i=0;i<priceList.size();i++) {
			String priceText1 = priceList.get(i).getText();
			String priceText2 = priceText1.substring(1);
			double price = Double.parseDouble(priceText2);
			pricedoubleList.add(price);
		}
		
		Collections.sort(pricedoubleList);
		double leastPrice = pricedoubleList.get(0);
		return Double.toString(leastPrice);
		
		
	}
	
	public void clickOnChooseFlight(String value) {
		
		String dynamicValue = "$"+value;
		String xpathWithDynamicValue = "//td[text()='"+dynamicValue+"']/parent::tr/td[1]/input";
		driver.findElement(By.xpath(xpathWithDynamicValue)).click();
		
	}

	public boolean checkTotalCostFormat() {
		 String totalCostText = totalCost.getText();
		 boolean isValidFormat = totalCostText.matches("Total Cost: \\d{3}\\.\\d{2}");
		 return isValidFormat;
	}
	
	public void clickPurchaseFlightButton() {
		purchaseFlightButton.click();
	}
	
	public String purchaseConfirmation() {
		String purchaseConfirmationText = purchaseConfirmationHeader.getText();
		return purchaseConfirmationText;
	}
	
	public void getId() {
		System.out.println(id.getText());
	}
}
