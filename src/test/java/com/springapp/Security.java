package com.springapp;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class Security extends TestCase{
    @Test
    public void testSpringEncoder() {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword("koala", null);
        assertEquals("a564de63c2d0da68cf47586ee05984d7", hashedPass);
    }
}
