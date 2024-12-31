
import java.io.Serializable;

/**
 *
 * @author My
 */
public class EconomyCar extends Car implements Serializable{
    
    //basic problems fixed (update 1:13 pm 10/12/24)
    
    private int offRoadLimt;

    public EconomyCar(String carModel, boolean availability, double rentPerDay, String color, String registrationNumber, int offRoadLimt, String engineType, String transmision, int seatingCapacity) {
        super(carModel, availability, rentPerDay, color, registrationNumber, seatingCapacity, engineType, transmision);
        this.offRoadLimt = offRoadLimt;
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
        return "Extra 10% of Rental cost will be applied as the Insurance Fee ";
    }
    
    @Override
    public double insuranceCost(){
        return 0.10*super.getRentPerDay();
    }
    
      @Override
    public void updateDetails(String newModel, String newColor, boolean newAvailability, int newSeatingCapacity) {
        setCarModel(newModel);
        setColor(newColor);
        setAvailability(newAvailability);
        setSeatingCapacity(newSeatingCapacity);
        // Update specific attributes later.
    }
    
}
