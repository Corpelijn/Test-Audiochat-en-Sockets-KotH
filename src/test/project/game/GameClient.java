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
        while (true){
            Object o;
            try {
                o = is.readObject();
            } catch (Exception exc){
                return;
            }
            
            if (o == null){
                continue;
            }
            
            if(o instanceof Date){
                System.out.println(o.toString());
            }
            else if (o instanceof String){
                System.out.println(o);
            }
        }
    }
}
