package com.zendesk.ticketviewer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class TicketServiceFacadeTest {
	
    @InjectMocks
    TicketServiceFacade ticketServiceFacade;

    RestTemplate restTemplate = mock(RestTemplate.class);

    @Before
    public void setUp() {
        ticketServiceFacade = new TicketServiceFacade(restTemplate);
        ticketServiceFacade.setRestTemplate(restTemplate);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTicketByPageNumber_Success() throws ApplicationException, JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("testUserName", "testPassword");
        String ticket = "{\"tickets\":[{\"url\":\"https://zccnikitastudent.zendesk.com/api/v2/tickets/1.json\",\"id\":1,\"external_id\":null,\"via\":{\"channel\":\"sample_ticket\",\"source\":{\"from\":{},\"to\":{},\"rel\":null}},\"created_at\":\"2021-11-19T19:51:50Z\",\"updated_at\":\"2021-11-19T19:51:50Z\",\"type\":\"incident\",\"subject\":\"Sample ticket: Meet the ticket\",\"raw_subject\":\"Sample ticket: Meet the ticket\",\"description\":\"Hi there,\\n\\nI’m sending an email because I’m having a problem setting up your new product. Can you help me troubleshoot?\\n\\nThanks,\\n The Customer\\n\\n\",\"priority\":\"normal\",\"status\":\"open\",\"recipient\":null,\"requester_id\":1267056648729,\"submitter_id\":1267062706630,\"assignee_id\":1267062706630,\"organization_id\":null,\"group_id\":1260815611389,\"collaborator_ids\":[],\"follower_ids\":[],\"email_cc_ids\":[],\"forum_topic_id\":null,\"problem_id\":null,\"has_incidents\":false,\"is_public\":true,\"due_at\":null,\"tags\":[\"sample\",\"support\",\"zendesk\"],\"custom_fields\":[],\"satisfaction_rating\":null,\"sharing_agreement_ids\":[],\"fields\":[],\"followup_ids\":[],\"ticket_form_id\":1260814882109,\"brand_id\":1260803204689,\"allow_channelback\":false,\"allow_attachments\":true}],\"count\":1}";
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                ticket,
                null,
                HttpStatus.OK
        );
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.GET_TICKET_INFO_URL)
                .queryParam("page", 1);
        TicketInfo expectedTicketInfo = new TicketInfo();
        expectedTicketInfo.setTotalTickets(1);

        when(restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, request, String.class)).thenReturn(responseEntity);
        TicketInfo actualTicketInfo = ticketServiceFacade.getTicketsByPageNumber(1);

        assertEquals(expectedTicketInfo.getTotalTickets(), actualTicketInfo.getTotalTickets());
    }

    @Test
    public void getTicketInfo_Success() throws ApplicationException, JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("testUserName", "testPassword");
        String ticket = "{\"ticket\":{\"url\":\"https://zccnikitastudent.zendesk.com/api/v2/tickets/1.json\",\"id\":1,\"external_id\":null,\"via\":{\"channel\":\"sample_ticket\",\"source\":{\"from\":{},\"to\":{},\"rel\":null}},\"created_at\":\"2021-11-19T19:51:50Z\",\"updated_at\":\"2021-11-19T19:51:50Z\",\"type\":\"incident\",\"subject\":\"Sample ticket: Meet the ticket\",\"raw_subject\":\"Sample ticket: Meet the ticket\",\"description\":\"Hi there,\\n\\nI’m sending an email because I’m having a problem setting up your new product. Can you help me troubleshoot?\\n\\nThanks,\\n The Customer\\n\\n\",\"priority\":\"normal\",\"status\":\"open\",\"recipient\":null,\"requester_id\":1267056648729,\"submitter_id\":1267062706630,\"assignee_id\":1267062706630,\"organization_id\":null,\"group_id\":1260815611389,\"collaborator_ids\":[],\"follower_ids\":[],\"email_cc_ids\":[],\"forum_topic_id\":null,\"problem_id\":null,\"has_incidents\":false,\"is_public\":true,\"due_at\":null,\"tags\":[\"sample\",\"support\",\"zendesk\"],\"custom_fields\":[],\"satisfaction_rating\":null,\"sharing_agreement_ids\":[],\"fields\":[],\"followup_ids\":[],\"ticket_form_id\":1260814882109,\"brand_id\":1260803204689,\"allow_channelback\":false,\"allow_attachments\":true}}";
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                ticket,
                null,
                HttpStatus.OK
        );
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.GET_TICKET_INFO_URL)
                .queryParam("page", 1);

        when(restTemplate.exchange(Constants.GET_TICKET_INFO_URL +"/" + 1, HttpMethod.GET, request, String.class)).thenReturn(responseEntity);
        Ticket actualTicket = ticketServiceFacade.getTicketInfo(1);
        assertNotNull((actualTicket));
        assertEquals("Sample ticket: Meet the ticket", actualTicket.getSubject());
        assertEquals("open", actualTicket.getStatus());
    }

}
