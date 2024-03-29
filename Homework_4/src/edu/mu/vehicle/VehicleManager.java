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
			//creates a new input stream with with a path to vehicleList.csv
			FileInputStream inputStream = new FileInputStream(filePath);
		
			//creates a scanner object
			Scanner scanner = new Scanner(inputStream);
			//skips the first line of the .csv
			scanner.nextLine();
			//iterates through the .csv and creates new objects based on the type specified in the .csv (Truck, SUV, Car, Motorbike)
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
		//closes the scanner
		scanner.close();
		//closes the input stream
		inputStream.close();
		return true;
		}
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean saveVehicleList() {
		
		//open the file we want to edit using a FileWriter object to overwrite content in the file and write the first line
		try(FileWriter writer = new FileWriter(filePath)) {
			writer.write("Type,Model,Make,ModelYear,Price,Color,FuelType,Mileage,Mass,Cylinders,GasTankCapacity,StartType\n");
			
			//loop through vehicles array list to write vehicle information to the file one by one
			String Type = "";
			for(int i = 0; i < vehicles.size(); i++) {
				//find out the type of vehicle to write to the file
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
				
				//write the rest of the information about the vehicle to the file using getters
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
			//close the file and return true after all information has been written
			writer.close();
			return true;
			
		} catch (IOException e) {
			//if error in writing to the file, print stack trace and return false
			e.printStackTrace();
			return false;
		}
	}
	
	//isVehicleType:
	//	takes class parameter and checks if the Vehicle object passed is an instance of that class using isInstance method.
	//	It is worth noting that via the method prototypes given in the assignment, the vehicle object could be tested as an instance of any object under the sun.
	private boolean isVehicleType(Vehicle v, Class clazz) {
		return clazz.isInstance(v);
	}
	
	//getNumberOfVehiclesByType:
	//	Is passed a class (again, could be literally anything) and uses a for-each loop to test if every vehicle is an instance of that class using the isVehicleType method.
	//	If it is an instance of that class, then it will be tallied.
	//	Final tally is returned to the user.
	public int getNumberOfVehichlesByType(Class clazz) {
		int count = 0;
		for(Vehicle vehicle : vehicles) {
			if(isVehicleType(vehicle, clazz)) {
				++count;
			}
		}
		return count;
	}
	
	//removeVehicle:
	//	Removes passed in Vehicle from the VehicleManager vehicles ArrayList field.
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
	
	//addVehicle:
	// Adds passed in new Vehicle object to the VehicleManager vehicles ArrayList field.
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
		//utilizes getNumberOfVehiclesByType to know the correct amount of Cars to be displayed
		int amountOfCars = getNumberOfVehichlesByType(Car.class);
		//variable to count the current amount of Cars
		int curAmountOfCars = 0;
		
		//iterates through arrayList vehicles, looking only at Car objects using instanceof.
		//also increments curAmountOfCars when a Car object is displayed
		//displays Car information including Distance and Fuel Efficiency
		for(Vehicle curCar: vehicles) {
			if(curCar instanceof Car) {
				curAmountOfCars += 1;
				System.out.println(curCar);
				System.out.println("	Distance: " + curCar.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curCar.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		//calculates the amount of missing Cars
		int missingCars = amountOfCars - curAmountOfCars;
		
		//checks if the amountOfCars is equal to the amount displayed
		//if not, the amount missing is printed
		if(amountOfCars != curAmountOfCars) {
			System.out.println("Could not find " + missingCars + " Cars.");
		}
		
	}
	
	public void displayAllTruckInformation() {
		//utilizes getNumberOfVehiclesByType to know the correct amount of Trucks to be displayed
		int amountOfTrucks = getNumberOfVehichlesByType(Truck.class);
		//variable to count the current amount of Trucks
		int curAmountOfTrucks = 0;
		
		//iterates through arrayList vehicles, looking only at Truck objects using instanceof.
		//also increments curAmountOfTrucks when a Truck object is displayed
		//displays Truck information including Distance and Fuel Efficiency
		for(Vehicle curTruck: vehicles) {
			if(curTruck instanceof Car) {
				curAmountOfTrucks += 1;
				System.out.println(curTruck);
				System.out.println("	Distance: " + curTruck.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curTruck.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		//calculates the amount of missing Trucks
		int missingTrucks = amountOfTrucks - curAmountOfTrucks;
		
		//checks if the amountOfTrucks is equal to the amount displayed
		//if not, the amount missing is printed
		if(amountOfTrucks != curAmountOfTrucks) {
			System.out.println("Could not find " + missingTrucks + " Trucks.");
		}
	}
	
	public void displayAllSUVInformation() {
		//utilizes getNumberOfVehiclesByType to know the correct amount of SUVs to be displayed
		int amountOfSUV = getNumberOfVehichlesByType(SUV.class);
		//variable to count the current amount of SUVs
		int curAmountOfSUV = 0;
		
		//iterates through arrayList vehicles, looking only at SUV objects using instanceof.
		//also increments curAmountOfSUV when a SUV object is displayed
		//displays SUV information including Distance and Fuel Efficiency
		for(Vehicle curSUV: vehicles) {
			if(curSUV instanceof Car) {
				curAmountOfSUV += 1;
				System.out.println(curSUV);
				System.out.println("	Distance: " + curSUV.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curSUV.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		//calculates the amount of missing SUVs
		int missingSUV = amountOfSUV - curAmountOfSUV;
		
		//checks if the amountOfSUV is equal to the amount displayed
		//if not, the amount missing is printed
		if(amountOfSUV != curAmountOfSUV) {
			System.out.println("Could not find " + missingSUV + " SUVs.");
		}
	}
	
	public void displayAllMotorBikeInformation() {
		//utilizes getNumberOfVehiclesByType to know the correct amount of MotorBikes to be displayed
		int amountOfMotorBike = getNumberOfVehichlesByType(MotorBike.class);
		//variable to count the current amount of MotorBikes
		int curAmountOfMotorBike = 0;
		
		//iterates through arrayList vehicles, looking only at MotorBike objects using instanceof.
		//also increments curAmountOfMotorBike when a MotorBike object is displayed
		//displays MotorBike information including Distance and Fuel Efficiency
		for(Vehicle curMotorBike: vehicles) {
			if(curMotorBike instanceof MotorBike) {
				curAmountOfMotorBike += 1;
				System.out.println(curMotorBike);
				System.out.println("	Distance: " + curMotorBike.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + curMotorBike.calculateFuelEfficiency(distance, fuelPrice));
			}
		}
		
		//calculates the amount of missing MotorBikes
		int missingMotorBike = amountOfMotorBike - curAmountOfMotorBike;
		
		//checks if the amountOfMotorBike is equal to the amount displayed
		//if not, the amount missing is printed
		if(amountOfMotorBike != curAmountOfMotorBike) {
			System.out.println("Could not find " + missingMotorBike + " MotorBikes.");
		}
	}
	
	public void displayVehicleInformation(Vehicle v) {
		//iterates through vehicles arrrayList until the provided vehicle is found
		//then displays all information of the vehicle including Distance and Fuel Efficiency
		for (Vehicle vehicle: vehicles) {
			if(vehicle.equals(v)) {
				System.out.println(vehicle);
				System.out.println("	Distance: " + vehicle.calculateMaintenaceCost(distance));
				System.out.println("	FuelEff: " + vehicle.calculateFuelEfficiency(distance, fuelPrice));
				return;
			}
		}
		//if the vehicle is not found a message is printed
		System.out.println("Could not find Vehicle specified.");
	}
	
	public void displayAllVehicleInformation() {
		//checks if the vehicle arrayList is empty
		if(vehicles.size() == 0) {
			System.out.println("List is empty.");
			return;
		}
		
		//iterates through all vehicles in arrayList vehicles, displays the information for each vehicle
		//including Distance and Fuel Efficiency
		for(Vehicle vehicle: vehicles) {
			System.out.println(vehicle);
			System.out.println("	Distance: " + vehicle.calculateMaintenaceCost(distance));
			System.out.println("	FuelEff: " + vehicle.calculateFuelEfficiency(distance, fuelPrice));
		}
	}
	
	//getAverageFuelEfficiency:
	//	A for-each loop is used to test every member of the vehicles ArrayList for SUV types.
	//	SUV type fuel efficiencies are then totaled and used to calculate the mean using the count of SUVS tallied within the loop.
	public double getAverageFuelEfficiencyOfSUVs(double distance, double fuelPrice) {
		double total = 0;
		int count = 0;
		//for each block cycling through vehicles, finding all SUV types using isVehicleType method and adding total fuel economy as well as keeping track of number of SUVS
		for(Vehicle vehicle : vehicles) {
			if(isVehicleType(vehicle, SUV.class)) {
				total += vehicle.calculateFuelEfficiency(distance, fuelPrice);
				++count;
			}
		}
		//The mean is calculated
		return total/count;
	}
	
	
	//getVehicleWithHighestFuelEfficiency:
	//	Calculates the fuel efficiencies for each vehicle in the Vehicle ArrayList and returns the vehicle with the highest fuel efficiency, as an
	//	Vehicle ArrayList with one element. If there are multiple Vehicles with the same highest fuel efficiency then all Vehicles with that fuel
	//	efficiency are all added to the Vehicle ArrayList which ends up getting returned to the function caller.
	public ArrayList<Vehicle>getVehicleWithHighestFuelEfficiency(double distance, double fuelPrice)
	{
		ArrayList<Vehicle>results = new ArrayList<Vehicle>();
		results.add(vehicles.get(0));
		for(Vehicle vehicle : vehicles)
		{
			
			if(vehicle.calculateFuelEfficiency(distance, fuelPrice) > results.get(0).calculateFuelEfficiency(distance, fuelPrice))
			{
				
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
		
		return results;
		
	}
	
	//getVehicleWithLowestFuelEfficiency:
	//	Calculates the fuel efficiencies for each vehicle in the Vehicle ArrayList and returns the vehicle with the lowest fuel efficiency, as an
	//	Vehicle ArrayList with one element. If there are multiple Vehicles with the same lowest fuel efficiency then all Vehicles with that fuel
	//	efficiency are all added to the Vehicle ArrayList which ends up getting returned to the function caller
	public ArrayList<Vehicle>getVehicleWithLowestFuelEfficiency(double distance, double fuelPrice)
	{
		ArrayList<Vehicle>results = new ArrayList<Vehicle>();
		results.add(vehicles.get(0));
		for(Vehicle vehicle : vehicles)
		{
			
			if(vehicle.calculateFuelEfficiency(distance, fuelPrice) < results.get(0).calculateFuelEfficiency(distance, fuelPrice))
			{
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
		return results;
	}
	
	public Vehicle getVehicleWithHighestMaintenanceCost(double distance) {
		
		//create variable to track index of the vehicle with the highest maintenance cost
		int highestIndex = 0;
		
		//find the vehicle with the highest maintenance cost and store index into highestIndex variable
		for(int i = 1; i < vehicles.size(); i++) {
			if(vehicles.get(i).calculateMaintenaceCost(distance) > vehicles.get(highestIndex).calculateMaintenaceCost(distance)) {
				//System.out.println(vehicles.get(i).getMake() + ": " + vehicles.get(i).calculateMaintenaceCost(distance));
				highestIndex = i;
			}
		}
		
		//initialize new array list to store all vehicles with the same maintenance cost
		ArrayList<Vehicle>highestCostList = new ArrayList<Vehicle>();
		//instantiate new vehicle object to compare maintenance costs
		Vehicle highestCostVehicle = vehicles.get(highestIndex);
		
		//System.out.println("Highest Cost Vehicle: " + highestCostVehicle.getMake());
		
		//iterate through vehicle list to find vehicles with the same maintenance cost
		//if there exists a vehicle with the same maintenance cost as the highest maintenance cost, add it to highestCostList
		for(Vehicle vehicle: vehicles) {
			//System.out.println(vehicle.getMake() + ": " + vehicle.calculateMaintenaceCost(distance));
			if(vehicle.calculateMaintenaceCost(distance) == highestCostVehicle.calculateMaintenaceCost(distance)) {
				//System.out.println("\nAdded Vehicle to highestList:\n" + vehicle.getMake() + " " + vehicle.calculateMaintenaceCost(distance) + "\n");
				highestCostList.add(vehicle);
			}
		}
		
		//randomly return an object from highestCostList based on the size of HighestCostList so each object has the same chance of being returned 
		Random rand = new Random();
		int randomIndex = rand.nextInt(highestCostList.size());
		Vehicle randVehicle = highestCostList.get(randomIndex);

		return randVehicle;
		}
	
	public Vehicle getVehicleWithLowestMaintenanceCost(double distance) {
		
		//create variable to track index of the vehicle with the lowest maintenance cost
		int lowestIndex = 0;
		
		//find the vehicle with the highest maintenance cost and store index into lowestIndex variable
		for(int i = 1; i < vehicles.size(); i++) {
			if(vehicles.get(i).calculateMaintenaceCost(distance) < vehicles.get(lowestIndex).calculateMaintenaceCost(distance)) {
				//System.out.println(vehicles.get(i).getMake() + ": " + vehicles.get(i).calculateMaintenaceCost(distance));
				lowestIndex = i;
			}
		}
		
		//instantiate new array list to store all vehicles with the same maintenance cost
		ArrayList<Vehicle>lowestCostList = new ArrayList<Vehicle>();
		//instantiate new vehicle object to compare maintenance costs
		Vehicle lowestCostVehicle = vehicles.get(lowestIndex);
		
		//System.out.println("Lowest Cost Vehicle: " + lowestCostVehicle.getMake());
		
		//iterate through vehicle list to find vehicles with the same maintenance cost
		//if there exists a vehicle with the same maintenance cost as the lowest maintenance cost, add it to lowestCostList
		for(Vehicle vehicle: vehicles) {
			//System.out.println(vehicle.getMake() + ": " + vehicle.calculateMaintenaceCost(distance));
			if(vehicle.calculateMaintenaceCost(distance) == lowestCostVehicle.calculateMaintenaceCost(distance)) {
				//System.out.println("\nAdded Vehicle to lowestCostList:\n" + vehicle.getMake() + " " + vehicle.calculateMaintenaceCost(distance) + "\n");
				lowestCostList.add(vehicle);
			}
		}
		
		//randomly return an object form highestCostList based on the size of HighestCostList so each object hast he same change of being returned
		Random rand = new Random();
		int randomIndex = rand.nextInt(lowestCostList.size());
		Vehicle randVehicle = lowestCostList.get(randomIndex);

		return randVehicle;
		}



}
