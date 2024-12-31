
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



public class ProcessReturn {
    private LocalDate returnDate;
    private Reservation reservation;
    private ReservationHistory reservationHistory;
    private ProcessReservation processReservation;
    private static final double LATE_FEE_PER_DAY = 50.0; 

    public ProcessReturn(Reservation reservation, LocalDate returnDate, ReservationHistory reservationHistory,ProcessReservation processReservation) {
        if (reservation == null || returnDate == null || reservationHistory == null) {
            throw new IllegalArgumentException("Reservation, return date, and reservation history must not be null.");
        }
        this.reservation = reservation;
        this.returnDate = returnDate;
        this.reservationHistory = reservationHistory;
        this.processReservation = processReservation;
    }

    // Check if the return is late and apply the late fee
    public double checkReturnDate() {
        LocalDate dueDate = reservation.getDueDate();
        if (returnDate.isAfter(dueDate)) {
            return applyLateFee();
        }
        return 0;
    }

    // Apply late fee if returned after the due date
    public double applyLateFee() {
        long overdueDays = ChronoUnit.DAYS.between(reservation.getDueDate(), returnDate);
        if (overdueDays > 0) {
            double lateFee = overdueDays * LATE_FEE_PER_DAY;  // Example late fee rate
            reservation.setRentalCost(reservation.getRentalCost() + lateFee);
            return lateFee;
        }
        return 0;
    }

    // Estimate total rental cost including late fees and insurance
    public double estimateRentalCost() {
        long daysBetween = ChronoUnit.DAYS.between(reservation.getRentalDate(), reservation.getDueDate());
        double rentalCost = daysBetween * reservation.getCar().getRentPerDay();
        double insuranceCost = reservation.getCar().insuranceCost();
        rentalCost += insuranceCost;
        return rentalCost;
    }

    // Complete the return process
    public String completeReturnProcess() {
        reservation.getCar().setAvailability(true);
        reservation.setReturnDate(returnDate);
        double totalCost = estimateRentalCost();
        reservation.setRentalCost(totalCost);
       try {
            processReservation.addReservation(reservation); // Add to history
            return "Car return processed successfully. Total cost: $" + totalCost;
        } catch (ReservationConflictException ex) {
            Logger.getLogger(ProcessReturn.class.getName()).log(Level.SEVERE, null, ex);
            return "Error processing the return: " + ex.getMessage();
        }
    }
    
        public Reservation getReservation() {
        return reservation;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
    

