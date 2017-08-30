package cn.zmy.common.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zmy on 2016/4/26 0026.
 */
public class Typer<T>
{
    public final Type type;

    protected Typer()
    {
        Type superClass = getClass().getGenericSuperclass();
        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }
}
