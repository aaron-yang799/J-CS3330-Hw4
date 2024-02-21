package edu.mu.vehicle;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManager {
	private static VehicleManager instance = null;
	private final static String filePath = "files/vehicleList.csv";
	private static ArrayList<Vehicle> vehicles;
	
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
							vehicles.add(new Truck(fields[1], fields[2], Long.parseLong(fields[3]), Double.parseDouble(fields[4]),
									VehicleColor.valueOf(fields[5]), FuelType.valueOf(fields[6]), Double.parseDouble(fields[7]), 
									Double.parseDouble(fields[8]), Integer.parseInt(fields[9]), Double.parseDouble(fields[10]), 
									StartMechanism.valueOf(fields[11])));						
							break;
						case "Car":
							vehicles.add(new Truck(fields[1], fields[2], Long.parseLong(fields[3]), Double.parseDouble(fields[4]),
									VehicleColor.valueOf(fields[5]), FuelType.valueOf(fields[6]), Double.parseDouble(fields[7]), 
									Double.parseDouble(fields[8]), Integer.parseInt(fields[9]), Double.parseDouble(fields[10]), 
									StartMechanism.valueOf(fields[11])));		
							break;
						case "MotorBike":
							vehicles.add(new Truck(fields[1], fields[2], Long.parseLong(fields[3]), Double.parseDouble(fields[4]),
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
		
		try(FileWriter writer = new FileWriter("files/vehicleListTest.csv")) {
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
								vehicles.get(i).getPrice() + "," +
								vehicles.get(i).getColor() + "," +
								vehicles.get(i).getFuelType() + "," +
								vehicles.get(i).getMileage() + "," +
								vehicles.get(i).getMass() + "," +
								vehicles.get(i).getCylinders() + "," +
								vehicles.get(i).getGasTankCapacity() + "," +
								vehicles.get(i).getStartType()
								);
			}
			writer.close();
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	private boolean isVehicleType(Vehicle v, Class<Vehicle> clazz) {
		return clazz.isInstance(v);
	}
	
	public int getNumberOfVehichlesByType(Class<Vehicle> clazz) {
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
}
