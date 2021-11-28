package com.zendesk.ticketviewer;

import java.text.ParseException;
import java.util.Scanner;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TicketViewerApplicationRunner {
	
    private static ConfigurableApplicationContext context;
    private static Scanner inputScanner = new Scanner(System.in);
	private static TicketViewerUtil ticketViewerUtil = new TicketViewerUtil(new RestTemplate());

	/**
	 * Main Method to run TicketViewer Application
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		try {
			context = SpringApplication.run(TicketViewerApplicationRunner.class, args);
			if (checkEnvironmentProperty()) {
				checkDifferentInputCases();
			}
		} catch (Exception ex) {
			System.out.println(Constants.COMMON_ERROR_MESSAGE + "Closing the Ticket Viewer Application."+ ex);
			terminateApplication();
		}
	}

	/**
	 * This method takes inputs from user and then perform action according the request.
	 * option menu opens menu which shows option 1, 2 and quit
	 * option quit terminates the ticket viewer application
	 * option 1 shows all tickets with pagination support (25 tickets on one page)
	 * option 2 takes ticket number from user and shows that ticket's information
	 * @throws ParseException
	 * @throws JSONException
	 */
	public static void checkDifferentInputCases() throws JSONException, ParseException {
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
						System.out.println("\tWrong Option, Please enter correct option.");
						break;
				}
			}
		} else if (userOption.equals("quit")){
			terminateApplication();
		} else {
			System.out.println("Wrong option.");
			terminateApplication();
		}
	}

	/**
	 * This method terminates the springboot application
	 */
	private static void terminateApplication() {
		System.out.println("Closing the Ticket Viewer Application.");
		context.close();
	}

	/**
	 * This method checks if username, password and subdomain is properly set in the environment variable or not
	 * @return true if environment variables are correct
	 * @Return false if environment variables are wrong
	 */
	private static Boolean checkEnvironmentProperty() {
		if (System.getenv("Username") == null || System.getenv("Password") == null || System.getenv("subdomain") == null) {
			System.out.println("Username or Password or subdomain are not set in the environment variable in the run configuration. "
					+ "Set Username and Password and subdomain in Environment Variable and then start the Tickket Viewer Application.");
			terminateApplication();
			return false;
		}
		return true;
	}

	/**
	 * This method is used in the unit test to enter scanner input
	 * @param sc Scanner
	 */
	public void setInputScanner(Scanner sc) {
		inputScanner = sc;
	}

	/**
	 * This method is used in the unit test to mock ticketviewerutil class
	 * @param ticketViewerUtil
	 */
	public void setTicketViewerUtil(TicketViewerUtil ticketViewerUtil) {
		TicketViewerApplicationRunner.ticketViewerUtil = ticketViewerUtil;
	}

	/**
	 * This method is used to get application context in the unit test
	 * @return
	 */
	public ConfigurableApplicationContext getContext() {
		return context;
	}

	/**
	 * This method is used to set Application Context for the unit test
	 * @param context ConfigurableApplicationContext
	 */
	public void setContext(ConfigurableApplicationContext context) {
		TicketViewerApplicationRunner.context = context;
	}
}
