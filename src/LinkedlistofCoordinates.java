import java.io.Serializable;

public class LinkedlistofCoordinates implements Serializable {

    class Node implements Serializable {
        public int destinationID;
        public Double latitude;
        public Double longitude;
        public Node next;
        public OrderData orderDataAssociatdWithDestination;

        public Node(int destinationID,  Double latitude, Double longitude,
                OrderData orderDataAssociatdWithDestination) {
            this.destinationID = destinationID;
        
            this.latitude = latitude;
            this.longitude = longitude;
            this.next = null;
            this.orderDataAssociatdWithDestination = orderDataAssociatdWithDestination;

        }


    }

    public Node head ;

    public LinkedlistofCoordinates()  {
        this.head = null;
    }

    public void addDestination(int destinationID,  Double latitude, Double longitude,
            OrderData orderDataAssociatdWithDestination) {
        Node newNode = new Node(destinationID , latitude, longitude, orderDataAssociatdWithDestination);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }


    
}
