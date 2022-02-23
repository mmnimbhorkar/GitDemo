import org.testng.Assert;

import PayLoads.AddPlace;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js2 = new JsonPath(AddPlace.coursePrice());
		int noOfCourses = js2.getInt("courses.size()");
		System.out.println(noOfCourses);
		
		int pAmount = js2.getInt("dashboard.purchaseAmount");
		System.out.println(pAmount);
		
		String firstTitle = js2.getString("courses[0].title");
		System.out.println(firstTitle);
		int sum=0;
		
		for(int i=0; i<noOfCourses; i++) {
			String title = js2.getString("courses["+i+"].title");
			System.out.println("title: " + title + " " + "purchaseamt: " + js2.get("courses["+i+"].price").toString());			
		
		if(title.equalsIgnoreCase("RPA")) {
			System.out.println("copies sold for RPA.: " + js2.getInt("courses["+i+"].copies"));
		}
		
		int price = js2.getInt("courses["+i+"].price");
		int copies = js2.getInt("courses["+i+"].copies");
		int amt = price*copies;
		sum = sum + amt;
				
		}
		
		Assert.assertEquals(sum, pAmount, "amount not same");
		
	}

}
