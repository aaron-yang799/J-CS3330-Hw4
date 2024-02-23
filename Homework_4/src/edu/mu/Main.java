package edu.mu;

import edu.mu.vehicle.*;

public class Main {

	public static void main(String[] args) {
		
		VehicleManager instance = VehicleManager.getInstance();
				
		// Read vehicle data from the vehcileList.csv file and initialize objects.
		System.out.println(instance.initializeStock());
		System.out.println("\n");

		
		// Display all vehicle information.
		System.out.println("Reulst of displayAllVehicleInformation: \n");
		instance.displayAllVehicleInformation();
		System.out.println("\n");
		
		// Display all car information.
		System.out.println("Reulst of displayAllCarInformation: \n");
		instance.displayAllCarInformation();
		System.out.println("\n");

		
		// Display all motorbike information.
		System.out.println("Reulst of displayAllMotorBikeInformation: \n");
		instance.displayAllMotorBikeInformation();
//		System.out.println("\n");
//
//		
//		System.out.println("Result of saveVehicleList: " + instance.saveVehicleList());
//		
//		System.out.println("Result of getNumberOfVehiclesByType: " + instance.getNumberOfVehichlesByType(Car.class));
//		
//		System.out.println("Result of getAverageFuelEfficencyOfSUV's: " + instance.getAverageFuelEfficiencyOfSUVs(400, 10.00));
//		
//		System.out.println("\n");
//		
//		Car newCar = new Car("Ford", "Edge", (long)2010, (double)8000, VehicleColor.RED, FuelType.HYBRID, (double)30000, (double)3000, 8, (double)30, StartMechanism.KEYSTART);
//		System.out.println("Result of addVehicle: " + instance.addVehicle(newCar));
//		System.out.println("\n");
//		instance.displayAllVehicleInformation();
//		System.out.println("\n");
//
//		
//		System.out.println("Result of removeVehicle: " + instance.removeVehicle(newCar));
//		System.out.println("\n");
//		instance.displayAllVehicleInformation();
//		System.out.println("\n");
//		
//		System.out.println("Result of getVehicleWithHighestFuelEfficiency");
//		System.out.println(instance.getVehicleWithHighestFuelEfficiency(100, 5));
//		System.out.println("\n");
//		
//		System.out.println("Result ofgetVehicleWithLowestFuelEfficiency");
//		System.out.println(instance.getVehicleWithLowestFuelEfficiency(100, 5));

	}

}
