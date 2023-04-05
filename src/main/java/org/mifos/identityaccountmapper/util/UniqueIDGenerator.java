package org.mifos.identityaccountmapper.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class UniqueIDGenerator {
    public static String generateUniqueNumber(int length) {
        Random rand = new Random();
        long timestamp = System.currentTimeMillis();
        int randomInt = rand.nextInt(100000000);
        String uniqueNumber = timestamp + "" + randomInt;
        return uniqueNumber.substring(0, length);
    }
    public static String generateUniqueID(int length) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Random rand = new Random();
            long timestamp = System.currentTimeMillis();
            int randomInt = rand.nextInt(100000);
            String input = timestamp + "" + randomInt;
            byte[] hash = md.digest(input.getBytes());
            String hashString = bytesToHex(hash);
            return hashString.substring(0, length);
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
