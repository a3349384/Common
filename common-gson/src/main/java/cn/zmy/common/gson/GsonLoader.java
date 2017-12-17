package cn.zmy.common.gson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import cn.zmy.common.interfaces.json.IJsonLoader;

/**
 * Created by zmy on 2016/4/20 0020.
 */
public class GsonLoader implements IJsonLoader
{
    private Gson mGson;

    public GsonLoader()
    {
        mGson = new Gson();
    }

    public <T> T fromString(String s, Type type)
    {
        return mGson.fromJson(s, type);
    }

    public <T> T fromString(String s, Class<T> type)
    {
        return mGson.fromJson(s, type);
    }

    public <T> T fromInputStream(InputStream inputStream, Class<T> type)
    {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
        T t = mGson.fromJson(jsonReader, type);
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

    public String toString(Object o)
    {
        return mGson.toJson(o);
    }
}
