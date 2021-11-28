package com.zendesk.ticketviewer;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TicketViewerUtilTest {
    @InjectMocks
    TicketViewerUtil ticketViewerUtil;

    TicketServiceFacade ticketServiceFacade = mock(TicketServiceFacade.class);
    Scanner inputScanner = mock(Scanner.class);
    RestTemplate restTemplate = mock(RestTemplate.class);

    @Before
    public void setUp() {
        ticketViewerUtil = new TicketViewerUtil(restTemplate);
        ticketViewerUtil.setTicketServiceFacade(ticketServiceFacade);
        ticketViewerUtil.setInputScanner(inputScanner);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void showAllTicket_Success_SinglePage() throws ApplicationException, JSONException, ParseException {
        List<Ticket> ticketList = createTicketList();
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setTicketList(ticketList);
        ticketInfo.setTotalTickets(5);
        when(ticketServiceFacade.getTicketsByPageNumber(1)).thenReturn(ticketInfo);
        when(inputScanner.next()).thenReturn("2");
        ticketViewerUtil.showAllTickets();
        assertEquals(ticketViewerUtil.getNoOfTickets(), 5);
        assertEquals(ticketViewerUtil.getTicketList(), ticketList);
    }

    @Test
    public void showAllTicket_Success_MultiplePages() throws ApplicationException, JSONException, ParseException {
        List<Ticket> ticketList = createTicketList();
        List<Ticket> secondPageList = createSecondPageList();

        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setTicketList(ticketList);
        ticketInfo.setTotalTickets(28);
        TicketInfo ticketInfo2 = new TicketInfo();
        ticketInfo2.setTicketList(secondPageList);
        when(ticketServiceFacade.getTicketsByPageNumber(1)).thenReturn(ticketInfo, ticketInfo2);
        when(inputScanner.next()).thenReturn("2", "3");
        ticketViewerUtil.showAllTickets();
        assertEquals(ticketViewerUtil.getNoOfTickets(), 28);
    }

    @Test
    public void showAllTicket_Success_ZeroTickets() throws ApplicationException, JSONException, ParseException {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setTicketList(new ArrayList<>());
        ticketInfo.setTotalTickets(0);
        when(ticketServiceFacade.getTicketsByPageNumber(1)).thenReturn(ticketInfo);
        ticketViewerUtil.showAllTickets();
        assertEquals(ticketViewerUtil.getNoOfTickets(), 0);
    }

    @Test
    public void showSpecificTicket_Success() throws ApplicationException, JSONException, ParseException {
    	when(inputScanner.next()).thenReturn("2");
    	when(ticketServiceFacade.getTicketInfo(2)).thenReturn(getTicket2());
    	ticketViewerUtil.showSpecificTicket();
 
    }
    
    private List<Ticket> createTicketList() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(getTicket1());
        ticketList.add(getTicket2());
        Ticket ticket3 = getTicket2();
        ticket3.setId(3);
        Ticket ticket4 = getTicket2();
        ticket4.setId(4);
        Ticket ticket5 = getTicket2();
        ticket5.setId(5);
        Ticket ticket6 = getTicket2();
        ticket6.setId(6);
        Ticket ticket7 = getTicket2();
        ticket7.setId(7);
        Ticket ticket8 = getTicket2();
        ticket8.setId(8);
        Ticket ticket9 = getTicket2();
        ticket9.setId(9);
        Ticket ticket10 = getTicket2();
        ticket10.setId(10);
        Ticket ticket11 = getTicket2();
        ticket11.setId(11);
        Ticket ticket12 = getTicket2();
        ticket12.setId(12);
        Ticket ticket13 = getTicket2();
        ticket13.setId(13);
        Ticket ticket14 = getTicket2();
        ticket14.setId(14);
        Ticket ticket15 = getTicket2();
        ticket15.setId(15);
        Ticket ticket16 = getTicket2();
        ticket16.setId(16);
        Ticket ticket17 = getTicket2();
        ticket17.setId(17);
        Ticket ticket18 = getTicket2();
        ticket18.setId(18);
        Ticket ticket19 = getTicket2();
        ticket19.setId(19);
        Ticket ticket20 = getTicket2();
        ticket20.setId(20);
        Ticket ticket21 = getTicket2();
        ticket21.setId(21);
        Ticket ticket22 = getTicket2();
        ticket22.setId(22);
        Ticket ticket23 = getTicket2();
        ticket23.setId(23);
        Ticket ticket24 = getTicket2();
        ticket24.setId(24);
        Ticket ticket25 = getTicket2();
        ticket25.setId(25);
        ticketList.add(ticket3);
        ticketList.add(ticket4);
        ticketList.add(ticket5);
        ticketList.add(ticket6);
        ticketList.add(ticket7);
        ticketList.add(ticket8);
        ticketList.add(ticket9);
        ticketList.add(ticket10);
        ticketList.add(ticket11);
        ticketList.add(ticket12);
        ticketList.add(ticket13);
        ticketList.add(ticket14);
        ticketList.add(ticket15);
        ticketList.add(ticket16);
        ticketList.add(ticket17);
        ticketList.add(ticket18);
        ticketList.add(ticket19);
        ticketList.add(ticket20);
        ticketList.add(ticket21);
        ticketList.add(ticket22);
        ticketList.add(ticket23);
        ticketList.add(ticket24);
        ticketList.add(ticket25);

        return ticketList;

    }

    private List<Ticket> createSecondPageList() {
        List<Ticket> ticketList = new ArrayList<>();
        Ticket ticket26 = getTicket1();
        ticket26.setId(26);
        Ticket ticket27 = getTicket1();
        ticket27.setId(27);
        Ticket ticket28 = getTicket1();
        ticket28.setId(28);
        ticketList.add(ticket26);
        ticketList.add(ticket27);
        ticketList.add(ticket28);
        return ticketList;
    }

    private Ticket getTicket1() {
        Ticket ticket1 = new Ticket();
        ticket1.setId(1);
        ticket1.setSubject("Test 1");
        ticket1.setAssignee_id("1");
        ticket1.setRequester_id("1");
        ticket1.setStatus("open");
        ticket1.setCreated_at("2021-11-19T20:51:07Z");
        ticket1.setSubmitter_id("1");
        ticket1.setDescription("Testing ticket");
        ticket1.setUpdated_at("2021-11-19T20:51:07Z");
        return ticket1;
    }

    private Ticket getTicket2() {
        Ticket ticket1 = new Ticket();
        ticket1.setId(2);
        ticket1.setSubject("Test 12");
        ticket1.setAssignee_id("2");
        ticket1.setRequester_id("2");
        ticket1.setStatus("open");
        ticket1.setCreated_at("2021-11-19T20:51:07Z");
        ticket1.setSubmitter_id("2");
        ticket1.setDescription("Testing ticket");
        ticket1.setUpdated_at("2021-11-19T20:51:07Z");
        return ticket1;
    }
}
