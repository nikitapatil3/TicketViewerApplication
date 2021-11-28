package com.zendesk.ticketviewer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

public class TicketServiceFacade {

	 private RestTemplate restTemplate;
	 private HttpHeaders headers = new HttpHeaders();

	 public TicketServiceFacade(RestTemplate restTemplate) {
		 this.restTemplate = restTemplate;
		 //Default environment value is used for unit test cases actual application checks environment variable value at application startup only
		 this.headers.setBasicAuth(System.getenv().getOrDefault("Username", "testUserName"),
				 System.getenv().getOrDefault("Password", "testPassword"));
	 }

	/**
	 *
	 * @param id id of the ticket to see detailed information of that ticket
	 * @return ticket information
	 * @throws ApplicationException if response returned from server is other than success
	 * @throws JSONException
	 */
	 public Ticket getTicketInfo(int id) throws ApplicationException, JSONException {
		Ticket ticket;
		 try {
			 HttpEntity<String> request = new HttpEntity<>(this.headers);
			 ResponseEntity<String> response = restTemplate.exchange(Constants.GET_TICKET_INFO_URL +"/" + id, HttpMethod.GET, 
					 request, String.class);
			 JSONObject responseBody = new JSONObject(response.getBody());
			 Gson gson = new Gson();  
			 ticket = gson.fromJson(responseBody.getJSONObject("ticket").toString(), Ticket.class);
		 } catch (HttpClientErrorException ex) {
			 throw new ApplicationException(ex.getStatusCode().value());
		 }
		 return ticket;
	}

	/**
	 * This method return ticket by pages, it returns 100 tickets per page, if page has less than 100 tickets then it returns them.
	 * @param pageNumber page number to return tickets on that page
	 * @return information about all tickets and count of tickets in the system
	 * @throws ApplicationException if response returned from server is other than success
	 * @throws JSONException
	 */
	public TicketInfo getTicketsByPageNumber(int pageNumber) throws ApplicationException, JSONException {
		TicketInfo ticketInfo = new TicketInfo();
		List<Ticket> ticketList = new ArrayList<>();
		try {
			JSONArray ticketArray;
		    HttpEntity<String> request = new HttpEntity<>(this.headers);
		    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.GET_TICKET_INFO_URL)
				    .queryParam("page", pageNumber);
			ResponseEntity<String> response = restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, 
			    request, String.class);

			JSONObject responseBody = new JSONObject(response.getBody());
			ticketInfo.setTotalTickets(responseBody.getInt("count"));
			ticketArray = responseBody.getJSONArray("tickets");  
			Gson gson = new Gson();  
			  
			for (int ticketNo =0; ticketNo<ticketArray.length(); ticketNo++) {
				ticketList.add(gson.fromJson(ticketArray.getString(ticketNo), Ticket.class));
			}
			ticketInfo.setTicketList(ticketList);
		} catch (HttpClientErrorException ex) {
			throw new ApplicationException(ex.getStatusCode().value());
		}
		return ticketInfo;
	}

	/**
	 * This method is used to mock the rest template in the  unit test cases
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
}
