package com.apitestscript.genericutility;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

/**
 * @author CHIDUSD
 */
public class JsonUtiliy {
	FileUtility fileUtility = new FileUtility();
	
	/**
	 * @param response
	 * @param jsonXPath
	 * @return
	 */
	public String getDataOnJsonPath(Response response, String jsonXPath) {
		List<Object> list = JsonPath.read(response.asString(), jsonXPath);
		return list.get(0).toString();
	}
	
	/**
	 * @param response
	 * @param xmlXPath
	 * @return
	 */
	public String getDataOnxmlXPath(Response response, String xmlXPath) {
		return response.xmlPath().get(xmlXPath);
	}
	
	/**
	 * @return
	 * @throws Throwable
	 */
	public String getAcessToken() throws Throwable {
		 Response resp =  given()
                .formParam("client_id", fileUtility.getDataFromPropertiesFile("ClinetID"))
                .formParam("client_secret", fileUtility.getDataFromPropertiesFile("ClinetSecret"))
                .formParam("grant_type", "client_credentials")			                 
	           .when()
                .post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
                resp.then()	        
                     .log().all();
          //capture data from the response      
          String token = resp.jsonPath().get("access_token");
		return token;
	}	
}
