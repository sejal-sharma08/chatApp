import java.net.*; 
import java.io.*;

class Server {
    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("System is ready to accept connection");
            System.out.println("waiting...");
            socket = server.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        // this thread will keep giving us data after reading
        Runnable r1 = ()->{
            System.out.println("reader started...");

            while(true) {
                try {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat");
                        break;
                    }
                    System.out.println("Client: " + msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void startWriting() {
        // this thread will take data from the user and then send it back to the client
        Runnable r2 = ()->{

        };
    }
    public static void main(String[] args) {
        System.out.println("this is server...going to start server");
        new Server();
    } 
}