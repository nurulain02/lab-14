import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientApp {
    public static void main(String[] args) {
        System.out.println("UDPClientApp: Demonstration of UDP Client-Side Application.");

        String serverAddress = "localhost";
        int serverPort = 8083;

        try {
            DatagramSocket datagramSocket = new DatagramSocket();

            String sentence = "Hello, world! This is a test sentence.";
            byte[] sendData = sentence.getBytes();
            InetAddress serverIPAddress = InetAddress.getByName(serverAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverPort);
            datagramSocket.send(sendPacket);

            byte[] receivedData = new byte[65535];
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            datagramSocket.receive(receivedPacket);

            receivedData = receivedPacket.getData();
            String result = new String(receivedData, 0, receivedPacket.getLength());

            System.out.println("Result received from server: " + result);

            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("UDPClientApp: End of program.");
    }
}
