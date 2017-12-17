package cn.zmy.common.interfaces.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zmy on 2016/4/26 0026.
 */
public class TypeHelper<T>
{
    public final Type type;

    protected TypeHelper()
    {
        Type superClass = getClass().getGenericSuperclass();
        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }
}
