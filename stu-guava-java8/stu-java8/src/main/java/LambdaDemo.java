import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by wangshengren on 2017/4/13.
 */
public class LambdaDemo {

    public static void fun(String a){
        System.out.println(a);
    }

    public static void fun1(String a,Consumer<String> c){
        //abc(c);
        c.accept(a);
    }


    public static void main(String[] args) {
        fun("abc");



        //()->statement;
        //a->statement;
        //(a,b)->statement;
        //(a,b)->{statement}
        //1
        Runnable r = () -> System.out.println("runner");
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("runner");
            }
        };
        //2
        Consumer<Integer> c = a -> System.out.println(a);
        Consumer<Integer> c1 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };
        //3,4
        BinaryOperator<Integer> bi = (a, b) -> a + b;
        BinaryOperator<Integer> bi1 = (a, b) -> {
            System.out.println("def");
            return a + b;
        };
        BinaryOperator<Integer> bi2 = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a1, Integer a2) {
                return a1 + a2;
            }
        };


        //jdk向后兼容(默认方法、静态方法)
        //类库的改进stream,collect
        //collection(stream)
        //Iterable(forEach)
        List<String> list = Arrays.asList("a", "b", "c", "d");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
        }
        list.forEach(s -> System.out.println(s));



        List<String> tmp = list.stream().filter(a -> !"a".equals(a)).map(a -> a + "..").collect(Collectors.toList());



        Map<String, String> map1 = list.stream().collect(Collectors.toMap(a -> a, a -> a + ".."));



        list = Arrays.asList("a", "b", "c", "c.");
        Map<String, String> map2 = list.stream().collect(Collectors.toMap(a -> a.replace(".", ""), b -> b + "..", (s, s2) -> s + "--" + s2));
        //System.out.println(tmp);
        System.out.println(map1);
        //System.out.println(map2);
        //统计分析
        //Stream.of(1, 2, 3, 4, 5, 6).mapToInt(Integer::intValue).summaryStatistics();

    }
}
