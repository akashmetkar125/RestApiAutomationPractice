package restAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePojo;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;


public class AddPlaceWithSpec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		
		
RequestSpecification reqSpec =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick")
		.setContentType(ContentType.JSON).build();

ResponseSpecification respSpec =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

  RequestSpecification request = given().log().all().spec(reqSpec).body(p);
  Response response = request.log().all().when().post("/maps/api/place/add/json")
		  .then().log().all().spec(respSpec).extract().response();

  JsonPath js = new JsonPath(response.asString());
  System.out.println(js);
  String Id =js.get("id");
  System.out.println(Id);
	}

}
