package com.zendesk.ticketviewer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TicketTest {
	
	@Test
	public void testHashCode() {
		Ticket ticket1 = getTicket1();
		Ticket ticket2 = getTicket1();
		Ticket ticket3 = getTicket2();
		assertEquals(ticket1.hashCode(), ticket2.hashCode());
		assertNotEquals(ticket1.hashCode(), ticket3.hashCode());
	}
	
	@Test
	public void testEquals() {
		Ticket ticket1 = getTicket1();
		Ticket ticket2 = getTicket1();
		Ticket ticket3 = getTicket2();
		assertEquals(ticket1, ticket2);
		assertNotEquals(ticket1, ticket3);
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
		ticket1.setId(12);
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
