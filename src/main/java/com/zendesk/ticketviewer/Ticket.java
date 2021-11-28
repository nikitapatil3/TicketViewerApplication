package com.zendesk.ticketviewer;

public class Ticket {
	
	private Integer id;
	private String subject;
	private String updated_at;
	private String created_at;
	private String description;
	private String assignee_id;
	private String requester_id;
	private String submitter_id;
	private String status;

	public String getSubject() {
		return subject;
	}

	public String getUpdated_at() {
		return updated_at;
	}
	
	public String getCreated_at() {
		return created_at;
	}

	public String getDescription() {
		return description;
	}

	public String getAssignee_id() {
		return assignee_id;
	}

	public String getRequester_id() {
		return requester_id;
	}

	public String getStatus() {
		return status;
	}

	public String getSubmitter_id() {
		return submitter_id;
	}

	public Integer getId() {
		return id;
	}

}
