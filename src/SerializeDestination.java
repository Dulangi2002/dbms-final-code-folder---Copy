import java.io.Serializable;
import java.time.LocalDate;

public class SerializeDestination implements Serializable {
   

    private int destinationID;
    private String address;
    private double latitude;
    private double longitude;
    private int orderID;
    private int itemID;
    private LocalDate purchasedDate;

    public SerializeDestination(int destinationID, String address, double latitude, double longitude, int orderID, int itemID, LocalDate localDate) {
        this.destinationID = destinationID;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.orderID = orderID;
        this.itemID = itemID;
        this.purchasedDate = localDate;
    }

    public int getDestinationID() {
        return destinationID;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getItemID() {
        return itemID;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    
}
