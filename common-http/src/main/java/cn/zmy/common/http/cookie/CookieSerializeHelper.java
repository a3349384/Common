package cn.zmy.common.http.cookie;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

import okhttp3.Cookie;

/**
 * Created by zmy on 2016/6/27 0027.
 */
public class CookieSerializeHelper
{
    public static String encodeCookie(Cookie cookie)
    {
        if (cookie == null)
        {
            return "";
        }
        byte[] data;
        try
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            writeToStream(cookie,outputStream);
            data = os.toByteArray();
            
            outputStream.close();
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
        if (data == null)
        {
            return "";
        }
        return byteArrayToHexString(data);
    }
    
    public static Cookie decodeCookie(String cookieString)
    {
        byte[] bytes = hexStringToByteArray(cookieString);

        try
        {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Cookie cookie = CookieSerializeHelper.readFromSteam(objectInputStream);
            
            try
            {
                objectInputStream.close();
                byteArrayInputStream.close();
            }
            catch (Exception ignored){}
            
            return cookie;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static synchronized void writeToStream(Cookie cookie, ObjectOutputStream out) throws IOException
    {
        out.writeObject(cookie.name());
        out.writeObject(cookie.value());
        out.writeLong(cookie.expiresAt());
        out.writeObject(cookie.domain());
        out.writeObject(cookie.path());
//        out.writeBoolean(cookies.secure());
//        out.writeBoolean(cookies.httpOnly());
//        out.writeBoolean(cookies.hostOnly());
//        out.writeBoolean(cookies.persistent());
    }

    public static synchronized Cookie readFromSteam(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        long expiresAt = in.readLong();
        String domain = (String) in.readObject();
        String path = (String) in.readObject();
//        boolean secure = in.readBoolean();
//        boolean httpOnly = in.readBoolean();
//        boolean hostOnly = in.readBoolean();
//        boolean persistent = in.readBoolean();
        Cookie.Builder builder = new Cookie.Builder();
        builder = builder.name(name);
        builder = builder.value(value);
        builder = builder.expiresAt(expiresAt);
        builder = builder.hostOnlyDomain(domain);
        //builder = hostOnly ? builder.hostOnlyDomain(domain) : builder.domain(domain);
        builder = builder.path(path);
        //builder = secure ? builder.secure() : builder;
        //builder = httpOnly ? builder.httpOnly() : builder;

        return builder.build();
    }

    public static String byteArrayToHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes)
        {
            int v = element & 0xff;
            if (v < 16)
            {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    public static byte[] hexStringToByteArray(String hexString)
    {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
