/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.game;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while(s.isConnected()){
            //String answer = input.readLine();
            //String answer = d.toString();
            //JOptionPane.showMessageDialog(null, answer);
            
            if(input.ready()){
               try{
                System.out.println(input.readLine());
                Object d = (Object)is.readObject();
                //if(d instanceof Date){
                    System.out.println(d.toString());
                //}
               }
               catch(EOFException exc) {
                   System.out.println("Exc :" + exc.getMessage());
               }
            }
            
            //try{
                
            //}
            //catch(EOFException exc){
               // System.out.println(exc.getMessage());
            //}
        }
        System.out.println("Connection lost");
        System.exit(0);
    }
}
