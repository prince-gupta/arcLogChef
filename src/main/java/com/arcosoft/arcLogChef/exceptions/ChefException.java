package com.arcosoft.arcLogChef.exceptions;

/**
 * Created by princegupta on 06/12/17.
 */
public class ChefException extends Exception {

    private String errorCode="Chef_Exception";

    public ChefException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }

    public String getErrorCode(){
        return this.errorCode;
    }
}
