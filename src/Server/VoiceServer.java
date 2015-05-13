/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Shared.Client;
import Shared.Message;
import Shared.TextMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bas
 */
public class VoiceServer {

    private final List<Client> connectedClients;
    public static List<Message> lastMessages;

    public VoiceServer() {
        this.connectedClients = new ArrayList<>();
        VoiceServer.lastMessages = new ArrayList<>();
    }

    public void start() throws IOException {
        try (ServerSocket listener = new ServerSocket(9090)) {
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
        }
    }

    public void writeMessage(String message) {
        System.out.println(message);
        lastMessages.add(new TextMessage(message));
    }
}
