package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import static io.restassured.RestAssured.*;


public class ApiTest {

	@Test(priority=1,description="get")
	public void availabeStatus() {

		Response response = given().given().queryParam("status", "available").when()
				.get("https://petstore.swagger.io/v2/pet/findByStatus");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String name = jsonPathEvaluator.get("name[0]");
		System.out.println(name);
		Assert.assertEquals(name, "Rex");

	}

	@Test(priority=2,description="post")
	public void postNewPet()  {

		String body="{\"id\":104,\"category\":{\"id\":1010,\"name\":\"string\"},\"name\":\"cow\",\"photoUrls\":[\"www.google.com\"],\"tags\":[{\"id\":1010,\"name\":\"gir\"}],\"status\":\"available\"}";
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		String Resp=given().
				body(body).
				when().
				post("/pet").
				then().
				extract().
				response().asString();

		System.out.println("Response is\t"+Resp);
		Response response2 = given().given().when().get("https://petstore.swagger.io/v2/pet/104");
		JsonPath jsonPathEvaluator = response2.jsonPath();
		String name = jsonPathEvaluator.get("name");
		System.out.println(name);
		Assert.assertEquals(name, "cow"); 

	}

	@Test(priority=3,description="Update")
	public void UpdatePutRequest()
	{
		String PutBody="{\"id\":104,\"category\":{\"id\":1010,\"name\":\"string\"},\"name\":\"cow\",\"photoUrls\":[\"www.google.com\"],\"tags\":[{\"id\":1010,\"name\":\"gir\"}],\"status\":\"sold\"}";
		
		
		RestAssured.baseURI="https://petstore.swagger.io/v2/";

				String Resp=given().
				header("Content-Type","application/json; charset=utf-8").
				body(PutBody).
				when().
				put("/pet").
				then().assertThat(). 
				statusCode(200).
				body("status",equalTo("sold")).
				extract().
				response().asString();
				
				Response response2 = given().given().when().get("https://petstore.swagger.io/v2/pet/104");
				JsonPath jsonPathEvaluator = response2.jsonPath();
				String status = jsonPathEvaluator.get("status");
				System.out.println(status);
				Assert.assertEquals(status, "sold"); 

		System.out.println("Response is\t"+Resp);
	}
	
	

}
