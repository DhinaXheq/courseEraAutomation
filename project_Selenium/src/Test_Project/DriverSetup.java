package Test_Project;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	
	public static WebDriver driver;
	
	public WebDriver getWebDriver(){
		
    	System.out.println("Select a browser to execute : \n"
    			+ "1.Chrome \n"
    			+ "2.Edge \n"
    			+ "3.Firefox \n");
    	
    	//Asks for user to select the brower
    	Scanner sc = new Scanner(System.in);
    	
    	String browser = sc.nextLine();
    	sc.close();
    	
    	File file;
    	
    	String absPath;
    	
    	if(browser.equalsIgnoreCase("chrome")){
    		
    		file = new File("chromedriver.exe");
    		absPath = file.getAbsolutePath();
        	System.setProperty("webdriver.chrome.driver", absPath);
            driver = new ChromeDriver();
    		
    	}else if(browser.equalsIgnoreCase("firefox")){
    		
    		file = new File("geckodriver.exe");
        	absPath = file.getAbsolutePath();
        	System.setProperty("webdriver.gecko.driver", absPath);
            driver = new FirefoxDriver();
    		
    	}else if(browser.equalsIgnoreCase("edge")){
    		
    		file = new File("msedgedriver.exe");
        	absPath = file.getAbsolutePath();
        	System.setProperty("webdriver.edge.driver", absPath);
            driver = new EdgeDriver();
    		
    	}
    	
		
		return driver; //returns the WebDriver
		
	}

}
