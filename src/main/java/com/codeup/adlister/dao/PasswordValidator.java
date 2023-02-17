package com.codeup.adlister.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final int MIN_LENGTH = 12;
    private static final int MIN_UPPERCASE = 2;
    private static final int MIN_LOWERCASE = 2;
    private static final int MIN_SPECIAL = 2;

    public static boolean isValidPassword(String password) {
        if (password.length() < MIN_LENGTH) {
            return false;
        }

        int uppercaseCount = 0;
        int lowercaseCount = 0;
        int specialCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercaseCount++;
            } else if (Character.isLowerCase(c)) {
                lowercaseCount++;
            } else if (isSpecialCharacter(c)) {
                specialCount++;
            }
        }

        return uppercaseCount >= MIN_UPPERCASE
                && lowercaseCount >= MIN_LOWERCASE
                && specialCount >= MIN_SPECIAL;
    }

    private static boolean isSpecialCharacter(char c) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(String.valueOf(c));
        return matcher.matches();
    }
}

// ============================================================ //
// * Ignore the yellow triangle. Intellij will break this code. //
// ============================================================ //