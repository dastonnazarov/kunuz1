package com.example.kunuz_1.excp;

public class AppBadRequestException  extends RuntimeException{

    public AppBadRequestException(String message) {
        super(message);
    }
}
