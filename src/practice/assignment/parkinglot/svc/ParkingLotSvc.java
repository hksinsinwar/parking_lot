package practice.assignment.parkinglot.svc;

import practice.assignment.parkinglot.domain.ParkTicket;
import practice.assignment.parkinglot.domain.AVehicle;

public interface ParkingLotSvc {
	void initParkingLot(int capcity);

	ParkTicket parkVehicle(AVehicle vehicle);

	String[] getParkedVehicleNosByColor(String color);

	int[] getParkedLotIdsByColor(String color);

	int findSlotNoforRegistration(String registrationNo);

	ParkTicket leaveSlot(int lotId);

	ParkTicket unparkVehicle(AVehicle vehicle);

}
