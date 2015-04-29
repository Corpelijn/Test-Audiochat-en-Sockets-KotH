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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class VoiceClient {

    /**
     * Runs the client as an application. First it displays a dialog box asking
     * for the IP address or hostname of a host running the date server, then
     * connects to it and displays the date that it serves.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String serverAddress = /*"127.0.0.1";*/ JOptionPane.showInputDialog(
                        "Enter IP Address of a machine that is\n"
                        + "running the date service on port 9090:");

        Socket s = new Socket(serverAddress, 9090);
        ObjectInputStream reader;
        ObjectOutputStream sender;
        
        try {
            reader = new ObjectInputStream(s.getInputStream());
            sender = new ObjectOutputStream(s.getOutputStream());
        } catch (Exception exc) {
            return;
        }

        while (true) {
            String command = JOptionPane.showInputDialog("Type a new command");
            sender.writeObject(new command(command));
            
            
            Object o;
            try {
                o = reader.readObject();

            } catch (Exception ecx) {
                return;
            }

            if (o == null) {
                continue;
            }

            command readed = (command) o;
            String answer = readed.getText();

            if (answer.equals("exit")) {
                break;
            }

            JOptionPane.showMessageDialog(null, answer);
        }
        System.exit(0);
    }
}
