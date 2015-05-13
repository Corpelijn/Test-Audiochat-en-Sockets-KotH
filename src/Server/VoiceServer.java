/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Shared.Client;
import Shared.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bas
 */
public class VoiceServer {

    private List<Client> connectedClients;
    public static List<Message> lastMessages;

    public VoiceServer() {
        this.connectedClients = new ArrayList<>();
        VoiceServer.lastMessages = new ArrayList<>();
    }

    public void start() throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        
        try {
            writeMessage("Server is online");

            while (true) {
                System.out.println("Waiting for clients to connect...");

                Socket socket = listener.accept();
                writeMessage("Client " + socket.getInetAddress() + " connected");

                try {

                    Client c = new Client(socket, connectedClients);
                    for(Client cli : connectedClients)
                    {
                        cli.addClient(c);
                    }
                    connectedClients.add(c);
                    
                    c.sendLastMessages(lastMessages);
                } catch (Exception ex) {
                } finally {
                    //socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

    public void writeMessage(String message) {
        System.out.println(message);
        lastMessages.add(new Message(message));
    }
}
