package org.example.exceptions;

public class FailedDecryptTextException extends RuntimeException {

    public FailedDecryptTextException() {
        super("Не удалось получить расшифрованный файл");
    }
}
