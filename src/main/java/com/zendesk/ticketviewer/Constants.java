package com.zendesk.ticketviewer;

import java.text.SimpleDateFormat;

public class Constants {
	
	public static final String COMMON_ERROR_MESSAGE = "Error occurred in the ticket viewer application.";
	public static final SimpleDateFormat DATE_TIME_ZONE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	//Default environment variable value is used for unit test cases actual application checks environment variable value at application startup only
	public static final String GET_TICKET_INFO_URL = "https://" + System.getenv().getOrDefault("subdomain", "testSubDomain") + "/api/v2/tickets";
	public static final int NO_OF_TICKETS_PER_PAGE = 25;
	public static final int NO_OF_TICKETS_RETURNED_BY_API_PER_PAGE = 100;
}
