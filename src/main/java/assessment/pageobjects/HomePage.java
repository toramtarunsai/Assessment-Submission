package assessment.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName="h1")
	WebElement homePageHeader;
	
	@FindBy(linkText="destination of the week! The Beach!")
	WebElement destinationWeekLink;
	
	public String getTitle() {
		String Title =homePageHeader.getText();
		return Title;
	}
	
	public void destinationOfTheWeekLink() {
		destinationWeekLink.click();
	}

}
