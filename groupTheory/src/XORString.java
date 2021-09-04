public class XORString implements Group<String> {


    @Override
    public String binaryOperator(String a, String b) {
        boolean a1 = a.equals("1");
        boolean b1 = b.equals("1");

        if (a1 && b1) return "0";
        if (a1) return "1";
        if (b1) return "1";
        return "0";
    }

    @Override
    public boolean unaryInvertOperator(String a) {

        if(binaryOperator(a,a).equals("0")) return true;

        return false;
    }
}
