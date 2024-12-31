
import java.io.Serializable;

/**
 *
 * @author My
 */
public class LuxuryCar extends Car implements Serializable{

    private String carBrand;

    public LuxuryCar(String carBrand, boolean availability, double rentPerDay, String carModel, String color, int seatingCapacity, String registrationNumber, String engineType, String transmision) {
        super(carModel, availability, rentPerDay, color, registrationNumber, seatingCapacity, engineType, transmision);
        this.carBrand = carBrand;
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
        return "Extra 20% of Rental cost will be applied as the Insurance Fee ";
    }
    
    @Override
    public double insuranceCost(){
        return 0.20*super.getRentPerDay();
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
