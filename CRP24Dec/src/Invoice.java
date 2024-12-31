
import java.time.temporal.ChronoUnit;

/**
 *
 * @author My
 */
public class Invoice {
      private Reservation reservation;
        private ReservationHistory reservationHistory;

    public Invoice(Reservation reservation, ReservationHistory reservationHistory) {
        this.reservation = reservation;
        this.reservationHistory = reservationHistory;
    }

    // Rental Confirmation Receipt
    public String RentalConfirmationReceipt() {
        return "---Rental Invoice---\n" +
                "Customer: " + reservation.getCustomerName() + "\n" +
                "Car Model: " + reservation.getCarModel() + "\n" +
                "Rental Date: " + reservation.getRentalDate() + "\n" +
                "Due Date: " + reservation.getDueDate() + "\n" +
                "Rental Cost: $" + reservation.getRentalCost() + "\n";
    }

    // Car Return Receipt
    public String CarReturnReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("---Return Invoice---\n");
        receipt.append("Customer: ").append(reservation.getCustomerName()).append("\n");
        receipt.append("Car Model: ").append(reservation.getCarModel()).append("\n");
        receipt.append("Rental Cost: $").append(reservation.getRentalCost()).append("\n");
        if (reservation.getReturnDate().isAfter(reservation.getDueDate())) {
            long overdueDays = ChronoUnit.DAYS.between(reservation.getDueDate(), reservation.getReturnDate());
            double lateFee = overdueDays * 50.0;
            receipt.append("Late Fee: $").append(lateFee).append("\n");
            receipt.append("Total Cost: $").append(reservation.getRentalCost() + lateFee).append("\n");
        } else {
            receipt.append("No Late Fee\n");
        }
        return receipt.toString();
    }

    // Admin Revenue Report
    public String AdminReport() {
        double totalRevenue = reservationHistory.getReservationHistoryList().stream()
                .mapToDouble(Reservation::getRentalCost).sum();
        return "---Admin Revenue Report---\n" +
                "Total Revenue: $" + totalRevenue + "\n";
    }
}
