package cn.zmy.common.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Created by zmy on 2016/4/20 0020.
 */
public class JsonUtil
{
    private static Gson gson;

    static
    {
        gson = new Gson();
    }

    public static <T> T fromString(String s, Type type)
    {
        return gson.fromJson(s,type);
    }

    public static <T> T fromString(String s,Class<T> type)
    {
        return gson.fromJson(s,type);
    }

    public static <T> T fromInputStream(InputStream inputStream, Class<T> type)
    {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
        T t = gson.fromJson(jsonReader, type);
        try
        {
            jsonReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return t;
    }

    public static String toString(Object o)
    {
        return gson.toJson(o);
    }
}
