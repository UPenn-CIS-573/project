import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DataManager_attemptLogin_Test {
	
	/*
	 * This is a test class for the DataManager.createFund method.
	 * Add more tests here for this method as needed.
	 * 
	 * When writing tests for other methods, be sure to put them into separate
	 * JUnit test classes.
	 */

	@Test
	public void testSuccessfulLogin() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				if (resource.equals("/findContributorNameById")) {
					return "{\"status\":\"success\",\"data\":\"utkarsh\"}";
				}
				
				return "{\"status\":\"success\", \"data\": {\"_id\": \"123\", "
						+ "\"description\": \"some description\", \"name\": \"abcd\","
						+ "\"funds\": [{\"_id\": \"1\", \"name\": \"xyz\", \"target\": \"1000\","
						+ "\"description\": \"other description\", \"donations\": [{"
						+ "\"contributor\": \"jkl\", \"amount\": \"100\", \"date\": \"12-Dec-2020\"}]}]}}"; 
			}
			
		});
		
		
		Organization org = dm.attemptLogin("utkarsh5k", "password");
		
		assertNotNull(org);
		assertEquals(org.getId(), "123");
		assertEquals(org.getDescription(), "some description");
		assertEquals(org.getName(), "abcd");
		
		List<Fund> funds = org.getFunds(); 
		Fund fund = funds.get(0);
		assertEquals(fund.getId(), "1");
		assertEquals(fund.getName(), "xyz");
		assertEquals(fund.getTarget(), 1000);
		assertEquals(fund.getDescription(), "other description");
		
		Donation donation = fund.getDonations().get(0);
		assertEquals(donation.getAmount(), 100);
		assertEquals(donation.getContributorName(), "utkarsh");
		assertEquals(donation.getDate(), "12-Dec-2020");
		assertEquals(donation.getFundId(), "1");
	}
	
	@Test
	public void testLoginFailedWellFormedResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "{\"status\":\"unauthorized\"}";

			}
			
		});
		
		
		Organization org = dm.attemptLogin("utkarsh5k", "password");
		
		assertNull(org);
	}
	
	@Test
	public void testLoginFailedStatusMissingInResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "{\"unknown_field\":\"unauthorized\"}";

			}
			
		});
		
		
		Organization org = dm.attemptLogin("utkarsh5k", "password");
		
		assertNull(org);
	}

	@Test
	public void testLoginFailedNullResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "";

			}
			
		});
		
		
		Organization org = dm.attemptLogin("utkarsh5k", "password");
		
		assertNull(org);
	}
	
	@Test
	public void testLoginFailedMalformedResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "this_is_not_json";

			}
			
		});
		
		
		Organization org = dm.attemptLogin("utkarsh5k", "password");
		
		assertNull(org);
	}
}
