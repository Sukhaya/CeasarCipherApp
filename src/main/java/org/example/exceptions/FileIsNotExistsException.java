package org.example.exceptions;

public class FileIsNotExistsException extends RuntimeException {

    public FileIsNotExistsException(String path) {
        super("Файл " + path + " не найден");
    }
}
