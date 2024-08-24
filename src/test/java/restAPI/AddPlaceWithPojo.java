package restAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.AddPlacePojo;
import pojo.Location;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceWithPojo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlacePojo p = new AddPlacePojo();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String> list = new ArrayList<>();
		list.add("shoe park");
		list.add("shop");
		
		p.setTypes(list);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		
		
		
	String resp =	given().log().all().queryParam("key", "qaclick123")
		.body(p).when().log().all().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK")).extract().response().asString();
	
	JsonPath js = new JsonPath(resp);
	String Id =js.get("id");
	System.out.println("Id : " +Id);
	
	}

}
