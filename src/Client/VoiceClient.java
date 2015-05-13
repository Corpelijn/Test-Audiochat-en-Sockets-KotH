/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Shared.Message;
import Shared.TextMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bas
 */
public class VoiceClient {

    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reader;
    private String name;

    public void start() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String serverAddress = input.nextLine();

        socket = new Socket(serverAddress, 9090);
        sender = new ObjectOutputStream(this.socket.getOutputStream());
        reader = new ObjectInputStream(this.socket.getInputStream());
        
        System.out.print("Client: Enter a nickname for the chat: ");
        this.name = input.nextLine();
        if(this.name.equals(""))
            this.name = "Ik ben een vieze eikel";

        startMessageReader();
        startMessageWriter();
    }

    private void startMessageReader() {
        Thread t = new Thread(() -> {
            while (true) {
                Message object = null;
                try {
                    object = (Message) reader.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    continue;
                }

                System.out.print("\r" + object.getTime() + ": " + object.getSender() + " says: ");
                System.out.println(object.data);
                System.out.print(">");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VoiceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
    }

    private void startMessageWriter() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Scanner input = new Scanner(System.in);
                    String newMessage = input.nextLine();

                    if (newMessage.startsWith("/nickname")) {
                        name = newMessage.replace("/nickname ", "");
                        System.out.print(">");
                        continue;
                    }
                    else if (newMessage.startsWith("/exit")) {
                        break;
                    }

                    sender.writeObject(new TextMessage(this.name, newMessage));
                    System.out.print(">");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VoiceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            System.exit(0);
        });
        t.start();
    }
}
