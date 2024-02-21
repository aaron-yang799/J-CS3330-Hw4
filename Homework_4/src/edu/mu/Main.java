package edu.mu;

import edu.mu.vehicle.VehicleManager;

public class Main {

	public static void main(String[] args) {
		VehicleManager instance = VehicleManager.getInstance();
		System.out.println(instance.initializeStock());
		
		
		System.out.println("Result of saveVehicleList: " + instance.saveVehicleList());
		
	}

}
