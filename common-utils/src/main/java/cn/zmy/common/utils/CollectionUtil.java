package cn.zmy.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zmy on 2016/6/14 0014.
 */
public class CollectionUtil
{
    public static <K,V> Map<K,ArrayList<V>> groupBy(List<V> list, IGroupBy<K,V> groupBy)
    {
        if (list == null || list.size() == 0)
        {
            return null;
        }

        Map<K,ArrayList<V>> map = new HashMap<>();
        for (V item : list)
        {
            K key = groupBy.byWhat(item);
            if (map.containsKey(key))
            {
                map.get(key).add(item);
            }
            else
            {
                ArrayList<V> group = new ArrayList<>();
                group.add(item);
                map.put(key,group);
            }
        }

        return map;
    }

    public static <K,V> List<K> select(List<V> list,ISelect<K,V> select)
    {
        if (list == null || list.size() == 0)
        {
            return null;
        }

        ArrayList<K> selectResult = new ArrayList<>();
        for (V item : list)
        {
            selectResult.add(select.select(item));
        }

        return selectResult;
    }

    public static <V> List<V> where(List<V> list,IWhere<V> where)
    {
        if (list == null)
        {
            return null;
        }

        ArrayList<V> whereResult = new ArrayList<>();
        for (V item : list)
        {
            if (where.where(item))
            {
                whereResult.add(item);
            }
        }

        return whereResult;
    }

    public static <V> void foreach(List<V> list,IForeach<V> foreach)
    {
        if (Util.getCollectSize(list) == 0)
        {
            return;
        }

        for (V item : list)
        {
            foreach.run(item);
        }
    }

    public static <V> V firstOrDefault(List<V> list,IWhere<V> where)
    {
        if (list == null)
        {
            return null;
        }

        V result = null;
        for (V item : list)
        {
            if (where.where(item))
            {
                result = item;
                break;
            }
        }

        return result;
    }

    public interface IGroupBy<K,V>
    {
        K byWhat(V v);
    }

    public interface ISelect<K,V>
    {
        K select(V v);
    }

    public interface IWhere<V>
    {
        boolean where(V v);
    }

    public interface IForeach<V>
    {
        void run(V v);
    }
}
