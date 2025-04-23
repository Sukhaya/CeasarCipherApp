package org.example.exceptions;

public class FileIsNotCreatedException extends RuntimeException {

    public FileIsNotCreatedException(String path) {
        super("Не удалось создать файл " + path);
    }
}
