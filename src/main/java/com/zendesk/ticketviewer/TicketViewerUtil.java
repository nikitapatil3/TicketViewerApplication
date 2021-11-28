package com.zendesk.ticketviewer;

import org.json.JSONException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.zendesk.ticketviewer.Constants.NO_OF_TICKETS_PER_PAGE;
import static com.zendesk.ticketviewer.Constants.NO_OF_TICKETS_RETURNED_BY_API_PER_PAGE;

public class TicketViewerUtil {
	
	private TicketServiceFacade ticketServiceFacade;
	
	private List<Ticket> ticketList = new ArrayList<>();
	private int noOfTickets = 0;
	private Scanner inputScanner = new Scanner(System.in);

	public TicketViewerUtil(RestTemplate restTemplate) {
		this.ticketServiceFacade = new TicketServiceFacade(restTemplate);
	}

	/**
	 * This method is used to show information about all tickets
	 * It first fetch 100 tickets and then displays them
	 * when user wants to go to page which will have tickets with id greater than 100 thenit calls ticket service api to get tickets from that page
	 * Whenever API is called ticket information is stored in the ticketlist, so when user navigates to different page there is no need to call API every time
	 * @throws JSONException
	 * @throws ParseException
	 */
	public void showAllTickets() throws JSONException, ParseException {
		try {
			if (this.ticketList.isEmpty()) {
				TicketInfo ticketInfo = ticketServiceFacade.getTicketsByPageNumber(1);
				this.noOfTickets = ticketInfo.getTotalTickets();
				this.ticketList.addAll(ticketInfo.getTicketList());
			}
			
			int totalPages = this.noOfTickets/NO_OF_TICKETS_PER_PAGE + (this.noOfTickets%NO_OF_TICKETS_PER_PAGE == 0 ? 0: 1);
			if (this.noOfTickets == 0) {
				System.out.println("No ticket exists in the system");
				return;
			}
			
			System.out.println("\t\tTotal tickets " + this.noOfTickets + " Showing " + NO_OF_TICKETS_PER_PAGE
					+ " ticket per Page. " + "Total Number of pages are "
					+ totalPages +".");
			int currentPageNumber = 1;
		
			while (true) {
				System.out.println("\t\tCurrently showing page number: " + currentPageNumber);
				
				for (int ticketNo = (currentPageNumber-1) * NO_OF_TICKETS_PER_PAGE;
					 	ticketNo < (currentPageNumber-1) * NO_OF_TICKETS_PER_PAGE + NO_OF_TICKETS_PER_PAGE
						&& ticketNo < this.ticketList.size(); ticketNo++) {
					System.out.println("\t\tTicket with id " + this.ticketList.get(ticketNo).getId()+ " with subject "+ this.ticketList.get(ticketNo).getSubject()
							+" is requested by " + this.ticketList.get(ticketNo).getRequester_id() + " and it is submitted by "
							+ this.ticketList.get(ticketNo).getSubmitter_id() + " On "
							+ Constants.DATE_TIME_ZONE_FORMATTER.parse(this.ticketList.get(ticketNo).getCreated_at()));
				}
				
				System.out.println("\n\t\tEnter Page Number(1-" + totalPages + ") to view tickets on that page and enter any other number to check other options: ");
				
				currentPageNumber = Integer.parseInt(inputScanner.next());
				if (currentPageNumber < 1 || currentPageNumber > totalPages) {
					break;
				}
				
				if (this.ticketList.size() < currentPageNumber * NO_OF_TICKETS_PER_PAGE &&
						this.ticketList.size() != this.noOfTickets) {
					this.ticketList.addAll(ticketServiceFacade.getTicketsByPageNumber((currentPageNumber * NO_OF_TICKETS_PER_PAGE) / NO_OF_TICKETS_RETURNED_BY_API_PER_PAGE + 1)
							.getTicketList());
				}
			}
		} catch (ApplicationException ex) {
			handleApplicationException(ex);
		} catch (NumberFormatException ex) {
			 System.out.println("Wrong Input");
		}
	}

	/**
	 * This method shows information about specific ticket.
	 * @throws JSONException
	 * @throws ParseException
	 */
	public void showSpecificTicket() throws JSONException, ParseException {
		
		System.out.println("\tEnter Ticket id to see information of that ticket: ");
		try {
			int ticketId = Integer.parseInt(inputScanner.next());
			Ticket ticket = ticketServiceFacade.getTicketInfo(ticketId);
			System.out.println("\t\tTicket with id " + ticket.getId() + " with subject "+ ticket.getSubject() + " and with description " + ticket.getDescription() + " is requested by " +
					ticket.getRequester_id() + " and it is submitted by " + ticket.getSubmitter_id() + " On "
					+ Constants.DATE_TIME_ZONE_FORMATTER.parse(ticket.getCreated_at()) + ". Current status of the ticket is "+ ticket.getStatus()+".");
		} catch (ApplicationException ex) {
			handleApplicationException(ex);
		} catch (NumberFormatException ex) {
			 System.out.println("Wrong Input");
		}
	}

	/**
	 * This method handles application exception
	 * @param ex
	 */
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

	/**
	 * This method is used to get total number of tickets in the unit test case
	 * @return number of tickets in the system
	 */
	public int getNoOfTickets() {
		return this.noOfTickets;
	}

	/**
	 * This method is used to get ticket list in the unit test
	 * @return returns ticket list
	 */
	public List<Ticket> getTicketList() {
		return this.ticketList;
	}

	/**
	 * This method is used to mock ticketservicefacade in the unit test
	 * @param ticketServiceFacade TicketServiceFacade
	 */
	public void setTicketServiceFacade(TicketServiceFacade ticketServiceFacade) {
		this.ticketServiceFacade = ticketServiceFacade;
	}

	/**
	 * This method is used to mock the input scanner in the unit test cases
	 * @param sc input scanner
	 */
	public void setInputScanner(Scanner sc) {
		this.inputScanner = sc;
	}
}
