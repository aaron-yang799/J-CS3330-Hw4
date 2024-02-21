package edu.mu;

import edu.mu.vehicle.*;

public class Main {

	public static void main(String[] args) {
		VehicleManager instance = VehicleManager.getInstance();
		System.out.println(instance.initializeStock());
		
		
		System.out.println("Result of saveVehicleList: " + instance.saveVehicleList());
		
		System.out.println(instance.getNumberOfVehichlesByType(Car.class));
	}

}
