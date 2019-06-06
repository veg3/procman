package edu.mmc.util;

import sun.applet.Main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static edu.mmc.util.MdHex.toHex;

public class MdHex {
    private static long previous;

    public synchronized static String md5(String id) {
        try {
            long current = System.currentTimeMillis();
            if (current == previous) {
                current++;
            }
            previous = current;
            byte[] now = new Long(current).toString().getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(id.getBytes());
            md.update(now);
            return MdHex.toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String toHex(byte[] buffer) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
        }
        return sb.toString();
    }
}