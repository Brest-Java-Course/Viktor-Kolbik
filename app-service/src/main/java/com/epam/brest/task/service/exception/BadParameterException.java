package com.epam.brest.task.service.exception;

/**
 * Created by simpson on 20/11/14.
 */
public class BadParameterException extends RuntimeException {

    protected Object obj;

    public BadParameterException(String message, Object obj){
        super(message);
        this.obj = obj;
    }

    public Object getObjectOfException(){
        return obj;
    }
}
