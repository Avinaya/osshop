package com.berrybytes.osshop.exception;

import java.io.Serial;

public class AlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    AlreadyExistException(String msg){
        super(msg);
    }
    
}
