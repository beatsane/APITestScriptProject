package com.apitestscript.projecttest;

import com.apitestscript.baseclass.BaseClassAPI;
import com.apitestscript.endpoints.IEndPoint;
import com.apitestscript.genericutility.DatabaseUtility;
import com.apitestscript.pojoclass.ProjectPojo;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.time.Duration;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest extends BaseClassAPI {

	ProjectPojo projectPojo;
	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void addSingleProjectWithCreatedTest() throws Throwable {

		String expectedMessage = "Successfully Added";
		String projectName = "AlfaLaval_" + javaUtility.getRandomNumber();
		projectPojo = new ProjectPojo(projectName, "Created", "ChiduSD", 0);

		Response response = given()
							.spec(requestSpecificationObject)
							.body(projectPojo)
							.when()
							.post(IEndPoint.ADD_PROJ);
					response.then()
							.assertThat()
							.statusCode(201)
							.spec(responseSpecificationObject)
							.log().all();
		String actualMessage = response.jsonPath().get("msg");
		String projectId = response.jsonPath().get("projectId");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(expectedMessage, actualMessage);
		
		DatabaseUtility.connectToDB();
		boolean flag  = databaseUtility.executeQueryVerifyAndGetData("select * from project", 4, projectName);
	    Assert.assertTrue(flag,"Project in DB is not verified"); 
		
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    driver.get("http://49.249.28.218:8091/");
	   
	    driver.findElement(By.id("username")).sendKeys("rmgyantra");
	    WebElement passwordField = driver.findElement(By.id("inputPassword"));
	    passwordField.sendKeys("rmgy@9999");
	    passwordField.submit();
	    driver.findElement(By.linkText("Projects")).click();
	    WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']"));
	    searchBox.sendKeys(projectId);
	    searchBox.sendKeys(Keys.ENTER); 
	    WebElement peojectStatus = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[5]"));
	    String projectStatus = peojectStatus.getText();
	    Assert.assertTrue(projectStatus.equals(status));
	    driver.quit();
	}
	
	@Test
	public void addProjectWitONGoingStatusTest() throws Throwable {
		
		String expectedMessage = "Successfully Added";
		String projectName = "AlfaLaval_" + javaUtility.getRandomNumber();
		projectPojo = new ProjectPojo(projectName, "OnGoing", "ChiduSD", 0);
		
					Response response = given()
							.spec(requestSpecificationObject)
							.body(projectPojo)
							.when()
							.post(IEndPoint.ADD_PROJ);
					response.then()
							.assertThat()
							.statusCode(201)
							.spec(responseSpecificationObject)
							.log().all();
					
		String actualMessage = response.jsonPath().get("msg");
		String projectId = response.jsonPath().get("projectId");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(expectedMessage, actualMessage);
		DatabaseUtility.connectToDB();
		boolean flag  = databaseUtility.executeQueryVerifyAndGetData("select * from project", 4, projectName);
	    Assert.assertTrue(flag,"Project in DB is not verified");
	    
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    driver.get("http://49.249.28.218:8091/");
	   
	    driver.findElement(By.id("username")).sendKeys("rmgyantra");
	    WebElement passwordField = driver.findElement(By.id("inputPassword"));
	    passwordField.sendKeys("rmgy@9999");
	    passwordField.submit();
	    driver.findElement(By.linkText("Projects")).click();
	    WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']"));
	    searchBox.sendKeys(projectId);
	    searchBox.sendKeys(Keys.ENTER); 
	    WebElement peojectStatus = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[5]"));
	    String projectStatus = peojectStatus.getText();
	    Assert.assertTrue(projectStatus.equals(status));
	    driver.quit();
	}
	
	@Test
	public void addProjectWithCompletedStatsTest() throws Throwable {
		String expectedMessage = "Successfully Added";
		String projectName = "AlfaLaval_" + javaUtility.getRandomNumber();
		projectPojo = new ProjectPojo(projectName, "Completed", "ChiduSD", 0);
		
					Response response = given()
							.spec(requestSpecificationObject)
							.body(projectPojo)
							.when()
							.post(IEndPoint.ADD_PROJ);
					response.then()
							.assertThat()
							.statusCode(201)
							.assertThat().time(Matchers.lessThan(7000L))
							.spec(responseSpecificationObject)
							.log().all();
					
		String actualMessage = response.jsonPath().get("msg");
		String projectId = response.jsonPath().get("projectId");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(expectedMessage, actualMessage);
		
		DatabaseUtility.connectToDB();
		boolean flag  = databaseUtility.executeQueryVerifyAndGetData("select * from project", 4, projectName);
	    Assert.assertTrue(flag,"Project in DB is not verified");
	    
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    driver.get("http://49.249.28.218:8091/");
	   
	    driver.findElement(By.id("username")).sendKeys("rmgyantra");
	    WebElement passwordField = driver.findElement(By.id("inputPassword"));
	    passwordField.sendKeys("rmgy@9999");
	    passwordField.submit();
	    driver.findElement(By.linkText("Projects")).click();
	    WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']"));
	    searchBox.sendKeys(projectId);
	    searchBox.sendKeys(Keys.ENTER); 
	    WebElement peojectStatus = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[5]"));
	    String projectStatus = peojectStatus.getText();
	    Assert.assertTrue(projectStatus.equals(status));
	    driver.quit();
	}
}
