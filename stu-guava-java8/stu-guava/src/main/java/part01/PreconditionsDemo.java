package part01;

import com.google.common.base.Preconditions;

/**
 * Created by wangshengren on 17/1/23.
 */
public class PreconditionsDemo {
    public static void main(String[] args) {
        Preconditions.checkArgument(true);
        Preconditions.checkState(true);
        Preconditions.checkNotNull(null);
        //*索引值常用来查找列表、字符串或数组中的元素，如List.get(int), String.charAt(int)
        //*位置值和位置范围常用来截取列表、字符串或数组，如List.subList(int，int), String.substring(int)
        Preconditions.checkElementIndex(1, 10);
        Preconditions.checkPositionIndex(1, 10);
        Preconditions.checkPositionIndexes(1, 5, 10);
    }
}
