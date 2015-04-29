/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.audiochat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bas
 */
public class ClientSender extends Thread {

    private ObjectOutputStream stream;

    public ClientSender(ObjectOutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        while (true) {
            String command = JOptionPane.showInputDialog("Type a new command");
            try {
                stream.writeObject(new command(command));
            } catch (IOException ex) {
            }
        }
    }
}
