package com.zendesk.ticketviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketViewerUtil {
	
	private TicketServiceFacade ticketServiceFacade = new TicketServiceFacade();
	
	private static List<Ticket> ticketList = new ArrayList<>();
	private static int noOfTickets = 0;
	private static Scanner inputScanner = new Scanner(System.in);
	 
	
	public void showAllTickets() {
		
		try {
			if (ticketList.isEmpty())
				noOfTickets = ticketServiceFacade.getTicketsByPageNumber(1, ticketList);
			
			int totalPages = noOfTickets/25 + (noOfTickets%25 == 0 ? 0: 1);
			if (noOfTickets == 0) {
				System.out.println("No ticket exists in the system");
				return;
			}
			
			System.out.println("\t\tTotal tickets " + noOfTickets + " Showing 25 ticket per Page. " + "Total Number of pages are " 
					+ totalPages +".");
			int currentPageNumber = 1;
		
			while (true) {
				System.out.println("\t\tCurrently showing page number: " + currentPageNumber);
				
				for (int ticketNo = (currentPageNumber-1) * 25; ticketNo < (currentPageNumber-1) * 25 + 25
						&& ticketNo < ticketList.size(); ticketNo++) {
					System.out.println("\t\tTicket with id " + ticketList.get(ticketNo).getId()+ " with subject "+ ticketList.get(ticketNo).getSubject() 
							+" is requested by " + ticketList.get(ticketNo).getRequester_id() + " and it is submitted by " 
							+ ticketList.get(ticketNo).getSubmitter_id() + " On " 
							+ Constants.DATE_TIME_ZONE_FORMATTER.parse(ticketList.get(ticketNo).getCreated_at()));
				}
				
				System.out.println("\n\t\tEnter Page Number(1-" + totalPages + ") to view tickets on that page and enter any other number to exit");
				
				currentPageNumber = Integer.parseInt(inputScanner.next());
				if (currentPageNumber < 1 || currentPageNumber > totalPages) {
					break;
				}
				
				if (ticketList.size() < currentPageNumber*25) {
					ticketServiceFacade.getTicketsByPageNumber((currentPageNumber *25) /100 + 1, ticketList);
				}
			}
		} catch (ApplicationException ex) {
			handleApplicationException(ex);
		} catch (Exception ex) {
			 System.out.println(Constants.COMMON_ERROR_MESSAGE + ex);
		}
	}
	
	public void showSpecificTicket() {
		
		System.out.println("\tEnter Ticket Number to see information of that ticket: ");
		try {
			int ticketNumber = Integer.parseInt(inputScanner.next());
		
			Ticket ticket = null;
			if (ticketList.isEmpty() || ticketNumber > ticketList.size())
				ticket = ticketServiceFacade.getTicketInfo(ticketNumber);
			else 
				ticket = ticketList.get(ticketNumber-1);
			
			System.out.println("\t\tTicket with subject "+ ticket.getSubject() + " and with description " + ticket.getDescription() + " is requested by " + 
					ticket.getRequester_id() + " and it is submitted by " + ticket.getSubmitter_id() + " On " 
					+ Constants.DATE_TIME_ZONE_FORMATTER.parse(ticket.getCreated_at()) + ". Current status of the ticket is "+ ticket.getStatus()+".");
		} catch (ApplicationException ex) {
			handleApplicationException(ex);
		} catch (Exception ex) {
			 System.out.println(Constants.COMMON_ERROR_MESSAGE + ex);
		}
		
	}

	private void handleApplicationException(ApplicationException ex) {
		
		switch (ex.getErrorCode()) {
			case 401:
				System.out.println("User is not authenticated to perform this action.");
				break;
			case 404:
				System.out.println("Ticket Information not found in the system.");
				break;
			default:
				System.out.println("Ticket service returned response other than success. There is some error in the system.");
				break;
		}
		
	}
	
}
