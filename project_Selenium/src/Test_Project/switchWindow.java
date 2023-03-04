package Test_Project;

import org.openqa.selenium.WebDriver;

public class switchWindow {

	public void switchToCurrentWin(WebDriver driver) {
		
		//gets GUI id of different tabs in the window and iterates and switch to the last tab
		for (String winHandle : driver.getWindowHandles()) { 
			driver.switchTo().window(winHandle);
		}

	}
	
	//Accepts driver and home window handle and switch to home window
	public void switchToHomeWin(WebDriver driver, String HomeWin) {

		driver.switchTo().window(HomeWin);

	}

}
