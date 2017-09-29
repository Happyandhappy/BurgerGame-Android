package com.magmamobile.mmusia.parser;

import android.os.SystemClock;
import com.magmamobile.mmusia.MCommon;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.*;

public class JSonParser
{

    protected static SimpleDateFormat formaterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static DefaultHttpClient http;
    private static ArrayList tmpApiLst = new ArrayList();
    private static boolean verbose = false;

    public JSonParser()
    {
    }

    public static void extractJsonArrayNames(JSONArray jsonarray, String s)
    {
        int i = 0;
        do
        {
            if(i >= jsonarray.length())
            {
                return;
            }
            try
            {
                extractJsonNames(jsonarray.getJSONObject(i), s, true);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            i++;
        } while(true);
    }

    public static void extractJsonNames(JSONObject jsonobject, String s, boolean flag)
    {
        String s1;
        Object obj;
        String s2;
        if(!verbose)
            return;
        for(int i = 0; i < jsonobject.names().length(); i++)
        {
            s1 = "";
            try {
                s2 = (new StringBuilder(String.valueOf(s))).append("/").append(jsonobject.names().getString(i)).toString();
                s1 = s2;
            }catch (Exception e) {

            }
            try
            {
                obj = jsonobject.get(jsonobject.names().getString(i));
                if(obj instanceof JSONObject)
                {
                    if(!tmpApiLst.contains(s1))
                    {
                        tmpApiLst.add(s1);
                        MCommon.Log_d(s1);
                    }
                    extractJsonNames((JSONObject)obj, s1, true);
                }else if(obj instanceof JSONStringer){
                    MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[StringER]").toString());
                }else if(obj instanceof JSONTokener){
                    MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[Token]").toString());
                }else  if(obj instanceof JSONArray){
                    if(!tmpApiLst.contains((new StringBuilder(String.valueOf(s1))).append("/[Array]").toString()))
                    {
                        tmpApiLst.add((new StringBuilder(String.valueOf(s1))).append("/[Array]").toString());
                        MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[Array]").toString());
                    }
                    extractJsonArrayNames((JSONArray)obj, s1);
                }else if(obj instanceof String){
                    if(!tmpApiLst.contains((new StringBuilder(String.valueOf(s1))).append("/[String]").toString()))
                    {
                        tmpApiLst.add((new StringBuilder(String.valueOf(s1))).append("/[String]").toString());
                        MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[String]").toString());
                    }
                }else if(obj instanceof Integer){
                    if(!tmpApiLst.contains((new StringBuilder(String.valueOf(s1))).append("/[int]").toString()))
                    {
                        tmpApiLst.add((new StringBuilder(String.valueOf(s1))).append("/[int]").toString());
                        MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[int]").toString());
                    }
                }else if(obj instanceof Boolean){
                    if(!tmpApiLst.contains((new StringBuilder(String.valueOf(s1))).append("/[bool]").toString()))
                    {
                        tmpApiLst.add((new StringBuilder(String.valueOf(s1))).append("/[bool]").toString());
                        MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[bool]").toString());
                    }
                }else if(obj instanceof Long){
                    if(!tmpApiLst.contains((new StringBuilder(String.valueOf(s1))).append("/[long]").toString()))
                    {
                        tmpApiLst.add((new StringBuilder(String.valueOf(s1))).append("/[long]").toString());
                        MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[long]").toString());
                    }
                }else if(obj instanceof Double){
                    if(!tmpApiLst.contains((new StringBuilder(String.valueOf(s1))).append("/[double]").toString()))
                    {
                        tmpApiLst.add((new StringBuilder(String.valueOf(s1))).append("/[double]").toString());
                        MCommon.Log_d((new StringBuilder(String.valueOf(s1))).append("/[double]").toString());
                    }
                }else if(obj.toString() != "null"){
                    MCommon.Log_d((new StringBuilder("OULALA !!! C'est quoi ce truc ? : ")).append(s1).append(" / ").append(obj.toString()).toString());
                }
            }
            catch(JSONException jsonexception1)
            {
                MCommon.Log_e((new StringBuilder(String.valueOf(s1))).append(" ERROR").toString());
                jsonexception1.printStackTrace();
            }
        }
    }

    public static String sendJSonRequest(String s)
        throws Exception
    {
        return sendJSonRequest(s, true);
    }

    public static String sendJSonRequest(String s, boolean flag)
        throws Exception
    {
        float f = SystemClock.currentThreadTimeMillis();
        if(http == null)
        {
            BasicHttpParams basichttpparams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basichttpparams, 20000);
            HttpConnectionParams.setSoTimeout(basichttpparams, 20000);
            http = new DefaultHttpClient(basichttpparams);
        }
        HttpGet httpget = new HttpGet(s);
        httpget.addHeader("Accept-Encoding", "gzip, deflate");
        HttpResponse httpresponse = http.execute(httpget);
        Object obj = httpresponse.getEntity().getContent();
        MCommon.Log_i((new StringBuilder("sendJSonRequest :: ")).append(s).toString());
        if(httpresponse.getHeaders("Content-Encoding").length > 0 && httpresponse.getHeaders("Content-Encoding")[0].getValue().toLowerCase().equals("gzip"))
        {
            obj = new GZIPInputStream(((java.io.InputStream) (obj)));
        }
        MCommon.Log_w((new StringBuilder("JSON Feed load time : ")).append(((float)SystemClock.currentThreadTimeMillis() - f) / 1000F).append(" sec").toString());
        return MCommon.generateString(((java.io.InputStream) (obj)));
    }

    public static String sendJSonRequestPost(String s, List list)
        throws Exception
    {
        return sendJSonRequestPost(s, list, true);
    }

    public static String sendJSonRequestPost(String s, List list, boolean flag)
        throws Exception
    {
        float f = SystemClock.currentThreadTimeMillis();
        if(http == null)
        {
            BasicHttpParams basichttpparams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basichttpparams, 20000);
            HttpConnectionParams.setSoTimeout(basichttpparams, 20000);
            http = new DefaultHttpClient(basichttpparams);
        }
        HttpPost httppost = new HttpPost(s);
        httppost.addHeader("Accept-Charset", "UTF-8");
        httppost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
        httppost.addHeader("Accept-Encoding", "gzip, deflate");
        HttpResponse httpresponse = http.execute(httppost);
        Object obj = httpresponse.getEntity().getContent();
        MCommon.Log_i((new StringBuilder("sendJSonRequest :: ")).append(s).toString());
        if(httpresponse.getHeaders("Content-Encoding").length > 0 && httpresponse.getHeaders("Content-Encoding")[0].getValue().toLowerCase().equals("gzip"))
        {
            obj = new GZIPInputStream(((java.io.InputStream) (obj)));
        }
        MCommon.Log_w((new StringBuilder("JSON Feed load time : ")).append(((float)SystemClock.currentThreadTimeMillis() - f) / 1000F).append(" sec").toString());
        return MCommon.generateString(((java.io.InputStream) (obj)));
    }

}
