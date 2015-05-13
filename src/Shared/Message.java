/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.io.Serializable;

/**
 *
 * @author Bas
 */
public class Message implements Serializable {
    public String header;
    public Object data;
    
    public String getHeader()
    {
        return this.header;
    }
    
    public void setHeader(String value)
    {
        this.header = value;
    }
    
    public Object getData()
    {
        return this.data;
    }
    
    public void setData(Object value)
    {
        this.data = value;
    }
}
