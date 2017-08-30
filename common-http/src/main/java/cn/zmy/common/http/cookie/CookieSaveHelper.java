package cn.zmy.common.http.cookie;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zmy.common.utils.CollectionUtil;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by zmy on 2016/6/27 0027.
 */
public class CookieSaveHelper
{

    public static CookieSaveHelper instance;

    private Context context;
    private Map<String,List<Cookie>> cookieMap;//url---->List<Cookie>

    public static synchronized CookieSaveHelper getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new CookieSaveHelper(context);
        }

        return instance;
    }

    private CookieSaveHelper(Context context)
    {
        this.context = context.getApplicationContext();
        this.cookieMap = new HashMap<>();
        Map<String,String> map = (Map<String, String>) getSharedPreferences().getAll();
        for (String key : map.keySet())
        {
            ArrayList<Cookie> cookies = new ArrayList<>();
            try
            {
                JSONArray array = new JSONArray(map.get(key));
                for (int i = 0; i < array.length(); i++)
                {
                    Cookie cookie = CookieSerializeHelper.decodeCookie(array.getString(i));
                    if (cookie != null)
                    {
                        cookies.add(cookie);
                    }
                }
                this.cookieMap.put(key,cookies);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void addCookie(HttpUrl url, List<Cookie> cookieList)
    {
        if (url == null || cookieList == null)
        {
            return;
        }

        List<Cookie> savedCookies = getCookies(url);
        List<Cookie> allCookies = new ArrayList<>(cookieList);
        if (savedCookies != null)
        {
            //获取所有已保存的Cookie的名称
            List<String> newCookieNames = CollectionUtil.select(cookieList, new CollectionUtil.ISelect<String, Cookie>()
            {
                @Override
                public String select(Cookie cookie)
                {
                    return cookie.name();
                }
            });

            //如果新Cookie和已保存的Cookie的名称冲突，则使新Cookie替换老Cookie
            for (String name : newCookieNames)
            {
                for (Cookie savedCookie : savedCookies)
                {
                    if (savedCookie.name().contentEquals(name))
                    {
                        savedCookies.remove(savedCookie);
                        break;
                    }
                }
            }
            allCookies.addAll(savedCookies);
        }

        //只保留未过期的Cookie
        List<Cookie> willSaveCookies = CollectionUtil.where(allCookies, new CollectionUtil.IWhere<Cookie>()
        {
            @Override
            public boolean where(Cookie cookie)
            {
                return cookie.expiresAt() > System.currentTimeMillis();
            }
        });
        cookieMap.put(url.host(),willSaveCookies);
        saveCookies(url,willSaveCookies);
    }

    public List<Cookie> getCookies(HttpUrl url)
    {
        List<Cookie> cookies = cookieMap.get(url.host());
        return cookies == null ? new ArrayList<Cookie>() : cookies;
    }

    public void clear()
    {
        getSharedPreferences().edit().clear().commit();
        cookieMap.clear();
    }

    private void saveCookies(HttpUrl url, List<Cookie> cookieList)
    {
        List<String> cookiesString = new ArrayList<>(cookieList.size());
        for (Cookie cookie : cookieList)
        {
            cookiesString.add(CookieSerializeHelper.encodeCookie(cookie));
        }

        JSONArray array = new JSONArray(cookiesString);
        getSharedPreferences().edit().putString(url.host(),array.toString()).commit();
    }

    private SharedPreferences getSharedPreferences()
    {
        return context.getApplicationContext().getSharedPreferences("COOKIE_LIST",Context.MODE_PRIVATE);
    }
}
