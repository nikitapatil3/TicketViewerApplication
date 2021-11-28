package com.zendesk.ticketviewer;

import java.text.SimpleDateFormat;

public class Constants {
	
	public static final String COMMON_ERROR_MESSAGE = "Error occurred in the ticket viewer application.";
	public static final SimpleDateFormat DATE_TIME_ZONE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
	public static final String GET_TICKET_INFO_URL = "https://zccnikitastudent.zendesk.com/api/v2/tickets";
}
