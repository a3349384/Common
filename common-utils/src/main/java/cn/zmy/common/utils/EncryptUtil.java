package cn.zmy.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zmy on 2016/6/8 0008.
 */
public class EncryptUtil
{
    public static String toMd5(String s)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(s.getBytes());
            byte[] m = md5.digest();
            String md5String = getString(m);
            return md5String.replaceAll("-", "");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String toSHA1(String text)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] textBytes = text.getBytes("utf-8");
            md.update(textBytes, 0, textBytes.length);
            byte[] sha1hash = md.digest();
            return convertToHex(sha1hash);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    private static String getString(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes)
        {
            sb.append(b);
        }
        return sb.toString();
    }

    private static String convertToHex(byte[] data)
    {
        StringBuilder buf = new StringBuilder();
        for (byte b : data)
        {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do
            {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}
