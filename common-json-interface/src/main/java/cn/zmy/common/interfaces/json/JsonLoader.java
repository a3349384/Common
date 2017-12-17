package cn.zmy.common.interfaces.json;

import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by zmy on 2017/12/17.
 */

public class JsonLoader implements IJsonLoader
{
    public static final JsonLoader instance = new JsonLoader();
    private IJsonLoader mBase;

    public JsonLoader()
    {
        //从设计模式来说，抽象不应该依赖于细节，但是此处依赖细节可以很好的解决问题。更关键的是这里的代码不会经常发生变动。
        try
        {
            mBase = (IJsonLoader) Class.forName("cn.zmy.common.gson.GsonLoader").newInstance();
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public <T> T fromString(String s, Type type)
    {
        return mBase.fromString(s, type);
    }

    @Override
    public <T> T fromString(String s, Class<T> type)
    {
        return mBase.fromString(s, type);
    }

    @Override
    public <T> T fromInputStream(InputStream inputStream, Class<T> type)
    {
        return mBase.fromInputStream(inputStream, type);
    }

    @Override
    public String toString(Object o)
    {
        return mBase.toString(o);
    }
}
