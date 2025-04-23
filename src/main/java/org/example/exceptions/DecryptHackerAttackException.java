package org.example.exceptions;

public class DecryptHackerAttackException extends RuntimeException {

    public DecryptHackerAttackException() {
        super("Аларм! Попытка взлома");
    }
}
