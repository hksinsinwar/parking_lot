package practice.assignment.parkinglot.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import practice.assignment.parkinglot.constants.MessageConstants;
import practice.assignment.parkinglot.domain.ParkTicket.TicketStatus;
import practice.assignment.parkinglot.domain.Slot.LotStatus;

public class ParkingLot implements IParkingLot{

	private int capacity;

	private int totalFilled;

	private Map<String, Slot> lotMap;
	private Slot[] lot;

	public ParkingLot(int capacity) {
		this.capacity = capacity;
		this.lotMap = new HashMap<>();
		this.lot = new Slot[capacity];
		this.totalFilled = 0;

		initParkingLot(lot, capacity);

	}

	private void initParkingLot(Slot[] lot2, int capacity2) {
		for (int i = 0; i < capacity2; i++) {
			Slot sLot = new Slot();
			sLot.setLotId(i + 1); // lotId is 1 based instead of 0
			lot2[i] = sLot;
		}
	}
	@Override
	public ParkTicket parkVehicle(AVehicle vehicle) {
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
		Slot slot = getNextAvailableSlot();

		boolean isParked = parkToSlot(slot, vehicle);
		if (isParked) {
			parkTicket.setLot(slot);
			parkTicket.setMessage(MessageFormat.format(MessageConstants.LOT_ALLOCATION, slot.getLotId()));
			parkTicket.setTicketStatus(TicketStatus.SUCCESS);
			totalFilled++;
		}
		return parkTicket;
	}

	private boolean parkToSlot(Slot slot, AVehicle vehicle) {
		slot.setVehicle(vehicle);
		slot.setStart(new Date());
		slot.setLotStatus(LotStatus.Filled);
		slot.setReserved(false);
		lotMap.put(vehicle.getVehicleNo(), slot);
		return true;
	}

	private Slot getNextAvailableSlot() {
		for (Slot slot : lot) {
			if (slot.getLotStatus() == LotStatus.Vacant)
				return slot;
		}
		return null;
	}
	@Override
	public ParkTicket unparkVehicle(AVehicle vehicle) {
		// check whether the vehicle is in parking lot ?
		Slot slot = lotMap.get(vehicle.getVehicleNo());
		String vehicleNo = vehicle.getVehicleNo();
		return deAllocateParkingSlot(slot, vehicleNo);
	}
	@Override
	public ParkTicket leaveSlot(int slotId) {
		// check whether the vehicle is in parking lot ?
		Slot slot = lot[slotId];
		String vehicleNo = slot.getVehicle().getVehicleNo();
		
		return deAllocateParkingSlot(slot, vehicleNo);
	}

	private ParkTicket deAllocateParkingSlot(Slot slot, String vehicleNo) {
		ParkTicket parkTicket = new ParkTicket();
			
		// Later it will have the cone of slot
		parkTicket.setTicketStatus(TicketStatus.UNPARKED);
		parkTicket.setEnd(new Date());
		parkTicket.setStart(slot.getStart());
		parkTicket.setLot(slot);
		parkTicket.setMessage(MessageFormat.format(MessageConstants.LOT_DEALLOCATION, slot.getLotId()));
		

		// reset the slot
		slot.reset();

		lotMap.remove(vehicleNo);
		totalFilled--;

		return parkTicket;
	}

	/**
	 * 
	 * @param vehicleNo
	 *            : Registration No of the vehicle
	 * @return
	 */
	@Override
	public int getVehicleSlot(String vehicleNo) {
		Slot slot = lotMap.get(vehicleNo);
		if (slot == null)
			return -1;// vehicle is not parked

		return slot.getLotId();
	}
	@Override
	public int[] getLotIdsByColor(String color) {
		List<Slot> slots = findAllSlotsByColor(color);
		int[] slotsIds = new int[slots.size()];
		int idx = 0;
		for (Slot slot : slots) {
			slotsIds[idx++] = slot.getLotId();
		}
		return slotsIds;
	}
	@Override
	public String[] getParkedVehicleRegistrationsByColor(String color) {
		List<Slot> slots = findAllSlotsByColor(color);
		String[] slotsIds = new String[slots.size()];
		int idx = 0;
		for (Slot slot : slots) {
			slotsIds[idx++] = slot.getVehicle().getVehicleNo();
		}
		return slotsIds;
	}

	private List<Slot> findAllSlotsByColor(String color) {
		List<Slot> allSlots = new ArrayList<>(lotMap.values());
		List<Slot> desiredSlots = new ArrayList<>();
		for (Slot slot : allSlots) {
			if (slot.getVehicle().getColor().equalsIgnoreCase(color)) {
				desiredSlots.add(slot);
			}
		}
		return desiredSlots;
	}

}
