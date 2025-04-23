package org.example;

import org.example.exceptions.FileIsNotExistsException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class FileManager {

    public static String readFile(String filePath) throws IOException {
        Validator validator = new Validator();
        if (!validator.isFileExists(filePath)) {
            throw new FileIsNotExistsException(filePath);
        }

        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void writeFile(String content, String filePath) throws IOException{
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            Validator validator = new Validator();
            if (!validator.isFileExists(path.toString())) {
                File file = new File(path.toString());
                file.createNewFile();
            }
            writer.write(content);
        }
    }
}
