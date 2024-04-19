package de.htwg.dbpopulation.generators;

import java.security.SecureRandom;
import java.time.LocalDate;

public class RandomLib {

    private static final int MAX_STAY = 30;

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

    public static class RandomIntSequence{

        int[] SEQUENCE;
        int POPPED = 0;

        public boolean contains ( int[] sequence, int value){
            for (int j : sequence) {
                if (j == value) {
                    return true;
                }
            }
            return false;
        }

        public void createSequence ( int min, int max, int length){
            if ((max - min + 1) < length) {
                throw new IllegalArgumentException("The range is too small to generate a sequence of the given length.");
            }
            SEQUENCE = new int[length];
            int created = 0;
            while (created < length) {
                int next = getInt(min, max);
                if (!contains(SEQUENCE, next)) {
                    SEQUENCE[created] = next;
                    created++;
                }
            }
        }

        public int getNextInt () {
            POPPED++;
            return SEQUENCE[POPPED - 1];
        }
    }

    public static LocalDate getRandomDate() {
        LocalDate today = LocalDate.now();
        return today.plusDays(getInt(1, 700));
    }

    public static LocalDate getRandomDate(LocalDate startDate) {
        return startDate.plusDays(getInt(3, MAX_STAY));
    }

}
