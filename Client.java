import java.net.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;
import java.io.*;

public class Client extends JFrame {
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    // Declare components
    private JLabel heading = new JLabel("Client Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    private Font font = new Font("Roboto", Font.PLAIN, 20);

    public Client() {
        try {
            // System.out.println("Sending request to server");
            // socket = new Socket("127.0.0.1", 7777);
            // System.out.println("Connection done");
            // br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // out = new PrintWriter(socket.getOutputStream());

            createGUI();
            // startReading();
            // startWriting();

        } catch (Exception e) {
            
        }
    }

    private void createGUI() {
        this.setTitle("Client Messager[END]");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void startReading() {
        // this thread will keep giving us data after reading
        Runnable r1 = ()->{
            System.out.println("reader started...");

            try{
                while(true) {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Server terminated the chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Server: " + msg);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Connection closed");
            }
        };
        new Thread(r1).start();
    }

    public void startWriting() {
        // this thread will take data from the user and then send it back to the client
        Runnable r2 = ()->{
            System.out.println("Writer started...");

            try{
                while(!socket.isClosed()) {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();

                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("this is client..");
        new Client();
    } 
}
