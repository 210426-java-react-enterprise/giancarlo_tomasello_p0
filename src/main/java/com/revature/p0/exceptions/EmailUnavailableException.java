package com.revature.p0.exceptions;

public class EmailUnavailableException extends ResourcePersistenceException{
    public EmailUnavailableException(String msg){
        super(msg);
    }
}
