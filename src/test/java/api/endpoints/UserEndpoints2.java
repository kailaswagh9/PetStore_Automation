package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This is user end points java class. // created to perform Create,Read,Update,Delete the user API.
public class UserEndpoints2 {
	
	//method created for getting URL's from properties file
	public static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file "route" is name of propertiese file.
		return routes;
	}

	public static Response createUser(User payload) {
		
		String post_url = getURL().getString("user_post_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return response;
	}
	
	public static Response readUser(String userName) {
		
		String get_url = getURL().getString("user_get_url");
		Response response =	given()
								.pathParam("username", userName)	
							.when()
								.get(get_url);
		
		return response;
	}
	
public static Response updateUser(String userName, User payload) {
	String update_url = getURL().getString("user_update_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		.when()
			.put(update_url);
		
		return response;
	}

public static Response deleteUser(String userName) {
	String delete_url = getURL().getString("user_delete_url");
	Response response =	given()
							.pathParam("username", userName)	
						.when()
							.delete(delete_url);
	
	return response;
}
}
