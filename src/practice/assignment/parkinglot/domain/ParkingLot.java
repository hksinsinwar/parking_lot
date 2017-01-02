package practice.assignment.parkinglot.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import practice.assignment.parkinglot.constants.MessageConstants;
import practice.assignment.parkinglot.domain.ParkTicket.TicketStatus;
import practice.assignment.parkinglot.domain.SLot.LotStatus;

public class ParkingLot {

	private int capacity;

	private int totalFilled;

	private Map<String, SLot> lotMap;
	private SLot[] lot;

	public ParkingLot(int capacity) {
		this.capacity = capacity;
		this.lotMap = new HashMap<>();
		this.lot = new SLot[capacity];
		this.totalFilled = 0;

		initParkingLot(lot, capacity);

	}

	private void initParkingLot(SLot[] lot2, int capacity2) {
		for (int i = 0; i < capacity2; i++) {
			SLot sLot = new SLot();
			sLot.setLotId(i + 1); // lotId is 1 based instead of 0
			lot2[i] = sLot;
		}
	}

	public ParkTicket parkVehicle(Vehicle vehicle) {
		ParkTicket parkTicket = new ParkTicket();
		if (totalFilled >= capacity) {
			parkTicket.setTicketStatus(TicketStatus.FAILURE);
			parkTicket.setMessage(MessageConstants.LOT_FULL);
			return parkTicket;
		}
		if (lotMap.containsKey(vehicle.getVehicleNo())) {
			parkTicket.setTicketStatus(TicketStatus.FAILURE);
			parkTicket.setMessage(MessageConstants.LOT_CONTAIN_VEHICLE);
			return parkTicket;
		}

		// check for next vacant
		SLot slot = getNextAvailableSlot();

		boolean isParked = parkToSlot(slot, vehicle);
		if (isParked) {
			parkTicket.setLot(slot);
			parkTicket.setMessage(MessageFormat.format(MessageConstants.LOT_ALLOCATION, slot.getLotId()));
			parkTicket.setTicketStatus(TicketStatus.SUCCESS);
			totalFilled++;
		}
		return parkTicket;
	}

	private boolean parkToSlot(SLot slot, Vehicle vehicle) {
		slot.setVehicle(vehicle);
		slot.setStart(new Date());
		slot.setLotStatus(LotStatus.Filled);
		slot.setReserved(false);
		lotMap.put(vehicle.getVehicleNo(), slot);
		return true;
	}

	private SLot getNextAvailableSlot() {
		for (SLot slot : lot) {
			if (slot.getLotStatus() == LotStatus.Vacant)
				return slot;
		}
		return null;
	}

	public ParkTicket unparkVehicle(Vehicle vehicle) {
		// check whether the vehicle is in parking lot ?
		ParkTicket parkTicket = new ParkTicket();
		SLot slot = lotMap.get(vehicle.getVehicleNo());

		// Later it will have the cone of slot
		parkTicket.setTicketStatus(TicketStatus.UNPARKED);
		parkTicket.setEnd(new Date());
		parkTicket.setStart(slot.getStart());
		parkTicket.setLot(slot);

		// reset the slot
		slot.reset();
		parkTicket.setMessage(MessageFormat.format(MessageConstants.LOT_DEALLOCATION, slot.getLotId()));

		lotMap.remove(vehicle.getVehicleNo());
		totalFilled--;

		return parkTicket;
	}

	/**
	 * 
	 * @param vehicleNo
	 *            : Registration No of the vehicle
	 * @return
	 */
	public int getVehicleSlot(String vehicleNo) {
		SLot slot = lotMap.get(vehicleNo);
		if (slot == null)
			return -1;// vehicle is not parked

		return slot.getLotId();
	}

	public int[] getLotIdsByColor(String color) {
		List<SLot> slots = findAllSlotsByColor(color);
		int[] slotsIds = new int[slots.size()];
		int idx = 0;
		for (SLot slot : slots) {
			slotsIds[idx++] = slot.getLotId();
		}
		return slotsIds;
	}

	public String[] getParkedVehicleRegistrationsByColor(String color) {
		List<SLot> slots = findAllSlotsByColor(color);
		String[] slotsIds = new String[slots.size()];
		int idx = 0;
		for (SLot slot : slots) {
			slotsIds[idx++] = slot.getVehicle().getVehicleNo();
		}
		return slotsIds;
	}

	public List<SLot> findAllSlotsByColor(String color) {
		List<SLot> allSlots = new ArrayList<>(lotMap.values());
		List<SLot> desiredSlots = new ArrayList<>();
		for (SLot slot : allSlots) {
			if (slot.getVehicle().getColor().equalsIgnoreCase(color)) {
				desiredSlots.add(slot);
			}
		}
		return desiredSlots;
	}

}
