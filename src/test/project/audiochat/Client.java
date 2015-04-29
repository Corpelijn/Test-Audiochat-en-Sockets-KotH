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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bas
 */
public class Client extends Thread {

    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream reader;

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Client(Socket socket) {
        this.socket = socket;
        try {
            sender = new ObjectOutputStream(this.socket.getOutputStream());
            //reader = new ObjectInputStream(socket.getInputStream());
        } catch (Exception ex) {
        }
    }

    @Override
    public void run() {
        int count = 0;
        try {
            while (true) {
                try {
                    sender.writeObject(new command("testje" + count));
                    count++;
                    if (count == 3) {
                        sender.writeObject(new command("exit"));
                        break;
                    }
                } catch (IOException ex) {
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
