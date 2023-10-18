import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OfflineCheck {


    public static void destinationData(){
        try {
            FileInputStream fileIn = new FileInputStream("src/serializedDestinations.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<SerializeDestination> destinations = (List<SerializeDestination>) in.readObject();
            for (SerializeDestination destination : destinations) {
                System.out.println(
                        "Destination ID: " + destination.getDestinationID()
                                + " Address: " + destination.getAddress()
                                + " Latitude: " + destination.getLatitude()
                                + " Longitude: " + destination.getLongitude()
                                + " Order Data: "
                                + " Order ID: " + destination.getOrderID()
                                + " Item ID: " + destination.getItemID()
                                + " Purchased Date: " + destination.getPurchasedDate());
            }
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getRoute(){
        try {
             FileInputStream fileIn = new FileInputStream("src/DestinationIDs.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Integer> destinationIDs = (ArrayList<Integer>) in.readObject();
            System.out.println("destinationIDs" + destinationIDs);
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Is the system offline? (y/n)");
        String input = sc.nextLine().toLowerCase();

        if (input.equals("y")) {
        

            getRoute();
            destinationData();


           



        } else if (input.equals("n")) {
            System.out.println("System is online");
        } else {
            System.out.println("Invalid input. Please enter 'y' for offline or 'n' for online.");
        }

        sc.close();

    }
}
