package com.example.kunuz_1.excp;

public class RegionNotFoundException extends RuntimeException{
    public RegionNotFoundException(String message) {
        super(message);
    }
}
