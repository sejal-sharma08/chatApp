import java.net.*;

public class Client {

    Socket socket;

    public Client() {
        try {
            System.out.println("Sending request to server");
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("Connection done");
        } catch (Exception e) {
            
        }
    }
    public static void main(String[] args) {
        System.out.println("this is client..");
    } 
}
