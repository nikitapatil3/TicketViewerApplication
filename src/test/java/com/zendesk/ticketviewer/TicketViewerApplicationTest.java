package com.zendesk.ticketviewer;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicketViewerApplicationRunner.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketViewerApplicationTest {

    @InjectMocks
    TicketViewerApplicationRunner ticketViewerApplicationRunner;
    Scanner inputScanner = mock(Scanner.class);
    TicketViewerUtil ticketViewerUtil = mock(TicketViewerUtil.class);

    @Before
    public void setUp() {
        ticketViewerApplicationRunner = new TicketViewerApplicationRunner();
        ticketViewerApplicationRunner.setInputScanner(inputScanner);
        ticketViewerApplicationRunner.setTicketViewerUtil(ticketViewerUtil);
        String[] args = {};
        ticketViewerApplicationRunner.setContext(SpringApplication.run(TicketViewerApplicationRunner.class, args));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mainMethodSuccess_ClosesAfter_menu_1_quit() throws JSONException, ParseException {
        when(inputScanner.next()).thenReturn("menu", "1", "quit");
        TicketViewerApplicationRunner.checkDifferentInputCases();
        assertFalse(ticketViewerApplicationRunner.getContext().isRunning());
    }

    @Test
    public void mainMethodSuccess_ClosesAfter_Quit() throws JSONException, ParseException {
        when(inputScanner.next()).thenReturn("quit");
        TicketViewerApplicationRunner.checkDifferentInputCases();
        assertFalse(ticketViewerApplicationRunner.getContext().isRunning());
    }

    @Test
    public void mainMethodSuccess_ClosesAfter_Menu_2_Quit() throws JSONException, ParseException {
        when(inputScanner.next()).thenReturn("menu", "2", "quit");
        TicketViewerApplicationRunner.checkDifferentInputCases();
        assertFalse(ticketViewerApplicationRunner.getContext().isRunning());
    }
}
