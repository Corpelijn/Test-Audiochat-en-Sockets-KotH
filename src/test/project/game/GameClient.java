/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Dennis
 */
public class GameClient {
       public static void main(String[] args) throws IOException, ClassNotFoundException {
        //String serverAddress = /*"127.0.0.1";*/JOptionPane.showInputDialog(
            //"Enter IP Address of a machine that is\n" +
            //"running the date service on port 9090:");
        Socket s = new Socket("127.0.0.1", 9090);
        ObjectInputStream is = new ObjectInputStream(s.getInputStream());
        //BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        while(s.isConnected()){
            Object d = (Object)is.readObject();
            //String answer = input.readLine();
            //String answer = d.toString();
            //JOptionPane.showMessageDialog(null, answer);
            
            System.out.println(d.toString());
        }
        System.exit(0);
    }
}
