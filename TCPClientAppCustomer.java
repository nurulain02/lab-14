import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClientApp {
    
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            // Create input and output streams
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Send customer IDs to the server
            int[] customerIds = {1001, 1002, 1003, 1004};
            for (int customerId : customerIds) {
                outputStream.writeInt(customerId);
                outputStream.flush();

                // Receive customer object from the server
                Customer customer = (Customer) inputStream.readObject();

                // Display customer details
                if (customer != null) {
                    System.out.println("Customer ID: " + customer.getCustomerId());
                    System.out.println("Name: " + customer.getName());
                    System.out.println("Email: " + customer.getEmail());
                } else {
                    System.out.println("Customer not found.");
                }

                System.out.println("--------------------");
            }

            // Close the streams and socket
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
