import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PayLoads.AddPlace;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddBook {


	@Test(dataProvider="testdata")

	public void addBook1(String isbn, String aisle) {

		RestAssured.baseURI="https://rahulshettyacademy.com";

		//add book
		String addBookResponse = given().header("Content-Type", "application/json")
				.body(AddPlace.addB(isbn, aisle))
				.when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();

		JsonPath js3 = new JsonPath(addBookResponse);
		String ID = js3.getString("ID");

		// delete book

		given().log().all().header("Content-Type", "application/json")
		.body(AddPlace.delb(ID))
		.when().delete("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200);

	}

	@DataProvider(name="testdata")
	
	public Object[][] tData() {
		return new Object[][] {{"199", "222"}, {"bbb", "234"}, {"ccc", "234"}};
	}
	
	
	
	
}
