package edu.mu.vehicle;

public class MotorBike extends Vehicle{
	
	public MotorBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MotorBike(Vehicle vehicle) {
		super(vehicle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateMaintenaceCost(double distance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateFuelEfficiency(double distance, double fuelPrice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void startEngine() {
		// TODO Auto-generated method stub
		
	}
	
}
