package com.unosquare;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class FirstTest   {
	@Test
	  public void f() {
		  
			
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get("/users/2");
			
			int statusCode = response.getStatusCode();

			// Assert that correct status code is returned.
			Assert.assertEquals(statusCode,200);
			Reporter.log("Sucess 200 validation");
	  }
	@Test
	  public void TestSecondTest() {
		  
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get("/users/-2");
			
			int statusCode = response.statusCode();

			// Assert that this user id "-2" is not found.
			Assert.assertEquals(statusCode,404);
			Reporter.log("Sucess 404 validation");
	  }
	@Test
	public void NasaTest() {
		RestAssured.baseURI = "https://api.nasa.gov/mars-photos/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		int pageIndex = 3;
		Response response = httpRequest.get("/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY&page=" + pageIndex);
		
		int statusCode = response.statusCode();

		// Assert status code returns OK.
		Assert.assertEquals(statusCode,200);
		Reporter.log("API Nasa, mars pictures Sucess 200 validation, first image url http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG");
	}
	@Test
	public void NasaTest_WrongApiKey() {
		RestAssured.baseURI = "https://api.nasa.gov/mars-photos/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		String invalidApiKey = "thisIsWrongApiKey";
		Response response = httpRequest.get("/rovers/curiosity/photos?sol=1000&api_key=" + invalidApiKey);
		
		int statusCode = response.statusCode();

		// Assert forbidden status code.
		Assert.assertEquals(statusCode,403);
		Reporter.log("API Nasa, mars pictures Sucess 403 validation with invalid API key");
	}
@BeforeMethod
  public void beforeMethod() {
	  RestAssured.baseURI = "https://reqres.in/api/";
  }

}
