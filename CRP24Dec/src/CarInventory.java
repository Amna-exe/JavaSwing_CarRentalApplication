
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My
 */
public class CarInventory implements Serializable {
    private final String filePath;
    public static ArrayList<Car> allAvailableCars = new ArrayList<>();

    public enum CarCategory{
        ECONOMY,LUXURY,ELECTRIC;
        
        public static boolean isValidCategory(String category) {
            try {
                CarCategory.valueOf(category.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }
    
    public CarInventory(){
        filePath = "C:\\Users\\My\\Documents\\NetBeansProjects\\CRPCarRentalProgram\\src\\main\\java\\com\\mycompany\\crpcarrentalprogram\\CarInventory.txt";
        
          // Ensure the file exists
    File file = new File(filePath);
    if (!file.exists()) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create inventory file.");
        }
    }
    loadCarsFromFile();
    }
    
    
    
    //reads all the class into the arraylist.
    public void loadCarsFromFile(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
                allAvailableCars = (ArrayList<Car>)ois.readObject();
                ois.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
       
    }
    
    
    //write all the cars to the file. as needed.
    public void saveCarsToFile(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(allAvailableCars);
            oos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //all the subclasses of type car are added to the arraylist
    public boolean addCarToInventory(Car carObject){
          if (carObject != null 
            && carObject.getCarModel() != null && !carObject.getCarModel().isEmpty()
            && carObject.getColor() != null && !carObject.getColor().isEmpty()
            && carObject.getCarCategory() != null 
            && CarCategory.isValidCategory(carObject.getCarCategory())) {
            
            if (allAvailableCars.stream().noneMatch(car -> car.getCarId().equalsIgnoreCase(carObject.getCarId()))) {
                allAvailableCars.add(carObject);
                saveCarsToFile(); // Save after adding a car
                return true;
            }
        }
        return false;
    }
    
     public boolean removeCarFromInventory(String carId) {
        boolean carFound = false;
    for (int i = 0; i < allAvailableCars.size(); i++) {
        if (allAvailableCars.get(i).getCarId().equalsIgnoreCase(carId)) {
            allAvailableCars.remove(i); // Remove car from the list
            carFound = true;
            break;
        }
    }
    if (carFound) {
        saveCarsToFile(); // Save the updated list after removal
        return true;
    }
    return false;
    }

     
     
     public boolean updateCarDetails(String carId, String newModel, String newColor, boolean newAvailability, int newSeatingCapacity) {
      boolean carFound = false;

    for (Car car : allAvailableCars) {
        if (car.getCarId().equalsIgnoreCase(carId)) {
            car.updateDetails(newModel, newColor, newAvailability, newSeatingCapacity); // Update car details
            carFound = true;
            break;
        }
    }

    if (carFound) {
        saveCarsToFile(); // Save the updated list after modification
        return true;
    }
    return false;
    }
     //separate update methods according to attributes for different cars.... (PolyMorphismm)
    private void updateEconomyCar(EconomyCar car, String newModel, boolean newAvailability, double newRentPerDay, String newColor) {
        car.setCarModel(newModel);
        car.setAvailability(newAvailability);
        car.setRentPerDay(newRentPerDay);
        car.setColor(newColor);
        System.out.println("EconomyCar details updated.");
    }

    private void updateLuxuryCar(LuxuryCar car, String newModel, boolean newAvailability, double newRentPerDay, String newColor) {
        car.setCarModel(newModel);
        car.setAvailability(newAvailability);
        car.setRentPerDay(newRentPerDay);
        car.setColor(newColor);
        System.out.println("LuxuryCar details updated.");
    }

    private void updateElectricCar(ElectricCar car, String newModel, boolean newAvailability, double newRentPerDay, String newColor) {
        car.setCarModel(newModel);
        car.setAvailability(newAvailability);
        car.setRentPerDay(newRentPerDay);
        car.setColor(newColor);
        System.out.println("ElectricCar details updated.");
    }

    public void updateCarAvailability(String carId, boolean availability) {
        for (Car car : allAvailableCars) {
            if (car.getCarId().equals(carId)) {
                car.setAvailability(availability);
                saveCarsToFile();
                break;
            }
        }
    }
   
     
    public ArrayList<Car> searchCars(String attribute, String value) {
    ArrayList<Car> matchingCars = new ArrayList<>();

    for (Car car : allAvailableCars) {
        switch (attribute.toLowerCase()) {
            case "carid":
                if (car.getCarId().equalsIgnoreCase(value)) {
                    matchingCars.add(car);
                }
                break;
            case "carmodel":
                if (car.getCarModel().equalsIgnoreCase(value)) {
                    matchingCars.add(car);
                }
                break;
            case "carcategory":
                if (car.getCarCategory().equalsIgnoreCase(value)) {
                    matchingCars.add(car);
                }
                break;
            case "availability":
                boolean isAvailable = Boolean.parseBoolean(value);
                if (car.isAvailability() == isAvailable) {
                    matchingCars.add(car);
                }
                break;
            case "rentperday":
                try {
                    double rentPerDay = Double.parseDouble(value);
                    if (car.getRentPerDay() == rentPerDay) {
                        matchingCars.add(car);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rent per day value.");
                }
                break;
            default:
                System.out.println("Invalid attribute specified.");
                break;
        }
    }

    return matchingCars;
}

    
    public void displaySearchResults(ArrayList<Car> matchingCars) {
        if (matchingCars.isEmpty()) {
            System.out.println("No cars found matching the criteria.");
        } else {
            System.out.println("Matching Cars:");
            for (Car car : matchingCars) {
                System.out.println(car);
            }
        }
    }

    
    public ArrayList<Car> filterCarsByType(String carCategory) {
        ArrayList<Car> filteredCars = new ArrayList<>();
        for (Car car : allAvailableCars) {
            if (car.getCarCategory().equalsIgnoreCase(carCategory)) {
                filteredCars.add(car);
            }
        }
        return filteredCars;
    }


    //changed this to better accomadate the gui methods
    public List<Car> getAvailableCarsDetails() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : allAvailableCars) {
            if (car.isAvailability()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public Car searchCarById(String carId) {
     for (Car car : allAvailableCars) {
        if (car.getCarId().equalsIgnoreCase(carId)) {
            return car;
        }
    }
    return null; 
    }
    
    public Car findCar(String carId, String carModel, String carCategory) {
    return allAvailableCars.stream()
        .filter(car -> car.getCarId().equalsIgnoreCase(carId) &&
                       car.getCarModel().equalsIgnoreCase(carModel) &&
                       car.getCarCategory().equalsIgnoreCase(carCategory) &&
                       car.isAvailability())
        .findFirst()
        .orElse(null);
}

}
