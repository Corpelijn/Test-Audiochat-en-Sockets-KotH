/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import Server.VoiceServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bas
 */
public class Client implements Serializable{

    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reader;
    private List<Client> knownClients;

    public Client(Socket socket, List<Client> knownClients) {
        this.socket = socket;
        this.knownClients = new ArrayList<>(knownClients);

        try {
            sender = new ObjectOutputStream(this.socket.getOutputStream());
            reader = new ObjectInputStream(this.socket.getInputStream());
        } catch (Exception ex) {
        }
        
        startMessageReader();
    }
    
    public void addClient(Client c)
    {
        knownClients.add(c);
    }
    
    public Socket getSocket()
    {
        return this.socket;
    }

    public void sendLastMessages(List<Message> lastMessages) {
        for (Message m : lastMessages) {
            this.sendMessage(m);
        }
    }

    public void sendMessage(Message message) {
        try {
            sender.writeObject(message);
        } catch (Exception ecx) {
            System.out.println(ecx.getMessage());
        }
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

                    System.out.println("Message received from " + socket.getInetAddress());
                    VoiceServer.lastMessages.add(object);
                    for(Client c : knownClients)
                    {
                        c.sendMessage(object);
                    }
                    try {
                        sender.writeObject(object);
                    } catch (IOException ex) {
                    }
                }
            }
            
        });
        t.start();
    }
}
