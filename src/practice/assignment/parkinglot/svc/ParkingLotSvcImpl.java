package practice.assignment.parkinglot.svc;

import org.junit.Assert;

import practice.assignment.parkinglot.constants.MessageConstants;
import practice.assignment.parkinglot.domain.IParkingLot;
import practice.assignment.parkinglot.domain.ParkTicket;
import practice.assignment.parkinglot.domain.ParkingLot;
import practice.assignment.parkinglot.domain.AVehicle;
/**
 * 
 *
 */
public class ParkingLotSvcImpl implements ParkingLotSvc {

	private IParkingLot parkingLot;

	@Override
	public void initParkingLot(int capcity) {
		parkingLot = new ParkingLot(capcity);
	}

	@Override
	public ParkTicket parkVehicle(AVehicle vehicle) {
		Assert.assertNotNull(MessageConstants.LOT_UNINITIALIZED, parkingLot);
		return parkingLot.parkVehicle(vehicle);
	}

	@Override
	public String[] getParkedVehicleNosByColor(String color) {
		Assert.assertNotNull(MessageConstants.LOT_UNINITIALIZED, parkingLot);
		return parkingLot.getParkedVehicleRegistrationsByColor(color);
	}

	@Override
	public int[] getParkedLotIdsByColor(String color) {
		Assert.assertNotNull(MessageConstants.LOT_UNINITIALIZED, parkingLot);

		return parkingLot.getLotIdsByColor(color);
	}

	@Override
	public int findSlotNoforRegistration(String registrationNo) {
		Assert.assertNotNull(MessageConstants.LOT_UNINITIALIZED, parkingLot);
		return parkingLot.getVehicleSlot(registrationNo);
	}

	@Override
	public ParkTicket leaveSlot(int lotId) {
		return parkingLot.leaveSlot(lotId);
	}

	@Override
	public ParkTicket unparkVehicle(AVehicle vehicle) {
		return parkingLot.unparkVehicle(vehicle);
	}

}
