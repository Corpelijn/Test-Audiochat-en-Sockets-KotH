/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

/**
 *
 * @author Bas
 */
public class TextMessage extends Message {

    public TextMessage(Object data) {
        super.data = data;
        super.header = "text";
    }

}
