package com.apitestscript.baseclass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.apitestscript.genericutility.DatabaseUtility;
import com.apitestscript.genericutility.FileUtility;
import com.apitestscript.genericutility.JavaUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author CHIDUSD
 */
public class BaseClassAPI {

	public JavaUtility javaUtility = new JavaUtility();
	public FileUtility fileUtility = new FileUtility();
	public DatabaseUtility databaseUtility = new DatabaseUtility();
	public static RequestSpecification requestSpecificationObject;
	public static ResponseSpecification responseSpecificationObject;

	@BeforeSuite
	public void configBeforeSuite() throws Throwable {
		DatabaseUtility.connectToDB();
		System.out.println("============Connect TO DB==========");
		//Request Specific
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setContentType(ContentType.JSON);
		requestSpecBuilder.setBaseUri(fileUtility.getDataFromPropertiesFile("BASEUri"));
		requestSpecificationObject = requestSpecBuilder.build();
		//Response Specific
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectContentType(ContentType.JSON);
		responseSpecificationObject = responseSpecBuilder.build();
	}

	@AfterSuite
	public void configAfterSuite() throws SQLException {
		DatabaseUtility.closeDb();
		System.out.println("==========DisConnectToDB===========");
	}

}
