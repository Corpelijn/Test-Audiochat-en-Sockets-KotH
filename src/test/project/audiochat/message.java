/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.audiochat;

import java.io.Serializable;

/**
 *
 * @author Bas
 */
public class Message implements Serializable {

    private String message;
    private Object data;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Message(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
