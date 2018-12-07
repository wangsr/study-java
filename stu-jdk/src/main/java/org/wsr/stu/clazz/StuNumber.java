package org.wsr.stu.clazz;

/**
 * Created by wangshengren on 2017/4/24.
 */
public class StuNumber {
    public static void main(String[] args) {
        /**
         *
         抽象类 Number 是 BigDecimal、BigInteger、Byte、Double、Float、Integer、Long 和 Short 类的超类。
         Number 的子类必须提供将表示的数值转换为 byte、double、float、int、long 和 short 的方法。
         */
        Number n = new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }

            @Override
            public byte byteValue() {
                return super.byteValue();
            }

            @Override
            public short shortValue() {
                return super.shortValue();
            }
        };
    }
}
