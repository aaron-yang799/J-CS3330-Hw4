package edu.mu.vehicle;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class VehicleManager {
	private static VehicleManager instance = null;
	private final static String filePath = "files/vehicleList.csv";
	private static ArrayList<Vehicle> vehicles;
	
	private final static double distance = 300;
	private final static double fuelPrice = 3.25;
	
	private VehicleManager() {
		vehicles = new ArrayList<Vehicle>();
	}
	
	public static VehicleManager getInstance() {
		if(instance == null) {
			instance = new VehicleManager();
		}
		return instance;
	}
	
	public boolean initializeStock() {
		try{
			FileInputStream inputStream = new FileInputStream(filePath);
		
			Scanner scanner = new Scanner(inputStream);
			scanner.nextLine();
			while(scanner.hasNextLine()) {
				String[] fields = scanner.nextLine().split(",");
					switch(fields[0]) {
						case "Truck": 
							vehicles.add(new Truck(fields[1], fields[2], Long.parseLong(fields[3]), Double.parseDouble(fields[4]),
									VehicleColor.valueOf(fields[5]), FuelType.valueOf(fields[6]), Double.parseDouble(fields[7]), 
									Double.parseDouble(fields[8]), Integer.parseInt(fields[9]), Double.parseDouble(fields[10]), 
									StartMechanism.valueOf(fields[11])));
							break;
						case "SUV":
							vehicles.add(new SUV(fields[1], fields[2], Long.parseLong(fields[3]), Double.parseDouble(fields[4]),
									VehicleColor.valueOf(fields[5]), FuelType.valueOf(fields[6]), Double.parseDouble(fields[7]), 
									Double.parseDouble(fields[8]), Integer.parseInt(fields[9]), Double.parseDouble(fields[10]), 
									StartMechanism.valueOf(fields[11])));						
							break;
						case "Car":
							vehicles.add(new Car(fields[1], fields[2], Long.parseLong(fields[3]), Double.parseDouble(fields[4]),
									VehicleColor.valueOf(fields[5]), FuelType.valueOf(fields[6]), Double.parseDouble(fields[7]), 
									Double.parseDouble(fields[8]), Integer.parseInt(fields[9]), Double.parseDouble(fields[10]), 
									StartMechanism.valueOf(fields[11])));		
							break;
						case "MotorBike":
							vehicles.add(new MotorBike(fields[1], fields[2], Long.parseLong(fields[3]), Double.parseDouble(fields[4]),
									VehicleColor.valueOf(fields[5]), FuelType.valueOf(fields[6]), Double.parseDouble(fields[7]), 
									Double.parseDouble(fields[8]), Integer.parseInt(fields[9]), Double.parseDouble(fields[10]), 
									StartMechanism.valueOf(fields[11])));
							break;
						default:
							break;
					}
			}
		scanner.close();
		inputStream.close();
		return true;
		}
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean saveVehicleList() {
		
		try(FileWriter writer = new FileWriter(filePath)) {
			writer.write("Type,Model,Make,ModelYear,Price,Color,FuelType,Mileage,Mass,Cylinders,GasTankCapacity,StartType\n");
			
			String Type = "";
			for(int i = 0; i < vehicles.size(); i++) {
				switch (vehicles.get(i).getClass().getSimpleName()) {
	
				case "Car":
					Type = "Car";
					break;
				
				case "MotorBike":
					Type = "MotorBike";
					break;
				
				case "SUV":
					Type = "SUV";
					break;
				
				case "Truck":
					Type = "Truck";
					break;
				
				default:
					break;
				}
				
				writer.write(Type + "," + 
								vehicles.get(i).getBrand() + "," +
								vehicles.get(i).getMake() + "," +
								vehicles.get(i).getModelYear() + "," +
								String.format("%.0f", vehicles.get(i).getPrice()) + "," +
								vehicles.get(i).getColor() + "," +
								vehicles.get(i).getFuelType() + "," +
								String.format("%.0f", vehicles.get(i).getMileage()) + "," +
								vehicles.get(i).getMass() + "," +
								vehicles.get(i).getCylinders() + "," +
								String.format("%.0f", vehicles.get(i).getGasTankCapacity()) + "," +
								vehicles.get(i).getStartType() + "\n"
								);
			}
			writer.close();
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	private boolean isVehicleType(Vehicle v, Class clazz) {
		return clazz.isInstance(v);
	}
	
	public int getNumberOfVehichlesByType(Class clazz) {
		int count = 0;
		for(Vehicle vehicle : vehicles) {
			if(isVehicleType(vehicle, clazz)) {
				++count;
			}
		}
		return count;
	}
	
	public boolean removeVehicle(Vehicle vehicle)
	{
		for(int i = 0; i < vehicles.size(); i++)
		{
			if(vehicles.get(i) == vehicle)
			{
				vehicles.remove(i);
				return true;
			}	
		}
		return false;
	}
	
	public boolean addVehicle(Vehicle vehicle)
	{
		if(vehicle == null)
		{
			return false;
		}
		
		vehicles.add(vehicle);
		return true;
		
	}
	
	public void displayAllCarInformation() {
		int amountOfCars = getNumberOfVehichlesByType(Car.class);
		int curAmountOfCars = 0;
		
		
		
		for(Vehicle curCar: vehicles) {
			if(curCar instanceof Car) {
				curAmountOfCars += 1;
				System.out.println(curCar);
				System.out.println("	Distance: " + curCar.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curCar.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		int missingCars = amountOfCars - curAmountOfCars;
		
		if(amountOfCars != curAmountOfCars) {
			System.out.println("Could not find " + missingCars + " Cars.");
		}
		
	}
	
	public void displayAllTruckInformation() {
		int amountOfTrucks = getNumberOfVehichlesByType(Truck.class);
		int curAmountOfTrucks = 0;
		
		for(Vehicle curTruck: vehicles) {
			if(curTruck instanceof Car) {
				curAmountOfTrucks += 1;
				System.out.println(curTruck);
				System.out.println("	Distance: " + curTruck.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curTruck.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		int missingTrucks = amountOfTrucks - curAmountOfTrucks;
		
		if(amountOfTrucks != curAmountOfTrucks) {
			System.out.println("Could not find " + missingTrucks + " Trucks.");
		}
	}
	
	public void displayAllSUVInformation() {
		int amountOfSUV = getNumberOfVehichlesByType(SUV.class);
		int curAmountOfSUV = 0;
		
		for(Vehicle curSUV: vehicles) {
			if(curSUV instanceof Car) {
				curAmountOfSUV += 1;
				System.out.println(curSUV);
				System.out.println("	Distance: " + curSUV.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curSUV.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		int missingSUV = amountOfSUV - curAmountOfSUV;
		
		if(amountOfSUV != curAmountOfSUV) {
			System.out.println("Could not find " + missingSUV + " SUVs.");
		}
	}
	
	public void displayAllMotorBikeInformation() {
		int amountOfMotorBike = getNumberOfVehichlesByType(MotorBike.class);
		int curAmountOfMotorBike = 0;
		
		for(Vehicle curMotorBike: vehicles) {
			if(curMotorBike instanceof MotorBike) {
				curAmountOfMotorBike += 1;
				System.out.println(curMotorBike);
				System.out.println("	Distance: " + curMotorBike.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curMotorBike.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		int missingMotorBike = amountOfMotorBike - curAmountOfMotorBike;
		
		if(amountOfMotorBike != curAmountOfMotorBike) {
			System.out.println("Could not find " + missingMotorBike + " MotorBikes.");
		}
	}
	
	public void displayVehicleInformation(Vehicle v) {
		for (Vehicle vehicle: vehicles) {
			if(vehicle.equals(v)) {
				System.out.println(vehicle);
				System.out.println("	Distance: " + vehicle.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + vehicle.calculateFuelEfficiency(distance, fuelPrice));
				return;
			}
		}
		System.out.println("Could not find Vehicle specified.");
	}
	
	public void displayAllVehicleInformation() {
		if(vehicles.size() == 0) {
			System.out.println("List is empty.");
			return;
		}
		
		for(Vehicle vehicle: vehicles) {
			System.out.println(vehicle);
			System.out.println("	Distance: " + vehicle.calculateMaintenaceCost(distance));
			System.out.println("	FuelEff: " + vehicle.calculateFuelEfficiency(distance, fuelPrice));
		}
	}
	
	public double getAverageFuelEfficiencyOfSUVs(double distance, double fuelPrice) {
		double total = 0;
		int count = 0;
		for(Vehicle vehicle : vehicles) {
			if(isVehicleType(vehicle, SUV.class)) {
				total += vehicle.calculateFuelEfficiency(distance, fuelPrice);
				++count;
			}
		}
		return total/count;
	}
	
	public ArrayList<Vehicle>getVehicleWithHighestFuelEfficiency(double distance, double fuelPrice)
	{
		ArrayList<Vehicle>results = new ArrayList<Vehicle>();
		results.add(vehicles.get(0));
		for(Vehicle vehicle : vehicles)
		{
			//System.out.println(vehicle.brand + " " + vehicle.calculateFuelEfficiency(distance, fuelPrice));
			
			if(vehicle.calculateFuelEfficiency(distance, fuelPrice) > results.get(0).calculateFuelEfficiency(distance, fuelPrice))
			{
				//System.out.println(vehicle.calculateFuelEfficiency(distance, fuelPrice));
				results.clear();
				results.add(vehicle);
			}
			else if(vehicle.calculateFuelEfficiency(distance, fuelPrice) == results.get(0).calculateFuelEfficiency(distance, fuelPrice))
			{
				results.add(vehicle);
			}
			else
			{
				continue;
			}
		}
		//System.out.println(results);
		return results;
		
	}
	
	public ArrayList<Vehicle>getVehicleWithLowestFuelEfficiency(double distance, double fuelPrice)
	{
		ArrayList<Vehicle>results = new ArrayList<Vehicle>();
		results.add(vehicles.get(0));
		for(Vehicle vehicle : vehicles)
		{
			//System.out.println(vehicle.brand + " " + vehicle.calculateFuelEfficiency(distance, fuelPrice));
			
			if(vehicle.calculateFuelEfficiency(distance, fuelPrice) < results.get(0).calculateFuelEfficiency(distance, fuelPrice))
			{
				//System.out.println(vehicle.calculateFuelEfficiency(distance, fuelPrice));
				results.clear();
				results.add(vehicle);
			}
			else if(vehicle.calculateFuelEfficiency(distance, fuelPrice) == results.get(0).calculateFuelEfficiency(distance, fuelPrice))
			{
				results.add(vehicle);
			}
			else
			{
				continue;
			}
		}
		//System.out.println(results);
		return results;
		
	}
	
	public Vehicle getVehicleWithHighestMaintenanceCost(double distance) {
		
		int highestIndex = 0;
		
		for(int i = 1; i < vehicles.size(); i++) {
			if(vehicles.get(i).calculateMaintenaceCost(distance) > vehicles.get(highestIndex).calculateMaintenaceCost(distance)) {
				//System.out.println(vehicles.get(i).getMake() + ": " + vehicles.get(i).calculateMaintenaceCost(distance));
				highestIndex = i;
			}
		}
		
		ArrayList<Vehicle>highestCostList = new ArrayList<Vehicle>();
		Vehicle highestCostVehicle = vehicles.get(highestIndex);
		
		//System.out.println("Highest Cost Vehicle: " + highestCostVehicle.getMake());
		
		for(Vehicle vehicle: vehicles) {
			//System.out.println(vehicle.getMake() + ": " + vehicle.calculateMaintenaceCost(distance));
			if(vehicle.calculateMaintenaceCost(distance) == highestCostVehicle.calculateMaintenaceCost(distance)) {
				//System.out.println("\nAdded Vehicle to highestList:\n" + vehicle.getMake() + " " + vehicle.calculateMaintenaceCost(distance) + "\n");
				highestCostList.add(vehicle);
			}
		}
		
		Random rand = new Random();
		int randomIndex = rand.nextInt(highestCostList.size());
		Vehicle randVehicle = highestCostList.get(randomIndex);

		return randVehicle;
		}
	
	public Vehicle getVehicleWithLowestMaintenanceCost(double distance) {
		
		int lowestIndex = 0;
		
		for(int i = 1; i < vehicles.size(); i++) {
			if(vehicles.get(i).calculateMaintenaceCost(distance) < vehicles.get(lowestIndex).calculateMaintenaceCost(distance)) {
				//System.out.println(vehicles.get(i).getMake() + ": " + vehicles.get(i).calculateMaintenaceCost(distance));
				lowestIndex = i;
			}
		}
		
		ArrayList<Vehicle>lowestCostList = new ArrayList<Vehicle>();
		Vehicle lowestCostVehicle = vehicles.get(lowestIndex);
		
		//System.out.println("Lowest Cost Vehicle: " + lowestCostVehicle.getMake());
		
		for(Vehicle vehicle: vehicles) {
			//System.out.println(vehicle.getMake() + ": " + vehicle.calculateMaintenaceCost(distance));
			if(vehicle.calculateMaintenaceCost(distance) == lowestCostVehicle.calculateMaintenaceCost(distance)) {
				//System.out.println("\nAdded Vehicle to lowestCostList:\n" + vehicle.getMake() + " " + vehicle.calculateMaintenaceCost(distance) + "\n");
				lowestCostList.add(vehicle);
			}
		}
		
		Random rand = new Random();
		int randomIndex = rand.nextInt(lowestCostList.size());
		Vehicle randVehicle = lowestCostList.get(randomIndex);

		return randVehicle;
		}



}
