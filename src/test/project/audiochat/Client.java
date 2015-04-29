/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.audiochat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bas
 */
public class Client extends Thread {

    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reader;
    private List<Message> messages;

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Client(Socket socket, VoiceServer parent) {
        this.socket = socket;
        this.messages = new ArrayList<>();
        try {
            sender = new ObjectOutputStream(this.socket.getOutputStream());
            reader = new ObjectInputStream(this.socket.getInputStream());
        } catch (Exception ex) {
        }
        
        MessageReader ms = new MessageReader(parent, reader);
        ms.start();
    }

    public synchronized void addMessage(String message) {
        if(message.equals("audio"))
            messages.add(new Message("audio", message));
        else
            messages.add(new Message("string", message));
    }

    @Override
    public void run() {
        messages.add(new Message("string", "Welcome"));
        try {
            while (true) {
                synchronized (messages) {
                    if (messages.size() > 0) {
                        try {
                            sender.writeObject(messages.get(0));
                            if (messages.get(0).equals("exit")) {
                                break;
                            }
                            messages.remove(0);
                        } catch (IOException ex) {
                        }
                    }
                }

            }
        } finally {
            try {
                sender.close();
                this.socket.close();
            } catch (IOException ex) {
            }

        }
    }

}
