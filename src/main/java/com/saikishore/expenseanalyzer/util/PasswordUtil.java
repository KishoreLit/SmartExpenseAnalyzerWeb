package com.saikishore.expenseanalyzer.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash a plain-text password
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verify a password against the stored hash
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}