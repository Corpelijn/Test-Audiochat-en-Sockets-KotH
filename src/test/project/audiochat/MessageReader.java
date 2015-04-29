/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.audiochat;

import java.io.ObjectInputStream;


/**
 *
 * @author Bas
 */
public class MessageReader extends Thread
{

    private VoiceServer parent;
    private ObjectInputStream reader;
    
    public MessageReader(VoiceServer p, ObjectInputStream reader)
    {
        parent = p;
        this.reader = reader;
    }
    
    @Override
    public void run() {
        while (true) {

            Object o;
            try {
                o = reader.readObject();

            } catch (Exception ecx) {
                return;
            }

            if (o == null) {
                continue;
            }

            Message readed = (Message) o;
            String answer = "";
            if(readed.getMessage().equals("string"))
            {
                answer = (String)readed.getData();
            }
            else if(readed.getMessage().equals("audio"))
            {
                answer = "audio fragment";
            }
            

            if (answer.equals("exit")) {
                parent.SendMessage(answer);
                parent.Exit();
                break;
            }

            parent.SendMessage(answer);
        }
    }
    
}