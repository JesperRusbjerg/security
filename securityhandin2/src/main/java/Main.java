
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

public class Main {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
//        System.out.println("hej");
//
//        BigInteger aSK = new BigInteger("109357556076863351275749112480504731590697003394409927226819241463972602909098");
//        BigInteger aPK = new BigInteger("1611869854234195676579827553667725198829527264267473570042021436001460975319216614515113712248114235957483666635155806079399666562303215107931528615417184");
//
//        ECKeyPair keyPair = new ECKeyPair(aSK, aPK);
//
//        String msg = "Message for signing";
//        byte[] msgHash = Hash.sha3(msg.getBytes());
//        Sign.SignatureData signature =
//                Sign.signMessage(msgHash, keyPair, false);
//        System.out.println("Msg: " + msg);
//        System.out.println("Msg hash: " + Hex.toHexString(msgHash));
//
//
//
//        BigInteger pubKeyRecovered =
//                Sign.signedMessageToKey(msg.getBytes(), signature);
//        System.out.println("Recovered public key: " +
//                pubKeyRecovered.toString(16));
//
//        boolean validSig = aPK.equals(pubKeyRecovered);
//        System.out.println("Signature valid? " + validSig);

    }
}
