package com.example.security04.util;

import java.nio.charset.StandardCharsets;

public class Base64 {

    public static String base64ToString(String string){
        byte[] decoded = java.util.Base64.getDecoder().decode(string);
        String value = new String(decoded, StandardCharsets.UTF_8);
        System.out.println(value);
        return value;

    }

    public static String stringToBase64(String string){
        String encode = java.util.Base64.getEncoder().encodeToString(string.getBytes());
        return encode;
    }
}
