package com.uh.fuelratecheck;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PasswordEncryptionTests {
    
    @Test
    void passwordEncryptionReturnsHash() {
        String hash = PasswordEncryption.hash("random");
        assertThat(hash).isNotNull();
        assertThat(hash).isNotEmpty();
    }

    @Test
    void PasswordEncryptionReturnsEmpty() {
        String hash = PasswordEncryption.hash(null);
        assertThat(hash).isEmpty();
    }
}
