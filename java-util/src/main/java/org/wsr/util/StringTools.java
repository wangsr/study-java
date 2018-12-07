package org.wsr.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author wangsr
 * @date 2018/8/2
 */
@Slf4j
public class StringTools {

    /**
     * 将source使用separator分隔，通过transfer进行转换item，返回list。
     * <p>
     * <b>
     * 常见使用场景：splitToList("1,2,3,4,5,6", ",", Integer::valueOf)
     * <b/>
     * <b>
     * API使用注意：
     * 1、分割后的字符串会被trim；
     * 2、转换失败的字符串会忽略
     * <b/>
     * </p>
     *
     * @param source    源字符串
     * @param separator 分隔符
     * @param transfer  转换函数
     * @param <T>       要转换成的目标类型
     * @return
     */
    @Nonnull
    public static <T> List<T> splitToList(@Nullable String source, @Nonnull String separator, @Nonnull Function<String, T> transfer) {
        Preconditions.checkNotNull(separator, "separator not null");
        Preconditions.checkNotNull(transfer, "transfer not null");
        if (StringUtils.isNotBlank(source)) {
            return StreamSupport.stream(Splitter.on(separator).trimResults().omitEmptyStrings().split(source).spliterator(), false).map(item -> {
                T ret = null;
                try {
                    ret = transfer.apply(item);
                } catch (Exception e) {
                    log.warn("splitToList contains failed. source is '{}', failed item is '{}'", source, item);
                    log.warn(e.getMessage(), e);
                }
                return ret;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Nonnull
    public static <T> Set<T> splitToSet(@Nullable String source, @Nonnull String separator, @Nonnull Function<String, T> transfer) {
        return new HashSet<>(splitToList(source, separator, transfer));
    }

    /**
     * 应该移到单元测试当中
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Long> list = splitToList("1,2,3,4,5,6", ",", Long::valueOf);
        System.out.println(list);
        //忽略转换异常的item
        list = splitToList("1,b,3,4,5,f", ",", Long::valueOf);
        System.out.println(list);
    }
}
