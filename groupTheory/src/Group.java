import java.util.ArrayList;

public interface Group<T> {


    T binaryOperator(T a, T b);

    boolean unaryInvertOperator(T a);

}
