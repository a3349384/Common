package cn.zmy.common.utils;

import java.security.MessageDigest;

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
            byte[] hash = md5.digest();
            return convertToHex(hash);
        }
        catch (Exception e)
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

    private static String convertToHex(byte[] hash)
    {
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash)
        {
            if ((b & 0xFF) < 0x10)
            {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
