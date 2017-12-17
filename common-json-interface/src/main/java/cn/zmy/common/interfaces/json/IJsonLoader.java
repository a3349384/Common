package cn.zmy.common.interfaces.json;

import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by zmy on 2017/12/17.
 */

public interface IJsonLoader
{
    <T> T fromString(String s, Type type);

    <T> T fromString(String s,Class<T> type);

    <T> T fromInputStream(InputStream inputStream, Class<T> type);

    String toString(Object o);
}
