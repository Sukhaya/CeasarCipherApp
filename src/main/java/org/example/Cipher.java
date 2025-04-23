package org.example;

import org.example.exceptions.DecryptHackerAttackException;

import java.util.List;

public class Cipher {
    private List<Character> alphabet;

    public Cipher(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        // Логика шифрования
        int encryptShift;
        char[] array = text.toLowerCase().toCharArray();

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j <= alphabet.size(); j++) {

                if (!alphabet.contains(array[i])) {
                    continue;
                }

                if (alphabet.get(j) == array[i]) {
                    encryptShift = (j + shift) % alphabet.size();
                    array[i] = alphabet.get(encryptShift);
                    break;
                }
            }
        }
        return String.valueOf(array);
    }

    public String decrypt(String encryptedText, int shift) {
        // Логика расшифровки
        int decryptShift;
        char[] array = encryptedText.toLowerCase().toCharArray();

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j <= alphabet.size(); j++) {

                if (!alphabet.contains(array[i])) {
                    throw new DecryptHackerAttackException();
                }

                if (alphabet.get(j) == array[i]) {
                    decryptShift = (j - shift) % alphabet.size();
                    if (decryptShift < 0) {
                        decryptShift += alphabet.size();
                    }
                    array[i] = alphabet.get(decryptShift);
                    break;
                }
            }
        }
        return String.valueOf(array);
    }
}
