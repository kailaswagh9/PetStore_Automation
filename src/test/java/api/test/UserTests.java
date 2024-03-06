package api.test;

import static org.testng.Assert.assertEquals;


import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	
	@Test(priority=1)
	public void testPostUser() {
		Response response= UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		Response response= UserEndpoints.readUser(userPayload.getUsername());
		response.then().log().all();
		System.out.println("Email id is -> "+ response.jsonPath().get("email"));
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	

	@Test(priority=3)
	public void testUpdateUserByName() {
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response= UserEndpoints.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//Checking data after update
		Response responseAfterUpdate =UserEndpoints.readUser(userPayload.getUsername());
		responseAfterUpdate.then().log().all();
					
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@Test(priority=4)
	public void testDeleteUserByName() {
		Response response= UserEndpoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
		
}
