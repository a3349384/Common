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
            return md5String.replaceAll("-","");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
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
}
