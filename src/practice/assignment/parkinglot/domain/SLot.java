package practice.assignment.parkinglot.domain;

import java.util.Date;

public class SLot {
	public enum LotStatus {
		Filled, Vacant
	}

	private Vehicle vehicle;
	private int lotId;
	private Date start;
	private Date end;

	private boolean reserved;
	private LotStatus lotStatus = LotStatus.Vacant;
	
	
	

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public LotStatus getLotStatus() {
		return lotStatus;
	}

	public void setLotStatus(LotStatus lotStatus) {
		this.lotStatus = lotStatus;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getLotId() {
		return lotId;
	}

	public void setLotId(int lotId) {
		this.lotId = lotId;
	}

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

	public void reset() {
		this.end = null;
		this.start = null;
		this.lotStatus = LotStatus.Vacant;
		this.vehicle = null;
	}

}
