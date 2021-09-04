import java.util.ArrayList;
import java.util.List;

public class IntegerWithAddition implements Group<Integer>{

    List<Integer> items = new ArrayList<>();

    public IntegerWithAddition() {
        for (int i = -9999; i <9999 ; i++) {
            items.add(i);
        }

    }

    public List<Integer> getItems() {
        return items;
    }

    @Override
    public Integer binaryOperator(Integer a, Integer b) {
        return a+b;
    }

    @Override
    public boolean unaryInvertOperator(Integer a) {

        int toCheck = binaryOperator(a, -1);
        if(items.contains(toCheck)) return true;
        else return false;
    }

}
