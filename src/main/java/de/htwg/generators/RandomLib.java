package de.htwg.generators;

import java.security.SecureRandom;

public class RandomLib {

    // Define characters that can be used in the password
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=";
    private static final SecureRandom random = new SecureRandom();

    // Method to generate a random password with a length between minLen and maxLen
    public static String generatePassword(int minLen, int maxLen) {
        int passwordLength = minLen + random.nextInt(maxLen - minLen + 1);
        StringBuilder password = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }

    public static int getInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static String generateIBAN() {
        String[] country_code = {"GB", "FR", "DE", "IT", "ES", "NL", "AT", "BE", "PT", "SE"};
        StringBuilder ibanBuilder = new StringBuilder();
        ibanBuilder.append(country_code[getInt(0, country_code.length - 1)]);

        for (int i = 0; i < 14; i++) { // 14 digits after the first one
            ibanBuilder.append(getInt(0, 9));
        }

        return ibanBuilder.toString();
    }
}
