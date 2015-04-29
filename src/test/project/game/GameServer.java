/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Dennis
 */
public class GameServer {
    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                System.out.println("Connecting...");
                Socket socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    //out.println(new Date().toString());
                    
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    //out.println("Date object");
                    os.writeObject(new Date());
                    //out.println("String object");
                    os.writeObject(new String("Koek"));
                    Thread.sleep(1000);
                    os.writeObject(new Date());
                    System.out.println("Connected to: " + socket.getRemoteSocketAddress().toString());
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }
}
