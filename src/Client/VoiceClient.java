/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Shared.InfoMessage;
import Shared.Message;
import Shared.TextMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
    
    private int clientID = -1;

    public void start() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String serverAddress = input.nextLine();

        socket = new Socket(serverAddress, 9090);
        sender = new ObjectOutputStream(this.socket.getOutputStream());
        reader = new ObjectInputStream(this.socket.getInputStream());
        
        System.out.print("Client: Enter a nickname for the chat: ");
        String name = input.nextLine();
        if(name.equals(""))
            name = "Ballenzuiger";

        startMessageReader();
        startMessageWriter();
        
        sender.writeObject(new InfoMessage(name, "CLIENT_NAME"));
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
                
                if(object instanceof InfoMessage)
                {
                    InfoMessage mess = (InfoMessage)object;
                    if(mess.getDefine().equals("CLIENT_ID"))
                    {
                        this.clientID = (int)mess.getData();
                        System.out.print("\r<< ClientID received from server >>\n>");
                        continue;
                    }
                }

                System.out.print("\r" + object.getTime() + ": " + object.getSenderName() + " says: ");
                System.out.println(object.getData());
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

                    sender.writeObject(new TextMessage(this.clientID, newMessage));
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
            
            //System.exit(0);
        });
        t.start();
    }
}
