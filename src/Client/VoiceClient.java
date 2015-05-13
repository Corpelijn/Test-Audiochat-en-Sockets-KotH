/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Shared.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bas
 */
public class VoiceClient {
    
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reader;
    
    public void start() throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String serverAddress = input.nextLine();
        
        socket = new Socket(serverAddress, 9090);
        sender = new ObjectOutputStream(this.socket.getOutputStream());
        reader = new ObjectInputStream(this.socket.getInputStream());
        
        startMessageReader();
        startMessageWriter();
    }
    
    private void startMessageReader()
    {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                {
                    Message object = null;
                    try {
                        object = (Message)reader.readObject();
                    } catch (Exception ex) {
                        continue;
                    }
                    
                    System.out.println(object.message);
                    System.out.print(">");
                }
            }
            
        });
        t.start();
    }
    
    private void startMessageWriter()
    {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true)
                {
                    try {
                        Scanner input = new Scanner(System.in);
                        String newMessage = input.nextLine();
                        sender.writeObject(new Message(newMessage));
                        System.out.print(">");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        continue;
                    }
                }
            }
            
        });
        t.start();
    }
}
