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

    public AudioMessage(int sender, Object data) {
        super.setHeader("audio");
        super.setData(data);
        super.setTime(new Date());
        super.setSender(sender);
    }
    
}
