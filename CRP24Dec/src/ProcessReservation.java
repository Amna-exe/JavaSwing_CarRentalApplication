/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ProcessReservation {
    

    private List<Reservation> reservationList;
    private boolean reservationStatus;
    private ReservationHistory reservationHistory; 

    public ProcessReservation() {
        this.reservationHistory = new ReservationHistory(); 
        this.reservationList = reservationHistory.getReservationHistoryList();
        this.reservationStatus = false;

    }

    // Add a reservation and save it to reservation history
    public void addReservation(Reservation reservation) throws ReservationConflictException {
            if (reservation == null) {
        throw new IllegalArgumentException("Reservation cannot be null.");
        }

        LocalDate rentalDate = reservation.getRentalDate();
        LocalDate dueDate = reservation.getDueDate();
        String carId = reservation.getCarId();

        // Validate reservation details
        if (rentalDate == null || dueDate == null || carId == null || carId.trim().isEmpty()) {
            throw new IllegalArgumentException("Reservation details cannot have null or empty values.");
        }

        // Ensure rentalDate is not after dueDate
        if (rentalDate.isAfter(dueDate)) {
            throw new IllegalArgumentException("Rental date cannot be after the due date.");
        }

        // Check for conflicts
        if (checkConflict(rentalDate, dueDate, carId)) {
            throw new ReservationConflictException("Conflict detected: The car is already reserved during the requested period.");
        }

        // Prevent duplicate reservations
        if (reservationList.contains(reservation)) {
            throw new IllegalArgumentException("Duplicate reservation: This reservation already exists.");
        }

        // Add reservation
        reservationList.add(reservation);
        reservationHistory.addReservationToHistory(reservation); // Track reservation in history
        reservationStatus = true;
    }

       // Remove reservation and update reservation history
    public boolean removeReservation(String reservationId) throws ReservationNotFoundException {
        if (reservationId == null || reservationId.isEmpty()) {
            throw new IllegalArgumentException("Reservation ID cannot be null or empty.");
        }

        Iterator<Reservation> iterator = reservationList.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getCarId().equals(reservationId)) {
                iterator.remove();
                reservationStatus = false;
                reservationHistory.addReservationToHistory(reservation);
                return true;
            }
        }
        throw new ReservationNotFoundException("Reservation with ID " + reservationId + " not found.");
    }

    // Check for conflicts in reservations
    public boolean checkConflict(LocalDate start, LocalDate end, String carId) {
        if (start == null || end == null || carId == null || carId.isEmpty()) {
        throw new IllegalArgumentException("Invalid input: Dates and car ID must not be null or empty.");
    }
    if (start.isAfter(end)) {
        throw new IllegalArgumentException("Start date cannot be after end date.");
    }
    if (reservationList == null) {
        throw new IllegalStateException("Reservation list has not been initialized.");
    }

    for (Reservation res : reservationList) {
         if (res.getCarId().equalsIgnoreCase(carId)) {
            // Reservation date ranges
            LocalDate existingStart = res.getRentalDate();
            LocalDate existingEnd = res.getDueDate();

            // Check for any overlap
            if (!start.isAfter(existingEnd) && !end.isBefore(existingStart)) {
                return true; // Conflict detected
            }
        }
    }
    return false;
    }
    
        // Update reservation details and log changes in reservation history
    public boolean updateReservation(String carId, LocalDate newRentalDate, LocalDate newDueDate, double newRentalCost)
            throws ReservationNotFoundException, ReservationConflictException {
                if (carId == null || carId.isEmpty()) {
            throw new IllegalArgumentException("Car ID cannot be null or empty.");
        }
        if (newRentalDate == null || newDueDate == null) {
            throw new IllegalArgumentException("Rental dates cannot be null.");
        }
        if (newRentalDate.isAfter(newDueDate)) {
            throw new IllegalArgumentException("Rental date cannot be after the due date.");
        }
        
        
        for (Reservation reservation : reservationList) {
            if (reservation.getCarId().equals(carId)) {
                if (!checkConflict(newRentalDate, newDueDate, carId)) {
                    reservation.setRentalDate(newRentalDate);
                    reservation.setDueDate(newDueDate);
                    reservation.setRentalCost(newRentalCost);
                    reservationHistory.addReservationToHistory(reservation); // Log update
                    return true;
                } else {
                    throw new ReservationConflictException("Conflict detected with the updated dates.");
                }
            }
        }
        throw new ReservationNotFoundException("Reservation with ID " + carId + " not found.");
    }

    // View all reservations
     public List<Reservation> getAllReservations() {
        
    return new ArrayList<>(reservationList);
}

    // View reservation history
     //history should give an arraylist instead og just an intialized object
     //how to use  that inside the code
    public List<Reservation> getReservationHistory() {
       if (reservationHistory == null) {
            throw new IllegalStateException("Reservation history is not initialized.");
        }
        return reservationHistory.getReservationHistoryList();
    }

    // Show reservation status for a car
    public boolean isCarReserved(String carId) {
        if (carId == null || carId.isEmpty()) {
            throw new IllegalArgumentException("Car ID cannot be null or empty.");
        }
        for (Reservation reservation : reservationList) {
            if (reservation.getCarId().equals(carId)) {
                return true;
            }
        }
        return false;
    }

      // Get reservations within specific date range
    public List<Reservation> getReservationsWithinDates(LocalDate startDate, LocalDate endDate) {
                if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start and end dates cannot be null.");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after the end date.");
        }
        
        List<Reservation> filteredReservations = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            if ((reservation.getRentalDate().isEqual(startDate) || reservation.getRentalDate().isAfter(startDate)) &&
                (reservation.getDueDate().isEqual(endDate) || reservation.getDueDate().isBefore(endDate))) {
                filteredReservations.add(reservation);
            }
        }
        return filteredReservations;
    }
    
    // Add this method to the ReservationHistory class
    public List<String> getReservationHistoryByCustomerId(String customerId) {
    List<String> customerReservations= new ArrayList<>();
    for(Reservation x:reservationHistory.getReservationHistoryList()){
        if(x.getCustomerId().equalsIgnoreCase(customerId)){
             String reservationDetails = String.format(
            "Customer Id:%s\n CustomerName:%s\nCarModel:%s\nBooking Date:%s\nDueDate:%s,\n%drentalCost: "
                    ,x.getCustomerId(),x.getCustomerName(),x.getCarModel(),x.getRentalDate(),x.getRentalDate(),x.getRentalCost());
            customerReservations.add(reservationDetails);
        }
    }
    return customerReservations;
}

    
}
