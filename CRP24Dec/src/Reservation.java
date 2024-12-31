import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation implements Serializable{
    private static final long serialVersionUID = 1L;
    private Customer customer;
    private String customerId;
    private Car car;
    private String carModel;
    private String carId;
    private String customerName;
    private LocalDate rentalDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double rentalCost;

    public Reservation(Customer customer, Car car, LocalDate rentalDate, LocalDate dueDate, LocalDate returnDate, double rentalCost) {
        this.customer = customer;
        this.customerId = customer.getUserId();
        this.car = car;
        this.carModel = car.getCarModel();
        this.carId = car.getCarId();
        this.customerName = customer.fullName;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.rentalCost = getRentalCost();
    }

   
        
    public Reservation(Car car,String custid, String custName, String carCategory, String carModel, LocalDate rentalDate, LocalDate dueDate, String carId, double rentalCost){
        this.customerId = custid;
        this.customerName = custName;
        this.carModel = carModel;
        this.carId = carId;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.rentalCost = getRentalCost();  
        
    }

     public double getRentalCost() {
        long daysRented = ChronoUnit.DAYS.between(this.rentalDate, this.dueDate);
        return daysRented * car.getRentPerDay(); // Use car's rent per day
    }

    private double getCarDailyRate() {
        return car.getRentPerDay();

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

 

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

   
   
}