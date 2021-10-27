import java.math.BigInteger;
import java.util.Random;

public class ElGamal {

    public static void main(String[] args) {
        Random ran = new Random();

        BigInteger g = BigInteger.valueOf(666);

        BigInteger p = BigInteger.valueOf(6661);

        System.out.println("Assignment one");
        //Assignment one

        //Bobs PK(Public key) = 2227
        //Was given in the assignment

        //finding x from gX (Not needed in this example, but doing it to understand)
        BigInteger count = BigInteger.valueOf(0);
        BigInteger x;
        while (true) {
            int test = g.modPow(count, p).intValue();
            if (test == 2227) {
                x = count;
                break;
            }
            int next = count.intValue();
            next += 1;
            count = BigInteger.valueOf(next);
        }

        //Bob sends this to alice
        BigInteger publicKey = g.modPow(x, p);

        //Alice now has g^X and can begin making the message
        //Computing the message
        BigInteger randomlyChosenY = BigInteger.valueOf(ran.nextInt(p.intValue() - 1) + 1);
//        BigInteger randomlyChosenY =  BigInteger.valueOf(880);

        BigInteger gY = g.modPow(randomlyChosenY, p);

        BigInteger gXY = publicKey.modPow(randomlyChosenY, p);

        //Message is
        int message = 2000;

        BigInteger c = BigInteger.valueOf(message * gXY.intValue());

        //Sending c and g^y through the network
        //Bob can now compute the m
        BigInteger gyx = gY.modPow(x, p);
        BigInteger c2 = c;

        int res = c2.intValue() / gyx.intValue();

        System.out.println("final result, bob has recieved a message of " +res);


        //Assignment two
        System.out.println("Assignment Two");
        //Eve has intercepted my encrypted message
        //This means that eve has g^y and c

        BigInteger xEve = BigInteger.valueOf(0);
        while (true) {
            BigInteger gX = BigInteger.valueOf(g.intValue());
            gX = gX.modPow(xEve, p);

            if (gX.intValue() == 2227) {
                System.out.println("Bobs private key is :" + xEve.intValue());
                break;
            } else {
                xEve = BigInteger.valueOf(xEve.intValue() + 1);
            }
        }

        BigInteger interceptedgofY = gY;

        BigInteger gxyEve = interceptedgofY.modPow(xEve,p);

        BigInteger interceptedMsg = c2;

        int reconstructedMsg = interceptedMsg.intValue()/gxyEve.intValue();
        System.out.println("The message which Alice sent was : " +reconstructedMsg);

        //Assignment 3
        System.out.println("Assignment3 ");

        //Copied down some variables from exercise 1 for simplicity
        BigInteger gyx2 = gY.modPow(x, p);
        BigInteger c23 = c;
        c23 = BigInteger.valueOf(c23.intValue()*3);


        int res3 = c23.intValue() / gyx.intValue();
        System.out.println(res3);





    }
}
