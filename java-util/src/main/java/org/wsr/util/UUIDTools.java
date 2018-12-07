package org.wsr.util;

import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

/**
 * @author wangsr
 * @date 2017/7/28
 */
public class UUIDTools {
    /**
     * 随机生成uuid
     *
     * @return
     */
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 随机生成base64形式的的uuid，长度为22字符
     *
     * @return
     */
    public static String base64UUID() {
        UUID uuid = UUID.randomUUID();
        return toBase64UUID(uuid);
    }

    /**
     * 将默认uuid压缩成base64的形式
     *
     * @param uuid
     * @return
     */
    public static String toBase64UUID(String uuid) {
        UUID id = UUID.fromString(uuid);
        return toBase64UUID(id);
    }

    /**
     * 将base64的形式的uuid转成默认表示形式
     *
     * @param base64UUID
     * @return
     */
    public static String toHexUUID(String base64UUID) {
        if (base64UUID.length() != 22) {
            throw new IllegalArgumentException("Invalid uuid!");
        }
        byte[] byUuid = Base64.decodeBase64(base64UUID + "==");
        long most = bytes2long(byUuid, 0);
        long least = bytes2long(byUuid, 8);
        UUID uuid = new UUID(most, least);
        return uuid.toString();
    }

    /**
     * 将默认uuid压缩成base64的形式,长度为22个字符
     *
     * @param uuid
     * @return
     */
    public static String toBase64UUID(UUID uuid) {
        byte[] uuidBuffer = new byte[16];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, uuidBuffer, 0);
        long2bytes(least, uuidBuffer, 8);
        String base64UUID = Base64.encodeBase64URLSafeString(uuidBuffer);
        return base64UUID;
    }

    protected static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }

    protected static long bytes2long(byte[] bytes, int offset) {
        long value = 0;
        for (int i = 7; i > -1; i--) {
            value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
        }
        return value;
    }
}
