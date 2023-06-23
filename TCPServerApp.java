import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class TCPServerApp {
    
    private static final int PORT = 8080;
    
    private static final Logger logger = Logger.getLogger(TCPServerApp.class.getName());
    
    public static void main(String[] args) {
        try {
            // Set up the logger
            FileHandler fileHandler = new FileHandler("server.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
            
            logger.info("TCP Server is running");
            
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            
            while (true) {
                // Accept client connection
                Socket clientSocket = serverSocket.accept();
                logger.info("Accepted client connection from " + clientSocket.getInetAddress());
                
                // Process client request
                processClientRequest(clientSocket);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error in TCP Server: " + e.getMessage(), e);
        }
    }
    
    private static void processClientRequest(Socket clientSocket) {
        try {
            // Create input and output streams for client communication
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            
            // Wrap input and output streams with readers and writers
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream), true);
            
            // Read customer name from the client
            String customerName = reader.readLine();
            logger.info("Received customer name from client: " + customerName);
            
            // Find customer based on the name (perform your logic here)
            Customer customer = findCustomerByName(customerName);
            
            // Send customer object to the client
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(customer);
            logger.info("Sent customer object to client");
            
            // Close streams and client socket
            objectOutputStream.close();
            writer.close();
            reader.close();
            clientSocket.close();
            
            logger.info("Closed client connection");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error in processing client request: " + e.getMessage(), e);
        }
    }
    
    private static Customer findCustomerByName(String name) {
        // Perform your logic to find the customer based on the name
        // Return the customer object
        
        return null; // Replace with your implementation
    }
}
