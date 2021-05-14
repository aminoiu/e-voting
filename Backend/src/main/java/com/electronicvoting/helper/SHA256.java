package com.electronicvoting.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class SHA256 {

    public static String generateHash(String value) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            hash = getStringHashCode(md, value);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public static String generateHash(Map<String,String> map) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String convertedMapToString=mapToString(map);
            hash = getStringHashCode(md, convertedMapToString);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    private static String getStringHashCode( MessageDigest md, String convertedMapToString) {
        String hash;
        byte[] bytes = md.digest(convertedMapToString.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        hash = sb.toString();
        return hash;
    }

    public static String mapToString(Map<String, String> map) {

        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : map.keySet()) {
            mapAsString.append(key).append("=").append(map.get(key)).append(", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
