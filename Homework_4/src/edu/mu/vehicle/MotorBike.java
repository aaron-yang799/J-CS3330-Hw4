package edu.mu.vehicle;

import java.time.Year;

public class MotorBike extends Vehicle{
	
	//default constructor using super key word
	public MotorBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	//copy constructor using super key word
	public MotorBike(Vehicle vehicle) {
		super(vehicle);
		// TODO Auto-generated constructor stub
	}
	
	//parameterized constructor using super key word
	public MotorBike(String brand, String make, long modelYear, double price, VehicleColor color, FuelType fuelType,
			double mileage, double mass, int cylinders, double gasTankCapacity, StartMechanism startType) {
		super(brand, make, modelYear, price, color, fuelType, mileage, mass, cylinders, gasTankCapacity, startType);
		// TODO Auto-generated constructor stub
	}

	//calculateMaintenanceCost:
	//	Uses formula: maintenanceCost=distance * mass * (currentYear-modelYear) * cylinders * 0.0002
	//	to calculate maintenance cost over a distance.
	@Override
	public double calculateMaintenaceCost(double distance) {
		return distance * mass * (Year.now().getValue() - modelYear) * cylinders * 0.0002;
	}

	//calculateFuelEfficiency:
	//	Uses formula: fuelEfficiency = cylinders * gasTankCapacity * fuelPrice / distance * 0.001
	// to calculate fuel efficiency over a distance.
	@Override
	public double calculateFuelEfficiency(double distance, double fuelPrice) {
		return cylinders * gasTankCapacity * fuelPrice / distance * 0.001;
	}

	//startEngine:
	//	prints vehicle start type
	@Override
	public void startEngine() {
		System.out.println(startType);
	}
	
}
