import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App implements Serializable {

    // public static int n = 4;
    public static ArrayList<Integer> ordersToBeDelivered = new ArrayList<Integer>();
    public static HashMap<Integer, ItemData> OrderIDandDeliveryDestinationID = new HashMap<Integer, ItemData>();
    public static HashMap<Integer, String> DestinationIDandAddress = new HashMap<Integer, String>();
    public static ArrayList<String> OrderIDsToBeStacked = new ArrayList<String>();
    public static ArrayList<Integer> destinationIDsOfItemsToBeStackedPreModification = new ArrayList<Integer>();
    public static ArrayList<Integer> destinationIDsOfItemsToBeStacked = new ArrayList<Integer>();

    static class ItemData {
        private int destinationID;
        private String invoiceID;
        private LocalDate orderCreatedAtDate;
        private String OrderID;
        private String ItemID;
        private String CustomerID;

        public ItemData(int destinationID, LocalDate orderCreatedAtDate, String ItemID, String CustomerID,
                String invoiceID) {
            this.destinationID = destinationID;
            this.invoiceID = invoiceID;
            this.orderCreatedAtDate = orderCreatedAtDate;
            this.ItemID = ItemID;
            this.CustomerID = CustomerID;
        }

        public int getdestinationID() {
            return destinationID;
        }

        public String getInvoiceID() {
            return invoiceID;
        }

        public void setInvoiceID(String invoiceID) {
            this.invoiceID = invoiceID;
        }

        public void setdestinationID(int destinationID) {
            this.destinationID = destinationID;
        }

        public Integer getOrderID() {
            return Integer.parseInt(OrderID);
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public LocalDate getOrderCreatedAtDate() {
            return orderCreatedAtDate;
        }

        public void setOrderCreatedAtDate(LocalDate orderCreatedAtDate) {
            this.orderCreatedAtDate = orderCreatedAtDate;
        }

        public String getItemID() {
            return ItemID;
        }

        public void setItemID(String ItemID) {
            this.ItemID = ItemID;
        }

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String CustomerID) {
            this.CustomerID = CustomerID;
        }

    }

    public static int numberofitemsInHashmap = 0;

    public static void createorders() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateA = LocalDate.parse("2023-05-02", formatter);
        LocalDate dateB = LocalDate.parse("2023-02-03", formatter);
        LocalDate dateC = LocalDate.parse("2023-05-04", formatter);
        LocalDate dateD = LocalDate.parse("2023-01-05", formatter);
        LocalDate dateE = LocalDate.parse("2022-05-06", formatter);

        // add items to the hshmap along woth item data such as item ID , order created
        // at date , amd delivery destination ID
        OrderIDandDeliveryDestinationID.put(11, new ItemData(1, dateA, "1", "Customer1", "Invoice1"));
        OrderIDandDeliveryDestinationID.put(2, new ItemData(2, dateB, "2", "Customer142", "Invoice2"));
        OrderIDandDeliveryDestinationID.put(3, new ItemData(3, dateC, "3", "Customer133", "Invoice3"));
        OrderIDandDeliveryDestinationID.put(4, new ItemData(4, dateD, "4", "Customer14", "Invoice4"));
        OrderIDandDeliveryDestinationID.put(5, new ItemData(5, dateE, "5", "Customer15", "Invoice5"));
        numberofitemsInHashmap++;

        // add the addresses to the hashmap , destination ID , address
        DestinationIDandAddress.put(1, "123 Galle Road, Colombo 03, Colombo ");
        DestinationIDandAddress.put(2, " 789 Negombo Road, Negombo");
        DestinationIDandAddress.put(3, "101 Matara Road, Matara");
        DestinationIDandAddress.put(4, "234 Badulla Road, Badulla");
        DestinationIDandAddress.put(5, "567 Ratnapura Road, Ratnapura");
        /*
         * for (Integer OrderID : OrderIDandDeliveryDestinationID.keySet()) {
         * System.out.println("Order ID: " + OrderID +
         * "  invoice ID: " +
         * OrderIDandDeliveryDestinationID.get(OrderID).getInvoiceID() +
         * "  Customer ID: " +
         * OrderIDandDeliveryDestinationID.get(OrderID).getCustomerID() +
         * "  Destination ID: " +
         * OrderIDandDeliveryDestinationID.get(OrderID).getdestinationID() +
         * "  Order Created At Date: " +
         * OrderIDandDeliveryDestinationID.get(OrderID).getOrderCreatedAtDate() +
         * "  Item ID: " + OrderIDandDeliveryDestinationID.get(OrderID).getItemID()+
         * "  Address: " +
         * DestinationIDandAddress.get(OrderIDandDeliveryDestinationID.get(OrderID).
         * getdestinationID())
         * 
         * );
         * 
         * }
         */

        /*
         * for (Integer DestinationID : DestinationIDandAddress.keySet()) {
         * System.out.println("Destination ID: " + DestinationID + " Address: " +
         * DestinationIDandAddress.get(DestinationID));
         * }
         */

    }

    // this function fetches all the orders the company has to make deliveries for
    public static void getHashMapKeys() {
        for (Integer OrderID : OrderIDandDeliveryDestinationID.keySet()) {
            ordersToBeDelivered.add(OrderID);
        }
        System.out.println(
                "overall orders of the company to be delivered. This is yet to be optimized to fit van capacity: "
                        + ordersToBeDelivered);

    }

    // this function sorts the orders to be delivered according to the order created
    // at date
    // so the earliest purchases are delivered first
    public static void bubblesortDeliveryDates() {

        int L = ordersToBeDelivered.size();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L - i - 1; j++) {
                Integer OrderID1 = ordersToBeDelivered.get(j);
                Integer OrderID2 = ordersToBeDelivered.get(j + 1);
                LocalDate date1 = OrderIDandDeliveryDestinationID.get(OrderID1).getOrderCreatedAtDate();
                LocalDate date2 = OrderIDandDeliveryDestinationID.get(OrderID2).getOrderCreatedAtDate();
                if (date1.compareTo(date2) > 0) {
                    ordersToBeDelivered.set(j, OrderID2);
                    ordersToBeDelivered.set(j + 1, OrderID1);
                }
            }

        }

        System.out.println("Sorted array according to purchase date" + ordersToBeDelivered);

    }

    // using the capacity of the van i have shortened the sorted array so that the
    // order count doesn't exceed the van capacity
    public static void determineorderstobeloadedtotruck() {
        int truckCapacity = 4;
        ArrayList<Integer> orderstobeloadedtotruck = new ArrayList<Integer>();

        for (int i = 0; i < truckCapacity; i++) {
            Integer OrderID = ordersToBeDelivered.get(i);
            orderstobeloadedtotruck.add(OrderID);
        }
        System.out.println(" IDs of orders to be loaded to truck for the ride: " + orderstobeloadedtotruck);

    }

    public static void travellingsalesmanProblemToFindShortestRoute() {
        int[][] grid = {
                { 0, 10, 20, 30, 40 },
                { 10, 0, 20, 25, 30 },
                { 15, 30, 0, 10, 25 },
                { 20, 15, 40, 0, 10 },
                { 25, 20, 35, 15, 0 }
        };

        int[] path = new int[6];
        int min = Integer.MAX_VALUE;
        int mincost = 0;
        int i = 0, j = 0;
        int total_visited = 1;
        List<Integer> visited = new ArrayList<>();

        visited.add(0);

        // Continue looping until all cities are visited
        while (total_visited < grid.length) {
            // Iterate through all cities to find the next optimal city to visit
            for (i = 0; i < grid.length; i++) {
                // Skip cities that have already been visited
                if (visited.contains(i)) {
                    continue;
                }

                // Check if the distance to city 'i' from the current city 'j' is smaller than
                // the current minimum
                // and ensure 'i' is not the same as 'j'
                if (grid[i][j] < min && i != j) {
                    // Update the minimum distance and record the index of city 'i' in the path
                    // array
                    min = grid[i][j];
                    path[total_visited] = i;
                }
            }

            // Set the next city to be visited as the one with the smallest distance found
            // in the previous loop
            j = path[total_visited];

            // Add the minimum distance to the total minimum cost
            mincost += min;

            // Reset the 'min' variable to its maximum value for the next iteration
            min = Integer.MAX_VALUE;

            // Mark the current city 'j' as visited
            visited.add(j);

            // Move to the next iteration
            total_visited++;
        }

        // After all cities are visited, return to the starting city (city 0) to
        // complete the route
        path[total_visited] = 0;

        // Add the distance from the last visited city to the starting city to the total
        // minimum cost
        mincost += grid[j][0];

        // System.out.println(mincost);

        System.out.println("\nOptimal route path: ");
        for (int k = 0; k < path.length; k++) {
            destinationIDsOfItemsToBeStackedPreModification.add(path[k]);
            System.out.print(+path[k] + " ");

        }

        // the ending and starting point is the same . it is the source
        // so i am simplify the route path to show just the destination stops as it is a
        // given the starting and ending point is the same
        System.out.println(
                "\n destination IDs of optimal route path  before the origin and ending points have been removed for simplicity "
                        + destinationIDsOfItemsToBeStackedPreModification);

        if (destinationIDsOfItemsToBeStackedPreModification == null
                || destinationIDsOfItemsToBeStackedPreModification.size() == 0) {
            return;
        }

        destinationIDsOfItemsToBeStackedPreModification.remove(0);

        // remove the last element from the arraylist
        int lastIndex = destinationIDsOfItemsToBeStackedPreModification.size() - 1;
        //to make sure there is more than one element in the arraylist
        if (lastIndex >= 0) {

            destinationIDsOfItemsToBeStackedPreModification.remove(lastIndex);
        }

        

        for (int k = 0; k < destinationIDsOfItemsToBeStackedPreModification.size(); k++) {
            destinationIDsOfItemsToBeStacked.add(destinationIDsOfItemsToBeStackedPreModification.get(k));
        }

        System.out.println("Route path after the origin and ending point is removed for simplicity"
                + destinationIDsOfItemsToBeStacked);


            //order IDs to be stacked is the order IDs that is associated with each point in the route path
        for (int l = 0; l < destinationIDsOfItemsToBeStacked.size(); l++) {
            for (Integer OrderID : OrderIDandDeliveryDestinationID.keySet()) {
                //if the destination ID of the order is equal to the destination ID of the route path
                if (destinationIDsOfItemsToBeStacked.get(l) == OrderIDandDeliveryDestinationID.get(OrderID)
                        .getdestinationID()) {
                            //add the order ID to the list of order IDs to be stacked
                    OrderIDsToBeStacked.add(OrderID.toString());
                }
            }
        }

        System.out.println("This is  the list of the order IDs that is associated with each point in the route path "
                + OrderIDsToBeStacked);

    }

    public static void makeFinalStack() {
        ArrayList<String> finalOrders = new ArrayList<String>();
        for (int i = 0; i < OrderIDsToBeStacked.size(); i++) {
            finalOrders.add(OrderIDsToBeStacked.get(i));
        }

        System.out.println(
                "\nfinal list of  order IDs that will be delivered optimized to adhere to van capacity " + finalOrders);
///reverse the list so that it can be pushed to a stack
        Collections.reverse(finalOrders);
        ArrayList<String> reversed = new ArrayList<String>();
        for (int i = 0; i < finalOrders.size(); i++) {
            reversed.add(finalOrders.get(i));
        }

        System.out
                .println("this is the final order ID's list reveresed so that it can be pushed to a stack " + reversed);

        //add the order IDs to a stack
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < reversed.size(); i++) {
            stack.push(reversed.get(i));
        }
        System.out.println(
                "this is the final loading order arrangment of ORDERS. we are yet to find the corresponding item IDs  "
                        + stack);


        //add the item IDs to a stack

        Stack<String> stackOfItemsIDs = new Stack<String>();
        for (int i = 0; i < reversed.size(); i++) {
            stackOfItemsIDs.push(OrderIDandDeliveryDestinationID.get(Integer.parseInt(reversed.get(i))).getItemID());
        }
        System.out.println(
                "This is the final item list in the correct order that must be followed during the loading  of items ( LIFO) "
                        + stackOfItemsIDs);

        for (int i = 0; i < reversed.size(); i++) {
            System.out.println(
                    "  " + OrderIDandDeliveryDestinationID.get(Integer.parseInt(reversed.get(i))).getInvoiceID()
                            + " Item ID: " + stackOfItemsIDs.get(i)

                            + " Customer ID: "
                            + OrderIDandDeliveryDestinationID.get(Integer.parseInt(reversed.get(i))).getCustomerID()
                            + " Order ID: " + reversed.get(i)
                            + " Purchased Date: "
                            + OrderIDandDeliveryDestinationID.get(Integer.parseInt(reversed.get(i)))
                                    .getOrderCreatedAtDate()
                            + " Destination ID: "
                            + OrderIDandDeliveryDestinationID.get(Integer.parseInt(reversed.get(i))).getdestinationID()
                            + " Address: " + DestinationIDandAddress.get(OrderIDandDeliveryDestinationID
                                    .get(Integer.parseInt(reversed.get(i))).getdestinationID())

            );

        }

        /*
         * for (int i = 0; i < reversed.size(); i++) {
         * System.out.println("Item ID: " + stackOfItemsIDs.get(i)
         * + " Destination ID: "
         * + OrderIDandDeliveryDestinationID.get(Integer.parseInt(reversed.get(i))).
         * getdestinationID()
         * + " Address: "
         * + DestinationIDandAddress.get(OrderIDandDeliveryDestinationID.get(Integer.
         * parseInt(reversed.get(i))).getdestinationID())
         * 
         * );
         * 
         * }
         */

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        createorders();
        getHashMapKeys();
        bubblesortDeliveryDates();
        determineorderstobeloadedtotruck();
        travellingsalesmanProblemToFindShortestRoute();
        makeFinalStack();
    }
}
