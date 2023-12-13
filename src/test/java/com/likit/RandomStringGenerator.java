package com.likit;

import java.util.Random;

public class RandomStringGenerator {
    public static String generateRandomString(int length) {
        // Define the characters you want to include in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a StringBuilder to build the random string
        StringBuilder randomStringBuilder = new StringBuilder(length);

        // Create a random generator
        Random random = new Random();

        // Generate the random string by appending random characters from the 'characters' string
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomStringBuilder.append(randomChar);
        }

        // Convert the StringBuilder to a String and return
        return randomStringBuilder.toString();
    }
}
