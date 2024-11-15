import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private static String str;
    private static int N;

    public static void main(String[] args) throws IOException {
    	//Prevent illegal lack of input
        if (args.length != 2) {
            System.out.println("Incorrect input.  Please run as follows: java Server <server port> <max clients>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        int maxClients = Integer.parseInt(args[1]);

        //Create a thread pool to handle clients
        ExecutorService threadPool = Executors.newFixedThreadPool(maxClients);

        //Create a server socket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            //Get the string and the N value from the user
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a string: ");
            str = console.readLine();
            System.out.println("Enter a positive integer (N): ");
            N = Integer.parseInt(console.readLine());

            while (true) {
                //Accept client connections
                Socket clientSocket = serverSocket.accept();
                threadPool.execute(new ClientHandler(clientSocket, str, N));
            }
        }
    }

    //ClientHandler class to handle each client in a separate thread
    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private String str;
        private int N;

        public ClientHandler(Socket clientSocket, String str, int N) {
            this.clientSocket = clientSocket;
            this.str = str;
            this.N = N;
        }

        @Override
        public void run() {
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                for (int i = 1; i <= N; i++) {
                    out.print(str);
                    if (i == N) {
                        out.println(); 
			//End the last transmission with a newline
                    }
                    out.flush();
                    Thread.sleep(1000); 
		    //1-second delay between transmissions
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
