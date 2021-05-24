package com.e2e.innosabi;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class E2E_protoType {
	private WebDriver driver;
	WebDriverWait wait;

	@BeforeSuite
	public void initDriver() throws Exception {
		System.out.println("You are testing in firefox");
		System.setProperty("webdriver.gecko.driver","resources/geckodriver.exe");
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 50);
	}

	@BeforeTest
	public void printTestsGoingToPerform() {
		System.out.println("/******************************************************************");
		System.out.println("/* We will perform below tests sequentially:                      */");
		System.out.println("/*   1. Check if URL is openened Correctly                        */");
		System.out.println("/*   2. Enter User name and navigate to password page        	  */");
		System.out.println("/*   3. Enter Password and check if it is navigated to home page  */");
		System.out.println("/*   4. Search and Click Innovation Challenge                     */");
		System.out.println("/*   5. Submit New idea and verfiy                                */");
		System.out.println("/*   6. Add comment to another Idea and verify                    */");
		System.out.println("/* Tech Stack Used:                                               */");
		System.out.println("/*    1. Java                                                     */");
		System.out.println("/*    2. Maven                                                    */");
		System.out.println("/*    3. Selenium web drivers                                     */");
		System.out.println("/*    4. TestNg                                                   */");
		System.out.println("/******************************************************************/");
		waitForOneSec(3);
	}

	@AfterMethod
	public void addNewLine() {
		System.out.println("-----------------------");
	}

	@Test(priority=0)
	public void verifyIfUrlIsOpened() {
		System.out.println("Opening E2E test URL and verifying Test started");
		String urlToOpen = "https://e2e.innosabi.com/";
		driver.get(urlToOpen);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		String actualUrl = driver.getCurrentUrl();
		assertEquals(actualUrl, urlToOpen);
		System.out.println("Opening E2E test URL and verifying Test Completed");
	}

	@Test(priority=1)
	public void verifyLoginPageTitle() {
		System.out.println("Verifying login Page Title Test started");
		String expectedTitle = "innosabi spark – agile innovation";
		String actual = driver.getTitle();
		assertEquals(actual, expectedTitle);
		System.out.println("Verifying login Page Title Test Completed");
	}

	@Test(priority=2)
	public void verifyIfNavigatedToPasswordPage() {
		System.out.println("Verifying if navigated to password Page Test started");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("bindu.mashal5@gmail.com");
		driver.findElement(By.xpath("//fe-button[@data-test-id='next-btn']/button")).click();
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		String expectedUrl = "https://e2e.innosabi.com/signin";

		String actual = driver.getCurrentUrl();
		assertEquals(actual, expectedUrl);
		System.out.println("Verifying if navigated to password Page Test Completed");
	}

	@Test(priority=3)
	public void verifyIfNavigatedToHomePage() {
		System.out.println("Verifying if navigated to Home Page after authentication Test started");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("ASWrjhAsuL5mVun@");
		driver.findElement(By.xpath("//fe-button[@data-test-id='login-btn']/button")).click();
		waitForOneSec(1);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		String expectedUrl = "https://e2e.innosabi.com/";
		String actual = driver.getCurrentUrl();
		assertEquals(actual, expectedUrl);
		System.out.println("Verifying if navigated to Home Page after authentication Test Completed");
	}

	@Test(priority=4)
	public void findAndClickInnovationChallenge() {
		System.out.println("Find & select Innovation Challenge Test started");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		while(true) {
			try {
				WebElement ele = driver.findElement(By.partialLinkText("Innovation Challenge"));
				break;
			}catch(Exception e) {
				System.out.println("Didn't find Innovation Challenge");
				js.executeScript("window.scrollBy(0,500)");
				wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
			}
		}

		waitForOneSec(5);
		WebElement ele = driver.findElement(By.xpath("//a[@class='card-topic']"));
		Actions act= new Actions(driver);
		act.moveToElement(ele).perform();
		ele.click();
		String expectedUrl = "https://e2e.innosabi.com/topic/detail/default/1/participate/1/suggest";
		waitUntilExpectedUrlReached(expectedUrl);
		String actual = driver.getCurrentUrl();
		assertEquals(actual, expectedUrl);
		System.out.println("Find & select Innovation Challenge starte Test Completed");
	}

	@Test(priority=5)
	public void addNewIdeaAndVerify() {
		System.out.println("Add Idea & Verfiy Test Started");
		String randomString = UUID.randomUUID().toString();

		System.out.println("Inserting Idea with title: \"this is my idea with ID:"+randomString+"\"");
		driver.findElement(By.xpath("//button[@aria-label='Opens dialog to submit idea']")).click();
		waitUntilExpectedUrlReached("https://e2e.innosabi.com/topic/detail/default/1/participate/1/suggestion/create");
		waitForOneSec(5);
		driver.findElement(By.xpath("//input[@formcontrolname='title']")).sendKeys("this is my idea with ID:"+randomString);
		driver.findElement(By.xpath("//textarea[@rows='2']")).sendKeys("this is my idea description");
		driver.findElement(By.xpath("//button[@aria-label='Submit idea']")).click();
		waitUntilExpectedUrlReached("https://e2e.innosabi.com/topic/detail/default/1/participate/1/suggest");

		boolean foundAddedComment = false;
		try {
			driver.findElements(By.partialLinkText("this is my idea with ID:"+ randomString));

			System.out.println("Found newly added Idea");
			foundAddedComment = true;
		}catch(Exception e) {
		}
		assertTrue(foundAddedComment);
		System.out.println("Add Idea & Verfiy Test Completed");
	}

	@Test(priority=6)
	public void addCommentAndVerify() {
		System.out.println("Add Comment & Verfiy Test Started");
		String randomString = UUID.randomUUID().toString();
		List<WebElement> commentBoxes = driver.findElements(By.xpath("//textarea[@rows='1']"));
		while(commentBoxes.size()<2) {
			waitForOneSec(2);
			commentBoxes = driver.findElements(By.xpath("//textarea[@rows='1']"));
		}
		System.out.println("adding comment: "+"\"this is my comment with unique ID:"+ randomString+"\"");
		commentBoxes.get(3).sendKeys("this is my comment with unique ID:"+ randomString);
		waitForOneSec(5);
		driver.findElement(By.xpath("//button[@class='btn-send ng-star-inserted']")).click();
		boolean foundAddedComment = false;
		try {
			driver.findElements(By.partialLinkText("this is my comment with unique ID:"+ randomString));
			System.out.println("Found newly added comment");
			foundAddedComment = true;
		}catch(Exception e) {
		}
		assertTrue(foundAddedComment);
		System.out.println("Add Comment & Verfiy Test Completed");
	}

	private void waitUntilExpectedUrlReached(String expectedURL) {
		if(!driver.getCurrentUrl().equals(expectedURL)) {
			waitForOneSec(5);
			waitUntilExpectedUrlReached(expectedURL);
		}
	}

	private void waitForOneSec(int numberOfIteration) {
		try {
			for(int i=0;i<numberOfIteration; i++)
				Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}
}
