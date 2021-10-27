package com.example.securitybob;

import javax.crypto.Cipher;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

public class Encrypt {
    public static byte[] encryptData(String message) throws Exception{

        //Importing pub key from file
        Path path = Paths.get("src/alicePK.pub");
        byte[] bytes = Files.readAllBytes(path);

        /* Generate public key. */
        X509EncodedKeySpec ksx = new X509EncodedKeySpec(bytes);
        KeyFactory kfx = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kfx.generatePublic(ksx);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//
        byte[] input = message.getBytes();
        cipher.update(input);
//
//        //encrypting the data
        byte[] cipherText = cipher.doFinal();
        return cipherText;
    }

    public static String decryptData(byte[] ciphertext) throws Exception{

        //Importing pub key from file
        Path path = Paths.get("src/bobRSA.key");
        byte[] bytes = Files.readAllBytes(path);

        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = kf.generatePrivate(ks);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

//        //Initializing the same cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
//        Decrypting the text
        byte[] decipheredText = cipher.doFinal(ciphertext);
        return new String(decipheredText);
    }

    public static  byte[] signMessage(String msg) throws Exception{
        Path path = Paths.get("src/bobSign.key");
        byte[] bytes = Files.readAllBytes(path);

        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = kf.generatePrivate(ks);

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);

        byte[] byteArrray = msg.getBytes();

        sign.update(byteArrray);

        byte[] signature = sign.sign();

        return signature;

    }

    public static boolean verifySignature(byte[] signature, String msg) throws Exception{

        Path path = Paths.get("src/aliceSign.pub");
        byte[] bytes = Files.readAllBytes(path);

        /* Generate public key. */
        X509EncodedKeySpec ksx = new X509EncodedKeySpec(bytes);
        KeyFactory kfx = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kfx.generatePublic(ksx);

        Signature sign2 = Signature.getInstance("SHA256withRSA");
        sign2.initVerify(publicKey);
        byte[] byteArrray = msg.getBytes();

        sign2.update(byteArrray);

        return sign2.verify(signature);

    }

    public static String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
