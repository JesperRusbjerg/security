package com.example.securitybob;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Base64;

@SpringBootApplication
@RestController
public class SecurityBobApplication {
    RestService r = new RestService();
    String commitmentNotVerified = "";
    String commitmentMsg = "";
    int aliceNumberConfirmed;
    String numberAndRandom = "";

    public static int bobNumber = 4;

    public static void main(String[] args) {
        SpringApplication.run(SecurityBobApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/commitment")
    public String getCommitment(@RequestBody byte[] commitment) throws Exception {
        String message = Encrypt.decryptData(commitment);
        commitmentNotVerified = message;
        return "got it";
    }

    @PostMapping("/commitmentVerify")
    public String getCommitmentVerify(@RequestBody byte[] signature) throws Exception {
        boolean verified = Encrypt.verifySignature(signature, commitmentNotVerified);

        if (verified) {
            commitmentMsg = commitmentNotVerified;
        } else {
            commitmentMsg = "couldnt verify";
            return "cannot verify signature!";
        }

        byte[] encryptedNumber = Encrypt.encryptData(String.valueOf(bobNumber));
        byte[] signatureNumberBob = Encrypt.signMessage(String.valueOf(bobNumber));

        r.post(encryptedNumber, "number");
        String verify = r.post(signatureNumberBob, "verifyNumber");

        if(verify.equals("could not verify")) System.exit(0);

        return "verified";
    }

    @PostMapping("/recieveNumberAndRandom")
    public String recieveNumberAndRandom(@RequestBody byte[] numAndRand) throws Exception {
        numberAndRandom = Encrypt.decryptData(numAndRand);
        return "got it";
    }


    @PostMapping("/verifyCommitment")
    public String verifyCommitmentAndMaybeRoll(@RequestBody byte[] signatureNumAndRand) throws Exception {

        boolean validateNumberAndRandom = Encrypt.verifySignature(signatureNumAndRand, numberAndRandom);

        if (validateNumberAndRandom) {
            int number = Integer.parseInt(String.valueOf(numberAndRandom.charAt(0)));
            aliceNumberConfirmed = number;

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(numberAndRandom.getBytes());

            // messageHashed is the commitment
            String messageHashed = new String(messageDigest.digest());

            boolean commitmentVerified = messageHashed.equals(commitmentMsg);

            if (commitmentVerified) {
                //bob has verified the number and can now roll!
                System.out.println(roll(aliceNumberConfirmed, bobNumber));
                //Reset for another potential dice roll
                commitmentNotVerified = "";
                commitmentMsg = "";
                aliceNumberConfirmed = -1;

                return "verified";
            } else {
                return "not verified";
            }
        }
        return "couldnt verify signature";
    }

    //Roll protocol
    public static int roll(int a, int b) {
        return a * b % 7;
    }

}