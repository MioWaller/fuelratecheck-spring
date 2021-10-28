package com.uh.fuelratecheck;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordEncryption {

    private PasswordEncryption() {

    }

    public static String hash(String password) {
        try {
            byte[] salt = "slqp0ppvgkwkifkvkd".getBytes();
            int iterations = 1024;
            
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        }
        catch (Exception e) {
            return "";
        }
    }
     
}