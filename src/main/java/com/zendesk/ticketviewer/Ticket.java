package com.zendesk.ticketviewer;

import java.util.Objects;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAssignee_id(String assignee_id) {
		this.assignee_id = assignee_id;
	}

	public String getRequester_id() {
		return requester_id;
	}

	public void setRequester_id(String requester_id) {
		this.requester_id = requester_id;
	}

	public String getSubmitter_id() {
		return submitter_id;
	}

	public void setSubmitter_id(String submitter_id) {
		this.submitter_id = submitter_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(assignee_id, created_at, description, id, requester_id, status, subject, submitter_id,
				updated_at);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(assignee_id, other.assignee_id) && Objects.equals(created_at, other.created_at)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(requester_id, other.requester_id) && Objects.equals(status, other.status)
				&& Objects.equals(subject, other.subject) && Objects.equals(submitter_id, other.submitter_id)
				&& Objects.equals(updated_at, other.updated_at);
	}
}
