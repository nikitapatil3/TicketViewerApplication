package com.zendesk.ticketviewer;

import java.util.List;
import java.util.Objects;

public class TicketInfo {

    private int totalTickets;
    private List<Ticket> ticketList;

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketInfo that = (TicketInfo) o;
        return totalTickets == that.totalTickets && Objects.equals(ticketList, that.ticketList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalTickets, ticketList);
    }

}
