package in.ashokeit.service;

import in.ashokeit.request.Passenger;
import in.ashokeit.response.Ticket;

public interface MakeMyTrip {
	
	public Ticket bookTicket(Passenger passenger);
	
	public Ticket getTicketByNum(Integer ticketNumber);

}
