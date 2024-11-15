import java.net.*;
import java.util.Random;

public class Listener {
    public static void main(String[] args) {
        int listenerPort = Integer.parseInt(args[0]);
        String talkerIP = args[1];

        try (DatagramSocket listenerSocket = new DatagramSocket(listenerPort)) {
            int expectedSequence = 0;
            StringBuilder receivedMessage = new StringBuilder();

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                listenerSocket.receive(receivePacket);

                String received = new String(receivePacket.getData()).trim();
                int receivedSeq = Integer.parseInt(received.substring(0, 1));
                String messageContent = received.substring(1);

                if (receivedSeq == 0) {
                    int numMessages = Integer.parseInt(messageContent);
                    System.out.println("Expecting " + numMessages + " messages.");
                } else if (receivedSeq == expectedSequence) {
                    receivedMessage.append(messageContent);
                    System.out.println("Received message: " + received);

                    Random rand = new Random();
                    if (rand.nextDouble() < 0.5) {
                        System.out.println("Simulated loss for ACK " + expectedSequence);
                    } else {
                        byte[] ackData = String.valueOf(expectedSequence + 1).getBytes();
                        DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length,
                                receivePacket.getAddress(), receivePacket.getPort());
                        listenerSocket.send(ackPacket);
                        System.out.println("Sent ACK for: " + receivedSeq);
                        expectedSequence++;
                    }
                }

                if (receivedSeq == 5) {
                    System.out.println("Full message received: " + receivedMessage.toString());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

