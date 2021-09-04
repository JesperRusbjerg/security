import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

    public static void main(String[] args) throws ScriptException {

        IntegerWithAddition iwa = new IntegerWithAddition();

        //Closure
        System.out.println(iwa.getItems().contains(iwa.binaryOperator(2,700)));
        System.out.println(iwa.getItems().contains(iwa.binaryOperator(7,700)));

        //Identity
        //Identity = 0
        System.out.println(iwa.binaryOperator(0,700) == 700);
        System.out.println(iwa.binaryOperator(0,708) == 708);

        //Commutativity
        System.out.println(iwa.binaryOperator(2,9) == iwa.binaryOperator(9,2));

        //Associativity
        int res1 = iwa.binaryOperator(1,2);
        int res2 = iwa.binaryOperator(2,3);
        System.out.println(res1+res2 == res2+res1);

        //Inverse
        System.out.println(iwa.unaryInvertOperator(900));
        System.out.println(iwa.unaryInvertOperator(400));


        // XOR STRING!!!!
        XORString xor = new XORString();
        //Closure
        // if 00 then 0 and 0 is a part of it, otherwise you couldnt do it
        // if 11 then 1 and 1 is a part of it, otherwise you couldnt do it
        // if 01 then 1 and 1 is a part of it, otherwise you couldnt do it

        //Commutativity
        String bitOne = "0";
        String bitTwo = "1";
        //Both will print one, as the exlusive operator will evaluate to that
        System.out.println(xor.binaryOperator(bitOne, bitTwo));
        System.out.println(xor.binaryOperator(bitTwo, bitOne));

        //Associativity
        String bitaOne = "1";
        String bitaTwo = "0";
        String bitaThree = "0";

        String x = xor.binaryOperator(bitaOne, bitaTwo);
        String res = xor.binaryOperator(x, bitaThree);
        System.out.println(res);

        x = xor.binaryOperator(bitaTwo, bitaThree);
        res = xor.binaryOperator(x, bitaOne);
        System.out.println(res);

        //Identity is 0 as shown in the following
        System.out.println(xor.binaryOperator("0", "1"));
        System.out.println(xor.binaryOperator("0", "0"));

        //Inverse
        //Inverse is itself, if 0 is a part of the string, it is its own inverse, as 0 XOR 0 == 0, same goes for 1 and 1
        System.out.println(xor.unaryInvertOperator("0"));
        System.out.println(xor.unaryInvertOperator("1"));


                

    }
}
