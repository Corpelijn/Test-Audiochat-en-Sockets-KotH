/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.util.Date;

/**
 *
 * @author Bas
 */
public class AudioMessage extends Message {

    public AudioMessage(String sender, Object data) {
        super.data = data;
        super.header = "audio";
        super.sender = sender;
        super.time = new Date();
    }
    
}
