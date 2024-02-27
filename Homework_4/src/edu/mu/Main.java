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
		
//			Testing for getVehicleWithHighestMaintenanceCost and getVehicleWithLowestMaintenanceCost
//		Truck Swag = new Truck("Toyota", "Swag", (long)2000, (double)100000, VehicleColor.WHITE, FuelType.DIESEL, (double)50000, (double)2, 12, (double)25, StartMechanism.KEYSTART);
//		Truck John = new Truck("Toyota", "John", (long)2000, (double)100000, VehicleColor.WHITE, FuelType.DIESEL, (double)50000, (double)2, 12, (double)25, StartMechanism.KEYSTART);
//		Truck Awesome = new Truck("Toyota", "Awesome", (long)2000, (double)100000, VehicleColor.WHITE, FuelType.DIESEL, (double)50000, (double)2, 12, (double)25, StartMechanism.KEYSTART);
//
//		Car Quagmire = new Car("Honda", "Quagmire", (long)2024, (double)18000, VehicleColor.GRAY, FuelType.ELECTRIC, (double)50000, (double).25, 6, (double)14, StartMechanism.PUSHSTART);
//		Car Bicycle = new Car("Honda", "Bicycle", (long)2024, (double)18000, VehicleColor.GRAY, FuelType.ELECTRIC, (double)50000, (double).25, 6, (double)14, StartMechanism.PUSHSTART);
//		Car The = new Car("Honda", "The", (long)2024, (double)18000, VehicleColor.GRAY, FuelType.ELECTRIC, (double)50000, (double).25, 6, (double)14, StartMechanism.PUSHSTART);	
//		
//		instance.addVehicle(Swag);
//		instance.addVehicle(John);
//		instance.addVehicle(Awesome);
//		instance.addVehicle(Quagmire);
//		instance.addVehicle(Bicycle);
//		instance.addVehicle(The);
//		System.out.println("\n\n\ngetVehicleWithHighestMaintenanceCost test");
//		System.out.println(instance.getVehicleWithHighestMaintenanceCost(100));
//		
//		System.out.println("\n\n\ngetVehicleWithLowestMaintenanceCost test");
//		System.out.println(instance.getVehicleWithLowestMaintenanceCost(100));
	}

}
