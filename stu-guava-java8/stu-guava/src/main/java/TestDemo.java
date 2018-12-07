import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wangshengren on 17/1/23.
 */
public class TestDemo {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, null, 4, 5, null);
        Iterable<Integer> tmp = Iterables.filter(list, input -> input != null);
        System.out.println(list);
        System.out.println(tmp);
        list.remove(0);
        System.out.println(list);
        System.out.println(tmp);
    }
}
