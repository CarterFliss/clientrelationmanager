/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
/**
 *
 * @author Carter
 * Messages POJO
 */
public class Messages implements Serializable{
    //initializing class-wide variables
    private Level type;
    private String message;
    
    //enumerates message levels
    public enum Level {
        ERROR, INFO, SUCCESS;
        
        public String getString() {
            return this.name();
        }
    }
    
    //creation of Message Objects.  One w/ parameters and another w/out.
    public Messages(){
        
    }
    
    public Messages(Level type,String message){
        this.type = type;
        this.message = message;
    }

    //getters and setters
    public Level getType(){
        return type;
    }

    public void setType(Level type){
        this.type = type;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    //puts message into String for testing purposes
    @Override
    public String toString(){
        return "Message{" + "type=" + type + ", message=" + message + '}';
    }
}
