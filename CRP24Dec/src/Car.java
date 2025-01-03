

import java.io.Serializable;

public abstract class Car implements Serializable{
    
    private String carId;
    private String carModel;
    private boolean availability;
    private double rentPerDay;
    private String color;
    private String registrationNumber;
    private int seatingCapacity;
    private String engineType; 
  // CNG //Diesel Electric Hybrid Lpg Petrol
    private String transmision; //automatic or manual..
    private double insurancePercent;
    private final String carCategory; //to identify the car type

    
 

    public Car(String carModel, boolean availability, double rentPerDay, String color, String registrationNumber, int seatingCapacity, String engineType, String transmision) {
        this.carModel = carModel;
        this.availability = availability;                                                                 
        this.rentPerDay = rentPerDay;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.seatingCapacity = seatingCapacity;
        this.engineType = engineType;
        this.transmision = transmision;
        this.carId = generateCarId();
        this.carCategory  = determineCarCategory(engineType); 
    }
    
    //abstract method for the the cars to show their features. 
    //it extends to three other car classes called electric car, luxury car and economy car
    public abstract void updateDetails(String newModel, String newColor, boolean newAvailability, int newSeatingCapacity);
    public abstract String keyFeatures();
    public abstract int basicPackagePricing();
    public abstract String insuranceInfo();
    public abstract double insuranceCost();
    
    
    //create method to automatically assign car id to a new car added.
    //know the already available no of cars and then add the keyword CA-1/CA-2 etc
    public String generateCarId(){
        int size = CarInventory.allAvailableCars.size();
        return String.format("CA-0%d",size);
    }

    public static String determineCarCategory(String engineType){
        return switch (engineType.toLowerCase()) {
            case "electric" -> "Electric";
            case "diesel", "petrol" -> "Economy";
            case "hybrid", "lpg" -> "Luxury";
            default -> "Uncategorized";
        }; // Default case if no match
    }

    public String getCarCategory() {
        return carCategory;
    }
    
    
    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(double rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
    
}
