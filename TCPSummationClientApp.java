import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPSummationClientApp {

    public static void main(String[] args) {
        
        System.out.println("\n\tExecuting TCPSummationClientApp");
        
        try {
            
            // Connect to the server
            String serverIp = "127.0.0.1";
            int serverPort = 8087;
            Socket socket = new Socket(serverIp, serverPort);
            
            // Send three numbers to the server
            int number1 = 5;
            int number2 = 10;
            int number3 = 3;
            
            OutputStream outStream = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outStream);
            dos.writeInt(number1);
            dos.writeInt(number2);
            dos.writeInt(number3);
            
            // Receive results from the server
            InputStream inStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inStream);
            int sum = dis.readInt();
            int multiplication = dis.readInt();
            
            System.out.println("\tFrom server: Sum = " + sum + ", Multiplication = " + multiplication);
            // From server: Sum = 18, Multiplication = 150
            
            // Close the connection
            socket.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
