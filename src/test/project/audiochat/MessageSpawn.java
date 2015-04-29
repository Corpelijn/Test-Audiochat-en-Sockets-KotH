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
public class MessageSpawn extends Thread
{

    private VoiceServer parent;
    
    public MessageSpawn(VoiceServer p)
    {
        parent = p;
    }
    
    @Override
    public void run() {
        while(true)
            parent.SendMessage("message");
    }
    
}