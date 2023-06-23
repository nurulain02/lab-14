import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClientApp {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 8087;

        try {
            // Connect to the server
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Connected to the server.");

            // Create input and output streams
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

            // Send customer names to the server
            String[] customerNames = {"John Doe", "Jane Smith", "Adam", "Non-existing Customer"};
            for (String name : customerNames) {
                outStream.writeObject(name);
                outStream.flush();
                System.out.println("Sent customer name: " + name);

                // Receive customer object from the server
                Object receivedObject = inStream.readObject();
                if (receivedObject instanceof Customer) {
                    Customer customer = (Customer) receivedObject;
                    System.out.println("Received customer object:");
                    System.out.println("Customer ID: " + customer.getCustomerId());
                    System.out.println("Customer Name: " + customer.getName());
                    // Display other customer details as needed
                } else {
                    System.out.println("Customer not found.");
                }
            }

            // Close the streams and socket
            outStream.close();
            inStream.close();
            socket.close();
            System.out.println("Disconnected from the server.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
