/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.MD5
 */
package com.common.util;

import java.security.MessageDigest;

/*
 * Exception performing whole class analysis ignored.
 */
public class MD5 {
    public static String getStr(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return MD5.toHex((byte[])messageDigest.digest());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String toHex(byte[] buffer) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; ++i) {
            sb.append(Character.forDigit((buffer[i] & 0xF0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0xF, 16));
        }
        return sb.toString().toLowerCase();
    }
}

