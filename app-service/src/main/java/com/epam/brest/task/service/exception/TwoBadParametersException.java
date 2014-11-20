package com.epam.brest.task.service.exception;

public class TwoBadParametersException extends BadParameterException {

    private Object obj2;

    public TwoBadParametersException(String message, Object obj, Object obj2){
        super(message, obj);
        this.obj2 = obj2;
    }

    public Object getSecondObjectOfException(){
        return obj2;
    }
}
