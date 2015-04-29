/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.audiochat;


/**
 *
 * @author Bas
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class VoiceServer {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        ArrayList<Socket> clients = new ArrayList<>();
        
        try {
            while (true) {
                System.out.println("Connecting...");
                Socket socket = listener.accept();

                Client c = new Client(socket);
                c.start();
            }
        }
        finally {
            listener.close();
        }
    }
}
