package app.tiktok;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class GenerateCodeForFile {
    public static String createCodeForFile(){
        return getString();
    }

    public static String getString() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            char randomSymbol = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length())); // Випадковий символ зі стрічки symbols

            stringBuilder.append(randomSymbol);
        }

        return stringBuilder.toString();
    }
}
