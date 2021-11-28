package com.zendesk.ticketviewer;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

public class TicketServiceFacade {

	 private RestTemplate restTemplate = new RestTemplate();
	 
	 public Ticket getTicketInfo(int id) throws ApplicationException {
		Ticket ticket = null;
		 try {
			 HttpHeaders headers = new HttpHeaders();
			 headers.setBasicAuth(System.getenv("Username"), System.getenv("Password"));
			 HttpEntity<String> request = new HttpEntity<String>(headers);
		
			 ResponseEntity<String> response = restTemplate.exchange(Constants.GET_TICKET_INFO_URL +"/" + id, HttpMethod.GET, 
					 request, String.class);
			 handleResponseErrors(response);
			 JSONObject responseBody = new JSONObject(response.getBody().toString());
			 Gson gson = new Gson();  
			 ticket = gson.fromJson(responseBody.getJSONObject("ticket").toString(), Ticket.class);
			 
		 } catch (ApplicationException ex) {
			 throw ex;
		 } catch (Exception ex) {
			 System.out.println(Constants.COMMON_ERROR_MESSAGE + ex);
		 }
		 return ticket;
			 
	}
	
	public int getTicketsByPageNumber(int pageNumber, List<Ticket> ticketList) throws ApplicationException {
	
		int numberOfTickets = 0;
		
		try {
			JSONArray ticketArray = null;
			HttpHeaders headers = new HttpHeaders();
		    headers.setBasicAuth(System.getenv("Username"), System.getenv("Password"));
		    HttpEntity<String> request = new HttpEntity<String>(headers);
		    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.GET_TICKET_INFO_URL)
				    .queryParam("page", pageNumber);
			ResponseEntity<String> response = restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, 
			    request, String.class);
			handleResponseErrors(response);
			JSONObject responseBody = new JSONObject(response.getBody().toString());  
			numberOfTickets = responseBody.getInt("count");
			ticketArray = responseBody.getJSONArray("tickets");  
			Gson gson = new Gson();  
			  
			for (int ticketNo =0; ticketNo<ticketArray.length(); ticketNo++) {
				ticketList.add(gson.fromJson(ticketArray.getString(ticketNo), Ticket.class));
			        
			}
			
		} catch (ApplicationException ex) {
			throw ex;
		} catch (Exception ex) {
			System.out.println(Constants.COMMON_ERROR_MESSAGE + ex);
		}
		return numberOfTickets;
	}
	
	private void handleResponseErrors(ResponseEntity<String> response) throws ApplicationException {
		if (response.getStatusCodeValue() != 200) {
			throw new ApplicationException(response.getStatusCodeValue());
		}
	}
	
}
