package com.apitestscript.projecttest;

import com.apitestscript.baseclass.BaseClassAPI;
import com.apitestscript.endpoints.IEndPoint;
import com.apitestscript.genericutility.DatabaseUtility;
import com.apitestscript.pojoclass.ProjectPojo;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest extends BaseClassAPI {

	ProjectPojo projectPojo;

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
							.assertThat().time(Matchers.lessThan(5000L))
							.spec(responseSpecificationObject)
							.log().all();
		String actualMessage = response.jsonPath().get("msg");
		Assert.assertEquals(expectedMessage, actualMessage);
		DatabaseUtility.connectToDB();
		boolean flag  = databaseUtility.executeQueryVerifyAndGetData("select * from project", 4, projectName);
	    Assert.assertTrue(flag,"Project in DB is not verified");
	}
	
	
}
