package part01;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Predicates;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterables;

import java.util.Arrays;

/**
 * Created by wangshengren on 17/1/23.
 */
public class OjbectsDemo {
    public static void main(String[] args) {
        Objects.equal("a", null);
        Objects.hashCode(1, 2, 3, 4);
        MoreObjects.firstNonNull(null, 1);
        //返回第一个不为空的对象
        Iterables.find(Arrays.asList(1, 2, 34), Predicates.notNull());
        MoreObjects.toStringHelper("name").omitNullValues().add("name", "value");
        //compare


    }

    class Per implements Comparable<Per> {
        private String name;
        private String nick;

        public String getName() {
            return name;
        }



        public void setName(String name) {
            this.name = name;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        @Override
        public int compareTo(Per o) {
            //compare
            return ComparisonChain.start().compare(this.getName(), o.getName()).result();
        }
    }
}
