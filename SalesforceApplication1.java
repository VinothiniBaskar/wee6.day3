package salesforce.app;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceApplication1 {

	public static void main(String[] args) throws InterruptedException {
		// Set up the driver
		WebDriverManager.chromedriver().setup();

		// dissable the notifications in the browser
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
		opportunityName.click();

		// Choose close date as Today
		WebElement todayDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		todayDate.click();
		todayDate.sendKeys("11/01/2022");

		// Select 'Stage' as Need Analysis
		WebElement none = driver.findElement(By.xpath("//button[@data-value='--None--']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(none).click().perform();

		// click Save
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

		driver.findElement(By.xpath("//a[text()='Opportunity Name']")).click();

		String errorMsg = driver
				.findElement(By.xpath("//div[@class='slds-form-element__control slds-grow']/following-sibling::div[1]"))
				.getText();
		System.out.println("The  error message for opportunityName and Stage  is : " + errorMsg);

		// Verify the opportunityName and Stage
		if (errorMsg.contains("Complete this field.")) {
			System.out.println("Alert error message  for opportunityName and Stage is verified successfully");

		} else {
			System.out.println("Alert error message  opportunityName and Stage  is not verified successfully");

		}

	}

}
