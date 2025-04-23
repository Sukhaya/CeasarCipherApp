package org.example.exceptions;

public class NotValidKeyException extends RuntimeException {

    public NotValidKeyException() {
        super("Ключ болжен быть в диапазоне алфавита");
    }
}
