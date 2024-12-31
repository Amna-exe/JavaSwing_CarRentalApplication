

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

 
 public class CRP24Dec {

    static CarInventory carInventory = new CarInventory();
    public static ProcessReservation reservationProcessor = new ProcessReservation();
    
    public static void main(String[] args) {
        
        carInventory.loadCarsFromFile();



        // Launch the GUI in a thread-safe manner
        SwingUtilities.invokeLater(() -> {
            new CRPGui(); // Launch the GUI
        });
    }
}
     
 
 
 