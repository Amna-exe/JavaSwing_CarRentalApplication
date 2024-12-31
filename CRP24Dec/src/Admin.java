
import java.io.IOException;
import java.util.List;


public class Admin extends User{
    
    public CarInventory carInventory;
    public Car newcar;
    public String adminId;

    
    public Admin(String adminId, String fullName, String email, String password, String phoneNumber) {
        super(fullName, email, password, phoneNumber);
        this.adminId = adminId;

        carInventory = new CarInventory();
    }
    public Admin(){
        this.adminId = null;
        carInventory = new CarInventory();
    }

    @Override
    public String getUserId() {
        return adminId;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }


    public void editAdminProfile(String newName, String newEmail, String newPassword, String newPhone) throws InvalidEmailException, EmptyFieldException {
        
        // Validate that fields are not empty
        if (newName.isEmpty() || newEmail.isEmpty() || newPhone.isEmpty() || newPassword.isEmpty()) {
            throw new EmptyFieldException("All fields must be filled. None of the fields can be empty.");
        }

        // Validate email format
        if (!isValidEmail(newEmail)) {
            throw new InvalidEmailException("The email format is invalid. Please enter a valid email.");
        }

        // Update fields if no exceptions were thrown
        setFullName(newName);
        setEmail(newEmail);
        setPassword(newPassword);
        setPhoneNumber(newPhone);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }



    protected void viewAdminProfile() {
        System.out.println("Admin ID: " + adminId);
        System.out.println("Full Name: " + getFullName());
        System.out.println("Email: " + getEmail());
        System.out.println("Password: " + getPassword());
        System.out.println("Phone Number: " + getPhoneNumber());
    }
    
    
    @Override
    public String toString() {
        return adminId + "," + super.toString();
    }

   protected void addCar(
        String carId, 
        String carCategory, 
        String carModel, 
        boolean isAvailable, 
        double rentPerDay, 
        String color, 
        String registrationNumber, 
        int seatingCapacity, 
        String engineType, 
        String transmission, 
        double insurancePercent,
        String carBrand,            // Unique to LuxuryCar
        Integer offRoadLimit,       // Unique to EconomyCar (use Integer to allow null)
        Double batteryCapacity,     // Unique to ElectricCar (use Double to allow null)
        Double chargingTime, 
        Double rangePerCharge
        )throws IOException, CarCategoryException {
    Car newCar;

    switch (carCategory.toLowerCase()) {
        case "economy" -> {
            if (offRoadLimit == null || offRoadLimit <= 0) {
                throw new CarCategoryException("Economy cars require a valid off-road limit.");
            }
            newCar = new EconomyCar(
                    carModel,
                    isAvailable,
                    rentPerDay,
                    color,
                    registrationNumber,
                    offRoadLimit,
                    engineType,
                    transmission, seatingCapacity
            );
            }

        case "luxury" -> {
            if (carBrand == null || carBrand.isEmpty()) {
                throw new CarCategoryException("Luxury cars require a valid car brand.");
            }
            newCar = new LuxuryCar(
                    carBrand,
                    isAvailable,
                    rentPerDay, carModel,
                    color,
                    seatingCapacity, registrationNumber,
                    engineType,
                    transmission
            );
            }

        case "electric" -> {
            if (batteryCapacity == null || batteryCapacity <= 0 ||
                    chargingTime == null || chargingTime <= 0 ||
                    rangePerCharge == null || rangePerCharge <= 0) {
                throw new CarCategoryException("Electric cars require valid battery capacity, charging time, and range per charge.");
            }
            newCar = new ElectricCar(
                    carModel,
                    isAvailable,
                    batteryCapacity,
                    color,
                    registrationNumber,
                    seatingCapacity,
                    engineType,
                    transmission, chargingTime, rangePerCharge, rentPerDay
            );
            }

        default -> throw new IllegalArgumentException("Invalid car category.");
    }

    carInventory.addCarToInventory(newCar);
    }

    protected void updateCar(String carId, String newCategory, String newModel, boolean newAvailability, double newRentPerDay, 
                             String newColor, String newRegistrationNumber, int newSeatingCapacity, String newEngineType, 
                             String newTransmission, double newInsurancePercent) {
        carInventory.updateCarDetails(carId, newCategory, newModel, newAvailability, newSeatingCapacity);
    }

    protected void deleteCar(String carId) {
        carInventory.removeCarFromInventory(carId);
    }

    //add methods in the admin so that admin can view cars according to a certain category or by type
    //add method so that admin can enter name of a car and see if it is available.
    //model isn't a good identifier, add some category name to filter out the relatd ones. 
    protected void viewCarsByCategory(String carCategory) {
        carInventory.filterCarsByType(carCategory);

    }
    
    //Car Inventory

    protected boolean addCarToInventory(Car car) throws IOException {
        return carInventory.addCarToInventory(car);
    }
    
    protected boolean deleteCarFromInventory(String carId) {
        return carInventory.removeCarFromInventory(carId);
    }
    
    protected boolean updateCarInInventory(String carId, String newCategory, String newModel, boolean newAvailability, int newRentPerDay) {
        return carInventory.updateCarDetails(carId, newCategory, newModel, newAvailability, newRentPerDay);
    }

   protected List<Car>viewAllCars(CarInventory carInventory){
       List<Car> carDetails = carInventory.getAvailableCarsDetails();
       return carDetails;
    }

}
