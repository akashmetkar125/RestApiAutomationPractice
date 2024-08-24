package restAPI;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;

public class AddPlaceAPIAutomation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
	String response =	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().log().all().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString(); 
	
	JsonPath js = new JsonPath(response);
	String PlaceId = js.getString("place_id");
	
	//Update Place
	String newAddress = "Shahu Maharaj Nagar";
	
	given().log().all().queryParam("key", "qaclick123", "place_id", PlaceId).header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+PlaceId+"\",\r\n"
			+ "\"address\":\""+newAddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}")
	.when().log().all().put("maps/api/place/update/json")
	.then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Get Place
	
	String getResponse =given().log().all().queryParam("place_id", PlaceId).queryParam("key", "qaclick123").header("Content-Type","application/json")
	.when().log().all().get("maps/api/place/get/json")
	.then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1 = new JsonPath(getResponse);
	String actAddress =js1.get("address");
	System.out.println(actAddress);
	Assert.assertEquals(newAddress, actAddress);
		
	}

	
	}


