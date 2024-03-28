package assessment.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DestinationPage {

	
		WebDriver driver;
		
		public DestinationPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
		 public boolean isNewTabOpened() {
//		        return driver.getWindowHandles().size() > 1 && driver.getCurrentUrl().contains("vacation");
			    if(driver.getWindowHandles().size() > 1) {
			    	return true;
			    }
			    else {
			    	return false;
			    }
		 }
		 
		 public boolean checkIfURLHasVacation() {
			boolean vacationInURL = driver.getCurrentUrl().contains("vacation");
			return vacationInURL;
		 }
		 
		 public void navigateToHomePage() {
			 if(driver.getWindowHandles().size() > 1) {
				 driver.getWindowHandles().iterator().next();
			    }
			 else{
				 driver.navigate().back();
			 }
		 }
		 

	

}
