package org.wsr.bestpractice;

import com.google.common.collect.*;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.SetUtils;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * 操作collection（list,set,map etc...）的最佳实践
 * <pre>
 *     参考:guava,apache
 *     https://github.com/google/guava/wiki
 *     http://ifeve.com/google-guava/
 * </pre>
 * @author wangsr
 * @date 2018/8/3
 */
public class CollectionBestPractice {
    //guava collection

    /**
     * 不可变集合: 用不变的集合进行防御性编程和性能提升
     */
    public void immutableXxx() {
        //guava 不可变集合的构造方式
        //1、copyOf方法，如ImmutableSet.copyOf(set);
        //2、of方法，如ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);
        //3、Builder工具
        /*
        public static final ImmutableSet<Color> GOOGLE_COLORS =
        ImmutableSet.<Color>builder()
            .addAll(WEBSAFE_COLORS)
            .add(new Color(0, 191, 255))
            .build();
        */
    }

    /**
     * 新集合类型: multisets, multimaps, tables, bidirectional maps等
     */

    /**
     * 多值set
     * <pre>
     * Map<String, Integer> counts = new HashMap<String, Integer>();
     * for (String word : words) {
     *     Integer count = counts.get(word);
     *     if (count == null) {
     *         counts.put(word, 1);
     *     } else {
     *         counts.put(word, count + 1);
     *     }
     * }
     * </pre>
     */
    public void multiSet() {
        //init
        Multiset<String> s1 = HashMultiset.create();
        Multiset<String> s2 = TreeMultiset.create();
        Multiset<String> s3 = LinkedHashMultiset.create();
        Multiset<String> s4 = ConcurrentHashMultiset.create();
        Multiset<String> s5 = ImmutableMultiset.of();

        //op
        //添加元素一次
        s1.add("a");
        //添加元素N次
        s1.add("a", 2);
        //删除元素一次
        s1.remove("a");
        //删除元素N次
        s1.remove("a", 2);
        //设置元素出现的次数
        s1.setCount("a", 10);
        //给定元素在Multiset中的计数
        int count = s1.count("a");
        //给定元素在Multiset中的计数
        Set<String> elementSet = s1.elementSet();
        //和Map的entrySet类似，返回Set<Multiset.Entry<E>>，其中包含的Entry支持getElement()和getCount()方法
        Set<Multiset.Entry<String>> entrySet = s1.entrySet();
        //返回集合元素的总个数（包括重复的元素）
        int size = s1.size();
    }

    /**
     * 多值Map，Multimap不是Map
     * <pre>
     *      可以用两种方式思考Multimap的概念：”键-单个值映射”的集合：
     *      a -> 1 a -> 2 a ->4 b -> 3 c -> 5
     *      或者”键-值集合映射”的映射：
     *      a -> [1, 2, 4] b -> 3 c -> 5
     *      一般来说，Multimap接口应该用第一种方式看待，但asMap()视图返回Map<K, Collection<V>>，让你可以按另一种方式看待Multimap。
     * </pre>
     */
    public void multiMap() {
        //init
        //ArrayList
        ListMultimap<String, String> m1 = ArrayListMultimap.create();
        //HashSet
        SetMultimap<String, String> m2 = HashMultimap.create();
        //TreeSet
        SortedSetMultimap<String, String> m3 = TreeMultimap.create();

        //op
        m1.put("a", "1");
        m1.putAll("a", Arrays.asList("2", "3"));
        List<String> list = m1.get("a");
        //转化为map视图view
        Map<String, Collection<String>> map = m1.asMap();
        Map<String, List<String>> map1 = Multimaps.asMap(m1);
        Map<String, Set<String>> map2 = Multimaps.asMap(m2);
        Map<String, SortedSet<String>> map3 = Multimaps.asMap(m3);
        //用Collection<Map.Entry<K, V>>返回Multimap中所有”键-单个值映射”——包括重复键
        Collection<Map.Entry<String, String>> entries = m1.entries();
        Set<Map.Entry<String, String>> entries2 = m2.entries();
        Set<Map.Entry<String, String>> entries3 = m3.entries();
        //用Set表示Multimap中所有不同的键
        Set<String> keySet = m1.keySet();
        //用Multiset表示Multimap中的所有键，每个键重复出现的次数等于它映射的值的个数。可以从这个Multiset中移除元素，但不能做添加操作；移除操作会反映到底层的Multimap
        Multiset<String> keys = m1.keys();
        /*
        用一个”扁平”的Collection<V>包含Multimap中的所有值。这有一点类似于Iterables.concat(multimap.asMap().values())，
        但它直接返回了单个Collection，而不像multimap.asMap().values()
        那样是按键区分开的Collection
         */
        Collection<String> v1 = m1.values();
        Collection<Collection<String>> v2 = m1.asMap().values();

    }

    /**
     * 双向Map
     * <pre>
     * 传统上，实现键值对的双向映射需要维护两个单独的map，并保持它们间的同步。但这种方式很容易出错，而且对于值已经在map中的情况，会变得非常混乱。例如：
     * Map<String, Integer> nameToId = Maps.newHashMap();
     * Map<Integer, String> idToName = Maps.newHashMap();
     *
     * nameToId.put("Bob", 42);
     * idToName.put(42, "Bob");
     * //如果"Bob"和42已经在map中了，会发生什么?
     * //如果我们忘了同步两个map，会有诡异的bug发生...
     *
     *
     * BiMap<K, V>是特殊的Map：
     * 可以用 inverse()反转BiMap<K, V>的键值映射
     * 保证值是唯一的，因此 values()返回Set而不是普通的Collection
     * 在BiMap中，如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常。如果对特定值，你想要强制替换它的键，请使用 BiMap.forcePut(key, value)。
     *
     * BiMap<String, Integer> userId = HashBiMap.create();
     * String userForId = userId.inverse().get(id);
     * </pre>
     */
    public void biMap() {
        BiMap<String, Integer> userId = HashBiMap.create();
        userId.put("a", 1);
        //会抛出IllegalArgumentException异常
        userId.put("a", 2);
        //请使用 BiMap.forcePut(key, value)
        userId.forcePut("a", 2);
        String userForId = userId.inverse().get(2);
    }

    /**
     * 二维表：table
     * <pre>
     *      Table<Vertex, Vertex, Double> weightedGraph = HashBasedTable.create();
     *      weightedGraph.put(v1, v2, 4);
     *      weightedGraph.put(v1, v3, 20);
     *      weightedGraph.put(v2, v3, 5);
     *      weightedGraph.row(v1); // returns a Map mapping v2 to 4, v3 to 20
     *      weightedGraph.column(v3); // returns a Map mapping v1 to 20, v2 to 5
     * </pre>
     */
    public void table() {
        Table<Integer, Integer, String> t1 = HashBasedTable.create();
        Table<Integer, Integer, String> t2 = TreeBasedTable.create();
        Table<Integer, Integer, String> t3 = ArrayTable.create(t1);
        t1.put(1, 1, "11");
        t1.put(1, 2, "12");
        t1.put(1, 3, "13");
        t1.put(2, 1, "21");
        t1.put(2, 2, "22");
        t1.put(2, 3, "23");
        //row
        //用Map<R, Map<C, V>>表现Table<R, C, V>
        Map<Integer, Map<Integer, String>> rm = t1.rowMap();
        //返回”行”的集合Set<R>
        Set<Integer> rk = t1.rowKeySet();
        //用Map<C, V>返回给定”行”的所有列，对这个map进行的写操作也将写入Table中
        Map<Integer, String> r = t1.row(1);

        //col
        t1.columnMap();
        t1.columnKeySet();
        t1.column(1);

        //cell
        //用元素类型为Table.Cell<R, C, V>的Set表现Table<R, C, V>。Cell类似于Map.Entry，但它是用行和列两个键区分的
        Set<Table.Cell<Integer, Integer, String>> cell = t1.cellSet();
    }

    /**
     * ClassToInstanceMap
     */
    public void classToInstanceMap() {
        ClassToInstanceMap<Number> c1 = MutableClassToInstanceMap.create();
        ClassToInstanceMap<Number> c2 = ImmutableClassToInstanceMap.of();
        c1.putInstance(Integer.class, 1);
        c1.putInstance(Long.class, 1L);
        c1.putInstance(Double.class, 1D);
        Integer a = c1.getInstance(Integer.class);
        Long b = c1.getInstance(Long.class);
        Double c = c1.getInstance(Double.class);
    }

    /**
     * 强大的集合工具类: 提供java.util.Collections中没有的集合工具
     * <pre>
     *      集合接口	          属于JDK还是Guava	          对应的Guava工具类
     *      Collection	      JDK		          	      Collections2：不要和java.util.Collections混淆
     *      List              JDK		          	      Lists
     *      Set	              JDK		          	      Sets
     *      SortedSet	      JDK		          	      Sets
     *      Map	              JDK		          	      Maps
     *      SortedMap	      JDK		          	      Maps
     *      Queue             JDK		          	      Queues
     *      Multiset          Guava		          	      Multisets
     *      Multimap          Guava		          	      Multimaps
     *      BiMap             Guava		          	      Maps
     *      Table             Guava		          	      Tables
     * </pre>
     */
    public void utils() {
        //apache、JDK7+，提供了更好的解决方式
        //集合操作：交集、并集、差集、补集、笛卡尔积
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4);
        Set<Integer> set2 = Sets.newHashSet(3, 4, 5, 6);
        Sets.union(set1, set2); //[1, 2, 3, 4, 5, 6]
        Sets.intersection(set1, set2); //[3, 4]
        Sets.difference(set1, set2);//[1, 2]
        Sets.symmetricDifference(set1, set2);//[1, 2, 5, 6]
        //笛卡尔积
        Sets.cartesianProduct(set1, set2);//[[1, 3], [1, 4], [1, 5], [1, 6], [2, 3], [2, 4], [2, 5], [2, 6], [3, 3], [3, 4], [3, 5], [3, 6], [4, 3], [4, 4], [4, 5], [4, 6]]
        //所有子集
        Sets.powerSet(set1);

        //Map的比较
        //entriesInCommon()     两个Map中都有的映射项，包括匹配的键与值
        //entriesDiffering()    键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        //entriesOnlyOnLeft()   键只存在于左边Map的映射项
        //entriesOnlyOnRight()  键只存在于右边Map的映射项
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesDiffering(); // {"c" => (3, 4)}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}
    }

    /**
     * 扩展工具类：让实现和扩展集合类变得更容易，比如创建Collection的装饰器，或实现迭代器
     */
    //省略。。。

    /**
     * 集合常见操作
     */
    public void apacheUtils() {
        //CollectionUtils
        //ListUtils
        List list1 = Lists.newArrayList(1, 2, 3);
        List list2 = Lists.newArrayList(4, 5, 6);
        ListUtils.partition(Lists.newArrayList(1, 2, 3, 4), 2);
        ListUtils.union(list1, list2);
        ListUtils.intersection(list1, list2);
        //SetUtils
        SetUtils.emptyIfNull(null);
        //MapUtils
        MapUtils.emptyIfNull(null);
        //这个getXXX的方法比较常用
        MapUtils.getString(Maps.<String, String>newHashMap(), "a", "default_value");
    }

    public static void main(String[] args) {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4);
        Set<Integer> set2 = Sets.newHashSet(3, 4, 5, 6);
        Sets.SetView<Integer> s = Sets.union(set1, set2);
        System.out.println(s);
        s = Sets.intersection(set1, set2);
        System.out.println(s);
        s = Sets.difference(set1, set2);
        System.out.println(s);
        s = Sets.symmetricDifference(set1, set2);
        System.out.println(s);
        //笛卡尔积
        Set<List<Integer>> s1 = Sets.cartesianProduct(set1, set2);
        System.out.println(s1);
        //所有子集
        Set<Set<Integer>> s2 = Sets.powerSet(set1);
        System.out.println(s2);
    }

}
