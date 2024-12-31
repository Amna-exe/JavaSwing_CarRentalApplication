
import java.io.Serializable;

/**
 *
 * @author My
 */
public class ElectricCar extends Car implements Serializable {

    private double batteryCapacity;
    private double chargingTime;
    private double rangePerCharge;

    public ElectricCar(String carModel, boolean availability, double batteryCapacity, String color, String registrationNumber, int seatingCapacity, String engineType, String transmision, double chargingTime, double rangePerCharge, double rentPerDay) {
        super(carModel, availability, rentPerDay, color, registrationNumber, seatingCapacity, engineType, transmision);
        this.batteryCapacity = batteryCapacity;
        this.chargingTime = chargingTime;
        this.rangePerCharge = rangePerCharge;
    }
    
    @Override
    public String keyFeatures(){
        return "KeyFeatures: "+ "Very good car";
    }
    
    @Override
    public int basicPackagePricing(){
        return 1;
    }
    
    @Override
    public String insuranceInfo(){
        return "Extra 30% of Rental cost will be applied as the Insurance Fee ";
    }
    
    @Override
    public double insuranceCost(){
        return 0.30*super.getRentPerDay();
    }
    
    @Override
    public void updateDetails(String newModel, String newColor, boolean newAvailability, int newSeatingCapacity) {
        setCarModel(newModel);
        setColor(newColor);
        setAvailability(newAvailability);
        setSeatingCapacity(newSeatingCapacity);
        // Update specific attributes if needed
    }
}
