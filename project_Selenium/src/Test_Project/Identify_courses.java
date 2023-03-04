package Test_Project;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class Identify_courses {
	//Declaring object variables in class level
    public static WebDriver driver;       
    public static String HomeWin;         
    public static readExcelData read;     
    public static switchWindow switchWin; 
    public static WebDriverWait wait;
    public static DriverSetup getDriver;
    
    @BeforeSuite 
    public void setup() throws Exception{

        getDriver = new DriverSetup(); 	//creating object of 'DriverSetup' Class for getting driver
        
        driver = getDriver.getWebDriver(); //getting driver from 'getWebDriver()' method

        driver.manage().window().maximize(); 	//Maximizing the window
        
        read = new readExcelData();    //declaring object for 'readExcelData' class to fetch data from excel sheet
        
        switchWin = new switchWindow(); //creating object for 'switchWindow' class

        driver.get(read.Excel(0)); //navigating to web Application (couresera.org)

        HomeWin = driver.getWindowHandle(); // assigning GUI id of home window to HomeWin
    		
    }
    
    @Test
    public void testCase1() throws Exception {

    	//locating search input box and searching for the course
      WebElement input = driver.findElement(By.xpath("//input[@class='react-autosuggest__input']"));  
      input.sendKeys(read.Excel(1)); 

      WebElement SearchBtn = driver.findElement(By.xpath("//button[@class='nostyle search-button']/div"));
      SearchBtn.click();
      
      By BeginnerXpath = By.xpath("//span[contains(text(),'Beginner')]/preceding-sibling::span[1]/span");
      
      wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      wait.until(ExpectedConditions.visibilityOfElementLocated(BeginnerXpath)); //Waiting explicitly for visiblity of sorting check boxes

      driver.findElement(BeginnerXpath).click();    //Selecting level as 'Beginner'
      	
      //Selecting course language as 'English'
      By languageXpath = By.xpath("//legend[contains(text(),'Language')]/parent::fieldset/parent::div/div/button/span");
      driver.findElement(languageXpath).click();
      
      By EngXpath = By.xpath("//span[contains(text(),'English')]/preceding-sibling::span/span");
      driver.findElement(EngXpath).click();

      driver.findElement(By.xpath("//span[contains(text(),'Apply')]")).click();
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("css-1j8ushu")));

      List<WebElement> list = driver.findElements(By.className("css-1j8ushu")); //Storing web elements of courses in the list
      
      //Iterating only for first two courses
      for (int i = 0; i < 2; i++) { 

          list.get(i).click(); 

          switchWin.switchToCurrentWin(driver); //Switchting to new tab opened
          
          //getting course name form the course page
          String name = driver.findElement(By.cssSelector("[data-e2e='xdp-banner-title']")).getText();  
          
          //getting duration of the course from the course page
          By durationXpath = By.xpath("//*[@id='main']/div/div[2]/div/div/div/div[2]/div/div/div[5]/div[2]/div[1]/span"); 
           
          String duration = driver.findElement(durationXpath).getText();
          
          //getting star rating of the course from the course page
          String rating = driver.findElement(By.xpath("//span[@data-test='number-star-rating']")).getText();
          
          //printing the name, duration, rating for the courses
          System.out.println("\n Course  :" + name + 
        		  			 "\n Duration : " + duration + 
        		             "\n Rating : " + rating.replaceAll("\\s+", " "));
          driver.close(); // closing the current tab
          
          switchWin.switchToHomeWin(driver,HomeWin); //switching to home window after completing the Iteration

      }
    }
    
    @Test
	public void testCase2() throws Exception {
      
      //locating search input box and clearing already present course name and searching for the new course
      WebElement input = driver.findElement(By.xpath("//input[@class='react-autosuggest__input']")); 
      input.sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.BACK_SPACE, read.Excel(2));

      driver.findElement(By.xpath("//button[@class='nostyle search-button']/div")).click(); //clicks the search option
       
      //copying the element which shows the no. of courses available 
      WebElement CountLocator = driver.findElement(By.xpath("//h1[@class='cds-1 css-1inluxh cds-3']/span")); 
      
      By CourseLocator = By.xpath("//div[@class='css-1j8ushu']");
       
      By BeginnerChckBox = By.xpath("//span[contains(text(),'Beginner')]/preceding-sibling::span[1]/span");
      
      wait = new WebDriverWait(driver, Duration.ofSeconds(30));  //Wait until the levels selecting checkboxes visible
      wait.until(ExpectedConditions.visibilityOfElementLocated(BeginnerChckBox));
      
      String[] levels = {"Beginner", "Intermediate", "Advanced", "Mixed"}; //Assigning different levels in a string array
      
      //Iterating by selecting different levels using string array
      for(String level : levels){
    	  
    	  By levelCheckBox = By.xpath("//span[contains(text(),'"+ level +"')]/preceding-sibling::span[1]/span");
    	  
          driver.findElement(levelCheckBox).click(); //locating the level selector checkboxes
          
          //Waiting for visibility of the courses
          wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CourseLocator));
          
          //Storing no of courses available in a string
          String count = CountLocator.getText();
          
          //Printing no of courses available in different levels
		  System.out.println("\n No. of courses for language learning available in "
							+ level
							+ " level :  "
							+ (count.replaceAll("[^0-9]", "")));
		  
          //clicking the checkbox again for unchecking
          driver.findElement(levelCheckBox).click();
      }
      
      
      switchWin.switchToHomeWin(driver, HomeWin); //switching to home window after completing the Iteration

        
    }
      
      @Test
      public void testcase3() throws Exception{
    	
    	//Selecting the enterprise link from the homepage
    	WebElement forEnterpriseLink = driver.findElement(By.xpath("//a[@id='enterprise-link']"));
  		forEnterpriseLink.click();
  		
  		//Hovering over the product tab
  		WebElement productLink = driver.findElement(By.xpath("//*[@id='menu-item-9140']/a"));
		Actions action=new Actions(driver);
		action.moveToElement(productLink).build().perform();
		
		//Clicking 'For Campus' option under the product tab
		WebElement forcampuslink = driver.findElement(By.xpath("//*[@id='menu-item-4901']/a"));
		forcampuslink.click();
  				
		switchWin.switchToCurrentWin(driver); //Switching to the new tab using 'switchWindow class'
		
		Thread.sleep(5000);// Waiting for sometime until the form 'Ready to transform?' appears 
  		
		//Entering the firstname by fetching the data from excel sheet
		WebElement firstName = driver.findElement(By.xpath("//*[@id='FirstName']"));
		firstName.sendKeys(read.Excel(3));
		
		//Entering lastname in lastname input field
		WebElement lastName = driver.findElement(By.xpath("//*[@id='LastName' and @placeholder='Last Name']"));
		lastName.sendKeys(read.Excel(4));
  		
		//Entering job title
		WebElement jobTitle = driver.findElement(By.xpath("//*[@id='Title']"));
		jobTitle.sendKeys(read.Excel(5));
		
		//Declaring invalid email id in the email field
		WebElement workEmail = driver.findElement(By.xpath("//*[@id='Email']"));
		workEmail.sendKeys(read.Excel(6));
		
		//Entering the phone number
		WebElement phoneNo = driver.findElement(By.xpath("//*[@id='Phone']"));
		phoneNo.sendKeys(read.Excel(7));
		
		//Entering the company name 
		WebElement Company = driver.findElement(By.xpath("//*[@id='Company']"));
		Company.sendKeys(read.Excel(8));
		
		//Selecting Institution type from the dropdown list box
		Select select = new Select(driver.findElement(By.xpath("//*[@id='Institution_Type__c']")));
		select.selectByIndex(1);
		
		select = new Select(driver.findElement(By.xpath("//*[@id='Primary_Discipline__c']")));
		select.selectByIndex(1);
		
		//Selecting country
		select = new Select(driver.findElement(By.xpath("//*[@id='Country']")));
		select.selectByIndex(1);
		
		//Selecting state
		select = new Select(driver.findElement(By.xpath("//*[@id='State']")));
		select.selectByIndex(1);
		
		//Clicking the submit button
		driver.findElement(By.xpath("//*[@class='mktoButton']")).click();
		
		//Copying the error message showing for invalid email
		String errorMsg = driver.findElement(By.id("ValidMsgEmail")).getText();
		
		//Printing the Error message
		System.out.println("\n Error Message for Invalid Email : " + errorMsg.replaceAll("\\n", " "));
		
		
      }
      
      @AfterSuite 
      public void closeBrowser(){
    	  
    	  driver.quit(); //closing the browser
    	  
      }

   
}
 
