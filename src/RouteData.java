
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RouteData implements Serializable {

    public static LinkedlistofCoordinates Route = new LinkedlistofCoordinates();
    public static ArrayList<Integer> destinationIDsOfItemsToBeStackedPreModification = new ArrayList<Integer>();
    public static ArrayList<Integer> destinationIDs = new ArrayList<Integer>();
    public static HashMap<Integer, String> DestinationIDandAddress = new HashMap<Integer, String>();

    public static void gettingtheshortestRoute() {

      

        int[][] grid = {
                { 0, 15, 10, 20, 30 },
                { 15, 0, 5, 30, 20 },
                { 10, 5, 0, 30, 40 },
                { 20, 25, 30, 0, 15 },
                { 30, 35, 40, 15, 0 }
        };

       

      

        int[] path = new int[6]; // Assuming 4 stops starting annd endign in origin
        int min = Integer.MAX_VALUE;
        int cost = 0;
        int i = 0, j = 0;
        int total_visited = 1; // Starting stop (0) is already visited
        List<Integer> visited = new ArrayList<>();

        visited.add(0);

        while (total_visited < grid.length) {
            for (i = 0; i < grid.length; i++) {
                if (visited.contains(i)) {
                    continue;
                }

                if (grid[i][j] < min && i != j) {
                    min = grid[i][j];
                    path[total_visited] = i;
                }
            }
            j = path[total_visited];
            cost += min;
            min = Integer.MAX_VALUE;
            visited.add(j);
            total_visited++;
        }

        // Return to the starting stop (0)
        path[total_visited] = 0;
        cost += grid[j][0];

       // System.out.println("\n Minimum cost is " + cost);
        System.out.println("\n Optimal Path is :");

        

        // add the path to array
        for (int k = 0; k < path.length; k++) {
            destinationIDsOfItemsToBeStackedPreModification.add(path[k]);
            System.out.print(+path[k] + " ");
        }

        System.out.println(
                "\ndestination IDs of route points before the origin starting and ending point(the starting destination is also the ending destination) have been removed for more simplicity"
                 + destinationIDsOfItemsToBeStackedPreModification);

        // get the path omitting the origin

        //here i am removing the first and last elements of the array which is 0 which represents the starting and ending points
        //start and end is the same
        //it is the source
        if (destinationIDsOfItemsToBeStackedPreModification == null
                || destinationIDsOfItemsToBeStackedPreModification.size() == 0) {
            return;
        }
        destinationIDsOfItemsToBeStackedPreModification.remove(0);
        int lastIndex = destinationIDsOfItemsToBeStackedPreModification.size() - 1;
        if (lastIndex >= 0) {
            destinationIDsOfItemsToBeStackedPreModification.remove(lastIndex);
        }
        for (int k = 0; k < destinationIDsOfItemsToBeStackedPreModification.size(); k++) {
            destinationIDs.add(destinationIDsOfItemsToBeStackedPreModification.get(k));
        }

        System.out.println("destination IDs" + destinationIDs);

    }

    public static void setHashMap() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateA = LocalDate.parse("2023-05-02", formatter);
        LocalDate dateB = LocalDate.parse("2023-02-03", formatter);
        LocalDate dateC = LocalDate.parse("2023-05-04", formatter);
        LocalDate dateD = LocalDate.parse("2023-01-05", formatter);

        OrderData orderDataAssociatdWithDestination1 = new OrderData(1, 1, dateA, 1);
        OrderData orderDataAssociatdWithDestination2 = new OrderData(2, 22, dateB, 102);
        OrderData orderDataAssociatdWithDestination3 = new OrderData(3, 3, dateC, 3);
        OrderData orderDataAssociatdWithDestination4 = new OrderData(4, 4, dateD, 4);

        Route.addDestination(0, 7.2118, 79.8369, null);
        Route.addDestination(1, 7.0596, 79.9599, orderDataAssociatdWithDestination1);
        Route.addDestination(2, 7.0026, 79.9070, orderDataAssociatdWithDestination2);
        Route.addDestination(3, 6.9271, 79.8824, orderDataAssociatdWithDestination3);
        Route.addDestination(4, 6.9271, 79.8824, orderDataAssociatdWithDestination4);

        // add the address to the hashmap
        DestinationIDandAddress.put(1, "123 Galle Road, Colombo 03, Colombo ");
        DestinationIDandAddress.put(2, " 789 Negombo Road, Negombo");
        DestinationIDandAddress.put(3, "101 Matara Road, Matara");
        DestinationIDandAddress.put(4, "234 Badulla Road, Badulla");
        DestinationIDandAddress.put(5, "567 Ratnapura Road, Ratnapura");

        // print the data

        List<SerializeDestination> destinations = new ArrayList<>();

        LinkedlistofCoordinates.Node current = Route.head;
        for (int i = 0; i < destinationIDs.size(); i++) {
            current = Route.head;
            while (current != null) {
                if (current.destinationID == destinationIDs.get(i)) {
                    System.out.println("Destination ID: " + current.destinationID
                            + "Address: " + DestinationIDandAddress.get(current.destinationID)
                            + " Latitude: " + current.latitude
                            + " Longitude: " + current.longitude + " Order Data: "
                            + " Order ID: " + current.orderDataAssociatdWithDestination.getOrderID()
                            + " Item ID: " + current.orderDataAssociatdWithDestination.getItemID()

                            + " Purchased Date: "
                            + current.orderDataAssociatdWithDestination.getOrderCreatedAtDate()

                    );
                    SerializeDestination destination = new SerializeDestination(
                            current.destinationID,
                            DestinationIDandAddress.get(current.destinationID),
                            current.latitude,
                            current.longitude,
                            current.orderDataAssociatdWithDestination.getOrderID(),
                            current.orderDataAssociatdWithDestination.getItemID(),
                            current.orderDataAssociatdWithDestination.getOrderCreatedAtDate());
                    destinations.add(destination);

                }
                current = current.next;
            }
        }

        try (FileOutputStream fos = new FileOutputStream("src/serializedDestinations.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(destinations);
            System.out.println("Serialization complete. Data saved to serializedDestinations.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void sendDestionIDForSerializing() {
        // serialize the destinationIDs
        BackupRouteData destionationIDs = new BackupRouteData();
        try {
            destionationIDs.serializeDestinationIDs(destinationIDs, "src/DestinationIDs.ser");
            System.out.println(" \n destination IDs have been serialized");
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

  

    public static void main(String[] args) {
        gettingtheshortestRoute();
        setHashMap();
        sendDestionIDForSerializing();
       


    }

}
