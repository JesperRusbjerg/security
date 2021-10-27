package com.example.securityalice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.security.*;


@SpringBootApplication
@RestController
public class SecurityAliceApplication {

    public static String somethingRandom;

    public static int numberFromBob;

    public static int aliceNumber = 6;

    public static RestService r = new RestService();

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SecurityAliceApplication.class, args);

        somethingRandom = Encrypt.randomString();

        String msg = aliceNumber + somethingRandom;

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(msg.getBytes());

        // messageHashed is the commitment
        String messageHashed = new String(messageDigest.digest());
        //Signature of messageHashed
        byte[] signatureOfMsg = Encrypt.signMessage(messageHashed);
        //Alice can now encrypt the signed message and send it to Bob
        byte[] encryptedMsg = Encrypt.encryptData(messageHashed);

        //Time to send the commitment and the signature to Bob so he can recieve it and ensure it is indeed from Alice

        String recieveComm = r.post(encryptedMsg, "commitment");
        if (!recieveComm.equals("got it")) System.exit(0);

        String verifyCommitment = r.post(signatureOfMsg, "commitmentVerify");
        if (!verifyCommitment.equals("verified")) System.exit(0);

    }

    @PostMapping("/number")
    public String getNumberFromBob(@RequestBody byte[] number) throws Exception {
        numberFromBob = Integer.parseInt(Encrypt.decryptData(number));
        return "verified";
    }

    @PostMapping("/verifyNumber")
    public String verifyNumberFromBob(@RequestBody byte[] signature) throws Exception {


        boolean verifyNumberFromBob = Encrypt.verifySignature(signature, String.valueOf(numberFromBob));
        if (!verifyNumberFromBob) {
            return "could not verify";
        } else {

            //Verify the commitment by sending the number and the random which Bob can then hash and check

            r.post(Encrypt.encryptData(aliceNumber+somethingRandom), "recieveNumberAndRandom");
            String verify = r.post(Encrypt.signMessage(aliceNumber+somethingRandom), "verifyCommitment");

            if (verify.equals("verified")) {
                System.out.println("now we roll, bob is happy with my commitment");

                System.out.println(roll(aliceNumber, numberFromBob));

                return "ive done it, thank you - Alice";
            }

            return "Too bad - Alice";
        }
    }

    //Roll protocol
    public static int roll(int a, int b) {
        return a * b % 7;
    }

}