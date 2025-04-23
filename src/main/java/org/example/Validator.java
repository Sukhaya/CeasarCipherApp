package org.example;

import java.io.File;
import java.util.List;

public class Validator {

    public boolean isValidKey(int key, List<Character> alphabet) {
        // Проверка ключа
        boolean isValid = true;

        if (key > alphabet.size() - 1) {
            isValid = false;
        }
        return isValid;
    }

    public boolean isFileExists(String filePath) {
        // Проверка существования файла
        File file = new File(filePath);
        boolean isExists = true;
        if (!file.exists()) {
            isExists = false;
        }
        return isExists;
    }
}
