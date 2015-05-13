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
public class TextMessage extends Message {

    public TextMessage(int sender, Object data) {
        super.setHeader("audio");
        super.setData(data);
        super.setTime(new Date());
        super.setSender(sender);
    }

}
