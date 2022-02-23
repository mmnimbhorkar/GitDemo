import static io.restassured.RestAssured.given;

import PayLoads.AddPlace;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.GetDetails;

public class Basics extends AddPlace{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//given - all input details
		//when - submit the api - resource and http method is defined here
		//then - validate the API response		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("Key", "qaclick123")
		.addHeader("Content-Type", "application/json").build();
		
		// add place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().spec(req).body(AddPlace.addPlacePayload()) //log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();	
		
		System.out.println("-------------------this is response log after adding place------------");
		
		JsonPath js = new JsonPath(response);
		String place = js.getString("place_id");
		
		System.out.println(place);
		
		// update place
		
		given().log().all().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
		.body(AddPlace.updatePlace(place))
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200);
		
		// get place
		GetDetails gd = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().as(GetDetails.class);
		
//		JsonPath js1 = new JsonPath(getDetails);
//		String add1 = js1.getString("address");
//		System.out.println("************"+add1);
		System.out.println("-------------pojo success" + gd.getAddress() + "pojo success----------------------");
		
		//Assert.assertEquals(add1, getAddress(), "Not matched");
	
	
		}
	}


