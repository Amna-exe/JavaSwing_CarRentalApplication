
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ReservationHistory implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
     private final String filePath; // Path to the rental history file
    private List<Reservation> reservationHistoryList; // List to store rental history

    public ReservationHistory() {
        this.filePath = "ReservationHistory.ser";
        this.reservationHistoryList = new ArrayList<>();
        loadReservationHistory();
    }


    // Deserialize objects from RentalHistory.txt and load them into rentalHistoryList
    private void loadReservationHistory() {
        File file = new File(this.filePath);
            
        // Ensure the file exists and is ready for reading
        if (!file.exists() || file.length()==0) {
             try {
            file.createNewFile(); // Create the file if it doesn't exist
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }
        return;
        }

        // Try loading the existing reservation history
        try (var ois = new ObjectInputStream(new FileInputStream(filePath))) {
            
                while (true) {
                    Reservation reservation = (Reservation) ois.readObject();
                    reservationHistoryList.add(reservation);
                }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            
        } 
    }
    
       // Get all rental history records
    public List<Reservation> getReservationHistoryList() {
        return reservationHistoryList;
    }

    // Add a reservation to the history and save it back to file
    public void addReservationToHistory(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null.");
        }
        // Add the reservation to the in-memory list
        reservationHistoryList.add(reservation);
    
        // Append this reservation to the file
        appendReservationToFile(reservation);
    }
    private void appendReservationToFile(Reservation reservation) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            oos.writeObject(reservation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Save the updated rental history list to file
    private void saveReservationHistory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(reservationHistoryList);
        } catch (IOException e) {
            System.out.println("Error saving reservation history: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //method to show all the reservations realed to a customer 
     public List<Reservation> searchReservations(String custId) {
        List<Reservation> matchingReservations = new ArrayList<>();
        for (Reservation reservation : reservationHistoryList) {
            if (reservation.getCustomerId().equalsIgnoreCase(custId)) {
                matchingReservations.add(reservation);
            }
        }
        return matchingReservations;
    }
}
