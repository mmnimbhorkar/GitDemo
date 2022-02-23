import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.AddL;
import pojo.AddP;
import pojo.respAdd;
public class AddPThroughPojo {

	@Test
	public void addPlace() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		AddP p =new AddP();
		p.setAccuracy(50);
		p.setAddress("wagholi");
		p.setLanguage("French-IN");
		p.setName("Frontline house");
		p.setWebsite("http://google.com");
		p.setPhone_number("(+91) 983 893 3937");
		ArrayList<String> l1 = new ArrayList<String>();
		l1.add("shoe park");
		l1.add("shop");
		p.setTypes(l1);
		
		AddL l = new AddL();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		respAdd resp = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.log().all().body(p)
		.when().post("maps/api/place/add/json")
		.then().extract().response().as(respAdd.class);
		
		//JsonPath jd = new JsonPath(s);
		//String need = jd.getString("scope");
		System.out.println("*********************" + resp.getScope());
		
		//System.out.println(res.getScope() + "-------------------------");
		
		
		System.out.println("newly added place is: " + p.getAddress()) ;
		
	}
	
	
}
