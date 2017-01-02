package practice.assignment.parkinglot.domain;

import java.util.Date;

public class ParkTicket {
	public enum TicketStatus{
		SUCCESS, FAILURE,UNPARKED
	}
	private SLot lot;
	
	private TicketStatus ticketStatus = TicketStatus.FAILURE;
	
	private Date start;
	
	private Date end;
	

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public SLot getLot() {
		return lot;
	}

	public void setLot(SLot lot) {
		this.lot = lot;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	//Issue a print command
	public void printTicket(){
		
	}
	
}
