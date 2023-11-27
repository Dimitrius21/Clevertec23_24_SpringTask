package ru.clevertec.springtask.SpringTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class ConverterJpgToString {
    public static void main(String[] args) throws IOException {
        String filename = "parrot.jpg";
        File file = new File(filename);
        byte[] contains = new FileInputStream(file).readAllBytes();
        String convertedFile = Base64.getEncoder().encodeToString(contains);
        new FileWriter("parrot.txt").write(convertedFile);
    }
}
