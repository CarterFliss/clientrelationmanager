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

    /**
     * Enumerates different message levels
     */
    public enum Level {

        /**
         *
         */
        ERROR,

        /**
         *
         */
        INFO,

        /**
         *
         */
        SUCCESS;
        
        /**
         * Returns the message type
         * @return
         */
        public String getString() {
            return this.name();
        }
    }
    
    //creation of Message Objects.  One w/ parameters and another w/out.

    /**
     * Initialization of a Messages object with no parameters
     */
    public Messages(){
        
    }
    
    /**
     * Initialization of Messages object with parameters
     * @param type
     * @param message
     */
    public Messages(Level type,String message){
        this.type = type;
        this.message = message;
    }

    //getters and setters

    /**
     * Gets the Messages type
     * @return
     */
    public Level getType(){
        return type;
    }

    /**
     * Sets the Messages type
     * @param type
     */
    public void setType(Level type){
        this.type = type;
    }

    /**
     * Gets the Messages object
     * @return
     */
    public String getMessage(){
        return message;
    }

    /**
     * Sets the Messages object
     * @param message
     */
    public void setMessage(String message){
        this.message = message;
    }

    //puts message into String for testing purposes
    @Override
    public String toString(){
        return "Message{" + "type=" + type + ", message=" + message + '}';
    }
}
