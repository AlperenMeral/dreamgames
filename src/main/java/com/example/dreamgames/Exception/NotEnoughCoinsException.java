package com.example.dreamgames.Exception;

public class NotEnoughCoinsException extends RuntimeException {
    public NotEnoughCoinsException(String message) {
        super(message);
    }
}
