package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDataDriverTest {

	User userPayload;
	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String username, String fname,String lname, String email, String pass, String phone) {
		userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(username);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pass);
		userPayload.setPhone(phone);
		
		Response response= UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username) {
	
		Response response= UserEndpoints.deleteUser(username);
			
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
}
