package com.zendesk.ticketviewer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TicketInfoTest {

    @Test
    public void testEquals() {
        TicketInfo ticketInfo1 = new TicketInfo();
        List<Ticket> ticketList = getTicketList();
        ticketInfo1.setTotalTickets(10);
        ticketInfo1.setTicketList(ticketList);

        TicketInfo ticketInfo2 = new TicketInfo();
        ticketInfo2.setTotalTickets(10);
        ticketInfo2.setTicketList(ticketList);

        TicketInfo ticketInfo3 = new TicketInfo();
        ticketInfo3.setTotalTickets(100);
        ticketInfo3.setTicketList(ticketList);

        assertEquals(ticketInfo1, ticketInfo2);
        assertNotEquals(ticketInfo1, ticketInfo3);
    }

    @Test
    public void testHasCode() {
        TicketInfo ticketInfo1 = new TicketInfo();
        List<Ticket> ticketList = getTicketList();
        ticketInfo1.setTotalTickets(10);
        ticketInfo1.setTicketList(ticketList);

        TicketInfo ticketInfo2 = new TicketInfo();
        ticketInfo2.setTotalTickets(10);
        ticketInfo2.setTicketList(ticketList);

        TicketInfo ticketInfo3 = new TicketInfo();
        ticketInfo3.setTotalTickets(100);
        ticketInfo3.setTicketList(ticketList);

        assertEquals(ticketInfo1.hashCode(), ticketInfo2.hashCode());
        assertNotEquals(ticketInfo1.hashCode(), ticketInfo3.hashCode());
    }

    private List<Ticket> getTicketList() {
        List<Ticket> ticketList = new ArrayList<>();
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
        ticketList.add(ticket1);
        return ticketList;
    }

}
