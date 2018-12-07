package part01;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;

/**
 * Created by wangshengren on 17/1/18.
 */
public class AvoidNullDemo {
    public static void main(String[] args) {
        //Optional
        //init
        Optional<Integer> op01 = Optional.absent();
        Optional<Integer> op02 = Optional.of(1);
        Optional<Integer> op03 = Optional.fromNullable(null);
        //get
        Integer a = op01.get();
        boolean b = op01.isPresent();
        Integer c = op01.orNull();
        op01.or(1);
        op01.or(Optional.of(2));
        op01.or(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 3;
            }
        });
        op01.transform(new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return input.toString();
            }
        });
    }
}
