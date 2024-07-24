package com.example.seshatrpgauxiliary.domain.exceptions.custom;

public class SeshatBadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public SeshatBadRequestException(String message){
        super(message);
    }
}
