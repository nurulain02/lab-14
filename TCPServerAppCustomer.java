// TCPServerApp.java
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServerApp {
    
    private static final Logger LOGGER = Logger.getLogger(TCPServerApp.class.getName());

    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.log(Level.INFO, "TCPServerApp: Server started and listening on port {0}", port);

            while (true) {
                LOGGER.log(Level.INFO, "TCPServerApp: Waiting for client connection...");

                Socket clientSocket = serverSocket.accept();
                LOGGER.log(Level.INFO, "TCPServerApp: Client connected: {0}", clientSocket.getInetAddress());

                // Create input/output streams
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                try {
                    // Read customer ID from client
                    int customerId = inputStream.readInt();
                    LOGGER.log(Level.INFO, "TCPServerApp: Received customer ID: {0}", customerId);

                    // Search for customer by ID
                    Customer customer = findCustomerById(customerId);

                    // Send customer object to client
                    outputStream.writeObject(customer);
                    LOGGER.log(Level.INFO, "TCPServerApp: Sent customer object to client");

                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "TCPServerApp: Error reading/writing data from/to client: {0}", e.getMessage());
                } finally {
                    // Close streams and client socket
                    inputStream.close();
                    outputStream.close();
                    clientSocket.close();
                    LOGGER.log(Level.INFO, "TCPServerApp: Closed client connection");
                }
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "TCPServerApp: Error starting the server: {0}", e.getMessage());
        }
    }

    private static Customer findCustomerById(int customerId) {
        // TODO: Implement customer search logic based on customer ID
        // This method should retrieve the customer object from a data source (e.g., database) using the provided customer ID
        // For demonstration purposes, a dummy implementation is shown below
        
        if (customerId == 1) {
            Customer customer = new Customer();
            customer.setCustomerId(1);
            customer.setName("John Doe");
            customer.setEmail("johndoe@example.com");
            return customer;
        } else {
            return null; // Customer not found
        }
    }
}
