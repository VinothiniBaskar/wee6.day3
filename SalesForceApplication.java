package salesforce.app;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceApplication {

	public static void main(String[] args) throws InterruptedException {
		// Set up the driver
		WebDriverManager.chromedriver().setup();

		// disable the notifications in the browser
		// create an object for the class ChromeOptions
		ChromeOptions options = new ChromeOptions();

		// use that object and use the method(addArguments)
		// In that method pass the arguments("disable-notification")
		options.addArguments("disable-notification");

		// pass the object in the constructor of ChromeDriver class
		ChromeDriver driver = new ChromeDriver(options);

		// Lanuch the url
		driver.get("https://login.salesforce.com");
		// Maximize the window
		driver.manage().window().maximize();
		// Give the implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// enter the username and password
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password#123");

		// Click the login button
		driver.findElement(By.id("Login")).click();

		// Click the App Launcher
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

		// Click the view all button
		driver.findElement(By.xpath("//button[text()='View All']")).click();

		// click the Sales
		driver.findElement(By.xpath("//p[text()='Sales']")).click();

		// Click on Opportunity tab
		WebElement opportunities = driver.findElement(
				By.xpath("//a[@class='slds-context-bar__label-action dndItem']//span[text()='Opportunities']"));
		driver.executeScript("arguments[0].click()", opportunities);

		// Click the new button
		driver.findElement(By.xpath("//div[@title='New']")).click();

		// Enter Opportunity name as 'Salesforce Automation by Vinothini
		WebElement opportunityName = driver.findElement(By.xpath("//input[@name='Name']"));
		// send the Opportunity name by uisng sendKeys method
		opportunityName.sendKeys("Selaesforce Automation By Vinothini");
		// Get the text
		String text = opportunityName.getAttribute("value");
		// Print the text
		System.out.println("The Opportunity name as :" + text);

		// Choose close date as Today
		WebElement todayDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		todayDate.click();
		todayDate.sendKeys("10/30/2022");

		// Select 'Stage' as Need Analysis
		driver.findElement(By.xpath("//button[@data-value='--None--']")).click();
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Needs Analysis']")).click();
       
       

		// click Save
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

		// Verify the opportunityName
		if (text.contains("Selaesforce Automation ")) {
			System.out.println("opportunity Name verified successfully");
		} else {
			System.out.println("opportunity Name  not verified ");

		}

	}

}
