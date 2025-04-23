package org.example;

import org.example.exceptions.FailedDecryptTextException;

import java.io.IOException;
import java.util.List;

public class BruteForce {
    public String decryptByBruteForce(String encryptedText, List<Character> alphabet) {
        // Логика brute force
        String finalText = null;
        String originalText = null;

        for (int i = 0; i <= alphabet.size(); i++) {
            Cipher cipher = new Cipher(alphabet);
            String interimText = cipher.decrypt(encryptedText, i);

            try {
                originalText = FileManager.readFile("input.txt");
            } catch (IOException e) {
                e.getMessage();
            }

            if (interimText.equalsIgnoreCase(originalText)) {
                System.out.println("\nТекст был зашифрован на шаг " + i);
                finalText = interimText;
            }
        }

        if (finalText == null) {
            throw new FailedDecryptTextException();
        }
        return finalText;
    }
}
