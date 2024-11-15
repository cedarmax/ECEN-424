import java.io.*;
import java.net.*;

public class NaiveClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java NaiveClient <server ip> <server port>");
            return;
        }

        String serverIp = args[0];
        int serverPort = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(serverIp, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            int c;
            // Print each character as it's received
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
                if (c == '\n') {
                    break; // Stop when a newline character is received
                }
            }
        }
    }
}

