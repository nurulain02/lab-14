import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerApp {
    public static void main(String[] args) {
        System.out.println("UDPServerApp: Demonstration of UDP Server-Side Application.");

        int portNo = 8083;

        try {
            DatagramSocket datagramSocket = new DatagramSocket(portNo);

            while (true) {
                byte[] receivedData = new byte[65535];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                datagramSocket.receive(receivedPacket);

                receivedData = receivedPacket.getData();
                String sentence = new String(receivedData, 0, receivedPacket.getLength());

                int vowelCount = 0;
                int consonantCount = 0;
                int punctuationCount = 0;

                for (char c : sentence.toCharArray()) {
                    if (Character.isLetter(c)) {
                        c = Character.toLowerCase(c);
                        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                            vowelCount++;
                        } else {
                            consonantCount++;
                        }
                    } else if (Character.isWhitespace(c)) {
                        continue;
                    } else {
                        punctuationCount++;
                    }
                }

                String result = "Vowels: " + vowelCount + ", Consonants: " + consonantCount + ", Punctuation: " + punctuationCount;

                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();
                byte[] resultData = result.getBytes();
                DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, clientAddress, clientPort);
                datagramSocket.send(resultPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("UDPServerApp: End of program.");
    }
}
