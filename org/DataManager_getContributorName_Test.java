import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class DataManager_getContributorName_Test {
	
	/*
	 * This is a test class for the DataManager.createFund method.
	 * Add more tests here for this method as needed.
	 * 
	 * When writing tests for other methods, be sure to put them into separate
	 * JUnit test classes.
	 */

	@Test
	public void testSuccessfulLookup() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "{\"status\":\"success\",\"data\":\"utkarsh\"}";

			}
			
		});
		
		
		String name = dm.getContributorName("123");
		
		assertNotNull(name);
		assertEquals("utkarsh", name);
	}
	
	@Test
	public void testLookupFailedWellFormedResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "{\"status\":\"unauthorized\"}";

			}
			
		});
		
		
		String name = dm.getContributorName("123");
		
		assertNull(name);
	}
	
	@Test
	public void testLookupFailedStatusMissingInResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "{\"unknown_field\":\"unauthorized\"}";

			}
			
		});
		
		
		String name = dm.getContributorName("123");
		
		assertNull(name);
	}

	@Test
	public void testLookupFailedNullResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "";

			}
			
		});
		
		
		String name = dm.getContributorName("123");
		
		assertNull(name);
	}
	
	@Test
	public void testLookupFailedMalformedResponse() {

		DataManager dm = new DataManager(new WebClient("localhost", 3001) {
			
			@Override
			public String makeRequest(String resource, Map<String, Object> queryParams) {
				return "this_is_not_json";

			}
			
		});
		
		
		String name = dm.getContributorName("123");
		
		assertNull(name);
	}
}
