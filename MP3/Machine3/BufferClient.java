import java.io.*;
import java.net.*;

public class BufferClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java BufferClient <server ip> <server port>");
            return;
        }

        String serverIp = args[0];
        int serverPort = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(serverIp, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            StringBuilder buffer = new StringBuilder();
            int c;
            // Buffer the characters until a newline is received
            while ((c = in.read()) != -1) {
                buffer.append((char) c);
                if (c == '\n') {
                    break; // Stop when a newline character is received
                }
            }
            // Print the whole buffered string at once
            System.out.print(buffer.toString());
        }
    }
}

