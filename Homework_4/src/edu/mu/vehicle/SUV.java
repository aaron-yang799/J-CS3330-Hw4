package edu.mu.vehicle;

public class SUV extends Vehicle{

	public SUV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SUV(Vehicle vehicle) {
		super(vehicle);
		// TODO Auto-generated constructor stub
	}
	
	public SUV(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
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
