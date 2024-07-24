package com.example.seshatrpgauxiliary.domain.exceptions.custom;

public class SeshatNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;



    public SeshatNotFoundException(String message){
        super(message);
    }

}

