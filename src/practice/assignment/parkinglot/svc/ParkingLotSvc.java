package practice.assignment.parkinglot.svc;

import practice.assignment.parkinglot.domain.ParkTicket;
import practice.assignment.parkinglot.domain.Vehicle;

public interface ParkingLotSvc {
	void initParkingLot(int capcity);

	ParkTicket parkVehicle(Vehicle vehicle);

	String[] getParkedVehicleNosByColor(String color);

	int[] getParkedLotIdsByColor(String color);

}
