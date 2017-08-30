package cn.zmy.common.http.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by zmy on 2016/6/27 0027.
 */
public class SaveAllCookieJar implements CookieJar
{
    private Context context;

    public SaveAllCookieJar(Context context)
    {
        this.context = context.getApplicationContext();
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        if (cookies != null && cookies.size() > 0)
        {
            CookieSaveHelper.getInstance(context).addCookie(url,cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url)
    {
        return CookieSaveHelper.getInstance(context).getCookies(url);
    }
}
