package com.example.monolithic_auth.utils;

import java.security.MessageDigest;

public class EncryptionUtils {

    public static String encryptSHA256(String s){
        return encrypt(s, "SHA-256");
    }

//    단방향 암호화
    public static String encrypt(String s, String messageDigest){
        try{
            MessageDigest md = MessageDigest.getInstance(messageDigest);
            byte[] passBytes = s.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digested.length; i++) sb.append(Integer.toString((digested[i]&0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (Exception e) {
            return s;
        }
    }

//    받은 비밀번호와 비교
    public static boolean matches(String rawPassword, String encodedPassword){
        String predict = encryptSHA256(rawPassword);
        return encodedPassword.equals(predict);
    }
}
