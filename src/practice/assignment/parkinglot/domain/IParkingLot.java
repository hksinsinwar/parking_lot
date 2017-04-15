package practice.assignment.parkinglot.domain;

public interface IParkingLot {

	ParkTicket parkVehicle(AVehicle vehicle);

	ParkTicket unparkVehicle(AVehicle vehicle);

	ParkTicket leaveSlot(int slotId);

	int getVehicleSlot(String vehicleNo);

	int[] getLotIdsByColor(String color);

	String[] getParkedVehicleRegistrationsByColor(String color);

}
