/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Bas
 */
public class Message implements Serializable {
    public String header;
    public Object data;
    public Date time;
    public String sender;
    
    
    public String getHeader()
    {
        return this.header;
    }
    
    public void setHeader(String value)
    {
        this.header = value;
    }
    
    public String getTime()
    {
        SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
        return dt.format(this.time);
    }
    
    public void setTime(Date time)
    {
        this.time = time;
    }
    
    public String getSender()
    {
        return this.sender;
    }
    
    public void setSender(String sender)
    {
        this.sender = sender;
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
