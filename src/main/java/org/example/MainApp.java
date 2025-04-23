package org.example;

import org.example.exceptions.FileIsNotCreatedException;
import org.example.exceptions.NotValidKeyException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final List<Character> ALPHABET = Arrays.asList('а', 'б',
            'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Логика для выбора режима работы, вызов соответствующих методов
        displayMenu();
        String choiceMenu = scanner.nextLine();
        handleUserChoice(choiceMenu);
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("""
                1. Шифрование
                2. Расшифровка с ключом
                3. Brute force
                4. Выход
                """);
        System.out.print("Выберите действие: ");
    }

    private static void handleUserChoice(String choice) {

        switch (choice) {
            case "1" -> {
                String stringFromFile = getStringFromFile(); //"input.txt"
                System.out.print("Введите шаг для шифрования: ");
                int shift = getShift();
                checkValidKey(shift);
                String encrypt = new Cipher(ALPHABET).encrypt(stringFromFile, shift);
                writeToFile(encrypt); //"encrypt.txt"
            }

            case "2" -> {
                String stringFromFile = getStringFromFile(); //"encrypt.txt"
                System.out.print("Введите ключ для расшифровки: ");
                int shift = getShift();
                checkValidKey(shift);
                String decrypt = new Cipher(ALPHABET).decrypt(stringFromFile, shift);
                writeToFile(decrypt); //"decrypt.txt"
            }

            case "3" -> {
                String stringFromFile = getStringFromFile();
                String bruteForcedText = new BruteForce().decryptByBruteForce(stringFromFile, ALPHABET);
                writeToFile(bruteForcedText);
            }

            case "4" -> {
            }
            default -> System.out.println("Неверный ввод. " +
                    "Пожалуйста, выберите пункт меню от 1 до 5");
        }
    }

    private static String getStringFromFile() {
        String string = null;
        System.out.println("Введите адрес файла для чтения: ");
        String path = scanner.nextLine();
        try {
            string = FileManager.readFile(path);
        } catch (IOException e) {
            e.getMessage();
        }
        return string;
    }

    private static int getShift() {
        int shift;
        try {
            shift = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели пустую строку. \nПожалуйста, введите числовое значение");
            shift = Integer.parseInt(scanner.nextLine());
        }
        return shift;
    }

    private static void checkValidKey(int shift) {
        boolean validKey = new Validator().isValidKey(shift, ALPHABET);
        if (!validKey) {
            throw new NotValidKeyException();
        }
    }

    private static void writeToFile(String encrypt) {
        System.out.println("Введите адрес файла для записи текста: ");
        String path = scanner.nextLine();
        try {
            FileManager.writeFile(encrypt, path);
        } catch (IOException e) {
            e.getMessage();
        }

        File file = new File(path);
        if (!file.exists()) {
            throw new FileIsNotCreatedException(path);
        }
    }
}