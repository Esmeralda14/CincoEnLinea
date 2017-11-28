/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author marianacro
 */
public class AuxiliarDAO {
    
    public String makeHash(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(string.getBytes());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}
