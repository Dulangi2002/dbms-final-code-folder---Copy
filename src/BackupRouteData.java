import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackupRouteData implements Serializable {

   

    // serialize the destination IDs
    public static void serializeDestinationIDs(ArrayList<Integer> destinationIDs, String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(destinationIDs);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved in src/DestinationIDs.ser");
    }

     /* 
    public static ArrayList<Integer> deserializeDestinationIDs(String filename)throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<Integer> destinationIDs = (ArrayList<Integer>) in.readObject();

        in.close();
        fileIn.close();
        return destinationIDs;

    }

    public static void serializeData(LinkedlistofCoordinates route, String string) {

        try {
            FileOutputStream fileOut = new FileOutputStream("src/Data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in src/Data.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

  
}
