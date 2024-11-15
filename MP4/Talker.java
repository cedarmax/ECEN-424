import java.net.*;
import java.util.Scanner;

public class Talker {
    public static void main(String[] args) {
        // Parse command-line arguments
        int talkerPort = Integer.parseInt(args[0]);
        String listenerIP = args[1];
        int listenerPort = Integer.parseInt(args[2]);

        try (DatagramSocket talkerSocket = new DatagramSocket(talkerPort)) {
            // Get message input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string (up to 50 characters): ");
            String message = scanner.nextLine();
            scanner.close();

            // Split into 10-character chunks
            String[] messages = new String[6];
            messages[0] = "5";  // Message 0: number of messages to send
            for (int i = 1; i <= 5; i++) {
                int start = (i - 1) * 10;
                messages[i] = message.substring(start, Math.min(start + 10, message.length()));
            }

            // Set up communication with Listener
            InetAddress listenerAddress = InetAddress.getByName(listenerIP);
            int sequenceNumber = 0;

            for (int i = 0; i <= 5; i++) {
                boolean ackReceived = false;
                while (!ackReceived) {
                    // Send message with sequence number
                    String framedMessage = sequenceNumber + messages[sequenceNumber];
                    byte[] sendData = framedMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, listenerAddress, listenerPort);
                    talkerSocket.send(sendPacket);
                    System.out.println("Sent: " + framedMessage);

                    // Wait for ACK
                    try {
                        talkerSocket.setSoTimeout(2000);
                        byte[] ackData = new byte[10];
                        DatagramPacket ackPacket = new DatagramPacket(ackData, ackData.length);
                        talkerSocket.receive(ackPacket);

                        String ackMessage = new String(ackPacket.getData()).trim();
                        if (Integer.parseInt(ackMessage) == sequenceNumber + 1) {
                            System.out.println("Received ACK for: " + sequenceNumber);
                            sequenceNumber++;
                            ackReceived = true;
                        }
                    } catch (SocketTimeoutException e) {
                        System.out.println("Timeout, resending message " + sequenceNumber);
                    }
                }
            }
            System.out.println("All messages sent.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

