import java.io.Serializable;
import java.rmi.server.LoaderHandler;
import java.time.LocalDate;
import java.util.HashMap;

public class OrderData implements Serializable {

    private int destinationID;
    private Integer OrderID;
    private Integer ItemID;
    private LocalDate orderCreatedAtDate;

    public OrderData(int destinationID, int i, LocalDate orderCreatedAtDate , Integer OrderID) {
        this.destinationID = destinationID;
        this.OrderID = OrderID;
        this.orderCreatedAtDate = orderCreatedAtDate;
        this.ItemID = i;
      
    }
    public int getDestinationID() {
        return destinationID;
    }
    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }
    public Integer getItemID() {
        return ItemID;
    }
    public void setItemID(Integer itemID) {
        ItemID = itemID;
    }
    public LocalDate getOrderCreatedAtDate() {
        return orderCreatedAtDate;
    }
    public void setOrderCreatedAtDate(LocalDate orderCreatedAtDate) {
        this.orderCreatedAtDate = orderCreatedAtDate;
    }
    public Integer getOrderID() {
        return OrderID;
    }
    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

}
