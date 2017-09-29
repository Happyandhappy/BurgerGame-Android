package com.magmamobile.mmusia.parser;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils
{

    private static final boolean verboseLog = false;

    public JsonUtils()
    {
    }

    public static Date getJSDate(JSONObject jsonobject, String s)
        throws JSONException
    {
        return getJSDate(jsonobject, s, new Date());
    }

    public static Date getJSDate(JSONObject jsonobject, String s, Date date)
        throws JSONException
    {
        if(jsonobject.has(s))
        {
            date = new Date(Date.parse(jsonobject.getString(s)));
        }
        return date;
    }

    public static double getJSDouble(JSONObject jsonobject, String s)
        throws JSONException
    {
        return getJSDouble(jsonobject, s, 0.0D);
    }

    public static double getJSDouble(JSONObject jsonobject, String s, double d)
        throws JSONException
    {
        if(jsonobject.has(s))
        {
            d = jsonobject.getDouble(s);
        }
        return d;
    }

    public static int getJSInt(JSONObject jsonobject, String s)
        throws JSONException
    {
        return getJSInt(jsonobject, s, 0);
    }

    public static int getJSInt(JSONObject jsonobject, String s, int i)
        throws JSONException
    {
        if(jsonobject.has(s))
        {
            i = jsonobject.getInt(s);
        }
        return i;
    }

    public static long getJSLong(JSONObject jsonobject, String s)
        throws JSONException
    {
        return getJSLong(jsonobject, s, 0L);
    }

    public static long getJSLong(JSONObject jsonobject, String s, long l)
        throws JSONException
    {
        if(jsonobject.has(s))
        {
            l = jsonobject.getLong(s);
        }
        return l;
    }

    public static String getJSString(JSONObject jsonobject, String s)
        throws JSONException
    {
        return getJSString(jsonobject, s, "");
    }

    public static String getJSString(JSONObject jsonobject, String s, String s1)
        throws JSONException
    {
        if(jsonobject.has(s))
        {
            s1 = jsonobject.getString(s);
        }
        return s1;
    }
}
