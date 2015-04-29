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
public class command implements Serializable {
    private String text;
    
    public String getText()
    { 
        return this.text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public command(String text)
    {
        this.text = text;
    }
}
