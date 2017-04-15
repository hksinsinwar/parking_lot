package practice.assignment.parkinglot.domain;

public abstract class AVehicle {
	
	private String color;
	private String vehicleNo;
	private String owner;
	
	private String type;

	public AVehicle(String color,String vehicleNo,String owner,String type){
		this.color =color;
		this.owner = owner;
		this.type = type;
		this.vehicleNo= vehicleNo;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
}
