import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;


public class JiraTest {
	
	@Test
	public void jiraAutomation() {
		
		RestAssured.baseURI= "http://localhost:8081";
		SessionFilter session = new SessionFilter();
		// create session id
		given().header("Content-Type", "application/json").body("{ \r\n"
				+ "\"username\": \"mmnimbhorkar\",\r\n"
				+ "\"password\": \"Abhrid46$\"\r\n"
				+ "}").filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
//		JsonPath js4 = new JsonPath(session);
//		String name = js4.getString("session.name");
//		String sessionid = js4.getString("session.value");
//		String header1 = name+"="+sessionid; // session id concatenation to create header for further requests
		
		// add comment to jira issue
		given().log().all().pathParam("id", 10103).header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \"this is second comment addition to defect through automation\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("/rest/api/2/issue/{id}/comment")
		.then().log().all().assertThat().statusCode(201);
		
		// add attachment
//		given().header("X-Atlassian-Token", "no-check").pathParam("id", 10103).filter(session).header("Content-Type", "multipart/form-data")
//		.multiPart("file", new File("jira.txt"))		
//		.when().post("/rest/api/2/issue/{id}/attachments")
//		.then().log().all().assertThat().statusCode(200);
				
		// get issue details
		given().pathParam("id", 10103).filter(session).queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{id}")
		.then().log().all().assertThat().statusCode(200);
	
	
	
	}
	
}
