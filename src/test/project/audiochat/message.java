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
public class message implements Serializable {

    private String message;

    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public message(String message)
    {
        this.message = message;
    }
}
