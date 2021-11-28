package com.zendesk.ticketviewer;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TicketViewerApplicationRunner {
	
    private static ConfigurableApplicationContext context;
    private static Scanner inputScanner = new Scanner(System.in);
    
   
	public static void main(String[] args) {
		try {
			TicketViewerUtil ticketViewerUtil = new TicketViewerUtil();
			context = SpringApplication.run(TicketViewerApplicationRunner.class, args);
			System.out.println("\nWelcome to the Ticket Viewer Application");
			System.out.println("Type 'menu' to view options or 'quit' to exit");
			String userOption = inputScanner.next();
			
			if (userOption.equals("menu")) {
				
				String viewOption = "1";
				while (!viewOption.equals("quit")) {
					System.out.println("\t\nSelect view options:");
					System.out.println("\t* Press 1 to view all tickets");
					System.out.println("\t* Press 2 to view a ticket");
					System.out.println("\t* Press 'quit' to exit");
					
					viewOption = inputScanner.next();
					
					switch(viewOption) {
						case "quit":
							terminateApplication();
							break;
						case "1": 
							ticketViewerUtil.showAllTickets();
							break;
						case "2":
							ticketViewerUtil.showSpecificTicket();
							break;
						default:
							System.out.println("\tWrong Option, check again");
							break;
					}
				}
			} else {
				terminateApplication();
			}
		} catch (Exception ex) {
			System.out.println(Constants.COMMON_ERROR_MESSAGE + "Closing the Ticket Viewer Application."+ ex);
			terminateApplication();
		}
		
	}
	
	private static void terminateApplication() {
		System.out.println("Closing the Ticket Viewer Application");
		context.close();
	}
	
	
}
