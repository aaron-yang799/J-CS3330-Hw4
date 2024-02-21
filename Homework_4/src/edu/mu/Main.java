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
		System.out.println("\n");

		
		System.out.println("Result of saveVehicleList: " + instance.saveVehicleList());
		
		System.out.println("Result of getNumberOfVehiclesByType: " + instance.getNumberOfVehichlesByType(Car.class));
		
	}

}
