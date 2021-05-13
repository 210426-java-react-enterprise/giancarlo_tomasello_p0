package com.revature.p0.exceptions;

public class UsernameUnavailableException extends ResourcePersistenceException{
    public UsernameUnavailableException(String msg){
        super(msg);
    }
}
