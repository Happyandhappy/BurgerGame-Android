package com.magmamobile.mmusia.parser.parsers;

import android.content.Context;
import android.os.SystemClock;
import com.magmamobile.mmusia.MCommon;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.parser.JSonParser;
import com.magmamobile.mmusia.parser.JsonUtils;
import com.magmamobile.mmusia.parser.data.*;
import java.io.*;
import java.util.List;
import org.json.*;

public class JSonNews extends JSonParser
{

    public JSonNews()
    {
    }

    public static ApiBase loadItems(Context context, String s, List list, boolean flag)
        throws JSONException
    {
        boolean flag1;
        float f;
        JSONArray jsonarray;
        int i;
        JSONArray jsonarray1;
        int j;
        JSONArray jsonarray2;
        int k;
        ApiBase apibase;
        JSONObject jsonobject;
        int l;
        ItemNews aitemnews[];
        ItemAppUpdate aitemappupdate[];
        ItemMoreGames aitemmoregames[];
        flag1 = true;
        f = SystemClock.currentThreadTimeMillis();
        apibase = new ApiBase();
        aitemnews = new ItemNews[0];
        aitemappupdate = new ItemAppUpdate[0];
        aitemmoregames = new ItemMoreGames[0];
        String s1;
        try {
            String s2 = sendJSonRequestPost(s, list);
            s1 = s2;
        }catch(Exception e) {
            s1 = "";
        }
        MCommon.Log_d((new StringBuilder("rawJSON from api : ")).append(s1).toString());
        if(flag)
        {
            return apibase;
        }
        boolean flag3;
        boolean flag2 = s1.equals("");
        flag3 = false;
        if(flag2)
        {
            s1 = readRawJson(context, "mmusia.json");
            MCommon.Log_d((new StringBuilder("rawJSON from cache : ")).append(s1).toString());
            flag1 = false;
            flag3 = true;
        }
        if(!s1.equals("")) {
            MCommon.Log_d(s1);
            try {
                jsonobject = new JSONObject(s1);
            } catch (JSONException jsonexception) {
                flag3 = true;
                MCommon.Log_d((new StringBuilder("try to load from cache : ")).append(s1).toString());
                s1 = readRawJson(context, "mmusia.json");
                jsonobject = new JSONObject(s1);
                jsonexception.printStackTrace();
                flag1 = false;
            }

            try {
                if (!flag3) {
                    apibase.HasNewNews = jsonobject.getInt("HasNewNews");
                    apibase.HasNewUpdates = jsonobject.getInt("HasNewUpdates");
                    apibase.HasNewVersionAvailable = jsonobject.getInt("newVersionAvailable");
                    apibase.promoId = jsonobject.getInt("promoId");
                    apibase.promoTitle = jsonobject.getString("promoTitle");
                    apibase.promoDesc = jsonobject.getString("promoDesc");
                    apibase.promoUrl = MCommon.checkURL((new StringBuilder(String.valueOf(jsonobject.getString("promoUrl")))).append("-").append(MMUSIA.RefererComplement).toString());
                    apibase.promoIconUrl = jsonobject.getString("promoIconUrl");
                    apibase.promoPName = jsonobject.getString("promoPName");
                    apibase.promoId2 = jsonobject.getInt("promoId2");
                    apibase.promoTitle2 = jsonobject.getString("promoTitle2");
                    apibase.promoDesc2 = jsonobject.getString("promoDesc2");
                    apibase.promoUrl2 = MCommon.checkURL((new StringBuilder(String.valueOf(jsonobject.getString("promoUrl2")))).append("-").append(MMUSIA.RefererComplement).toString());
                    apibase.promoIconUrl2 = jsonobject.getString("promoIconUrl2");
                    apibase.promoPName2 = jsonobject.getString("promoPName2");
                    apibase.promoId3 = jsonobject.getInt("promoId3");
                    apibase.promoTitle3 = jsonobject.getString("promoTitle3");
                    apibase.promoDesc3 = jsonobject.getString("promoDesc3");
                    apibase.promoUrl3 = MCommon.checkURL((new StringBuilder(String.valueOf(jsonobject.getString("promoUrl3")))).append("-").append(MMUSIA.RefererComplement).toString());
                    apibase.promoIconUrl3 = jsonobject.getString("promoIconUrl3");
                    apibase.promoPName3 = jsonobject.getString("promoPName3");
                } else {
                    apibase.HasNewNews = 0;
                    apibase.HasNewUpdates = 0;
                    apibase.HasNewVersionAvailable = 0;
                    apibase.promoId = 0;
                    apibase.promoTitle = "";
                    apibase.promoDesc = "";
                    apibase.promoUrl = "";
                    apibase.promoIconUrl = "";
                    apibase.promoPName = "";
                    apibase.promoId2 = 0;
                    apibase.promoTitle2 = "";
                    apibase.promoDesc2 = "";
                    apibase.promoUrl2 = "";
                    apibase.promoIconUrl2 = "";
                    apibase.promoPName2 = "";
                    apibase.promoId3 = 0;
                    apibase.promoTitle3 = "";
                    apibase.promoDesc3 = "";
                    apibase.promoUrl3 = "";
                    apibase.promoIconUrl3 = "";
                    apibase.promoPName3 = "";
                }
                apibase.appodayId = jsonobject.getInt("appodayId");
                apibase.appodayName = jsonobject.getString("appodayName");
                apibase.appodayUrl = MCommon.checkURL(jsonobject.getString("appodayUrl"));
                apibase.appodayIconUrl = jsonobject.getString("appodayIconUrl");
                jsonarray = jsonobject.getJSONArray("news");
                aitemnews = new ItemNews[jsonarray.length()];
                i = 0;
                while (i < jsonarray.length()) {
                    JSONObject jsonobject1 = jsonarray.getJSONObject(i);
                    aitemnews[i] = new ItemNews();
                    aitemnews[i].NewsID = jsonobject1.getInt("NewsID");
                    aitemnews[i].NewsTitle = jsonobject1.getString("NewsTitle");
                    aitemnews[i].NewsLanguage = jsonobject1.getString("NewsLanguage");
                    aitemnews[i].NewsDesc = jsonobject1.getString("NewsDesc");
                    aitemnews[i].NewsDate = JsonUtils.getJSDate(jsonobject1, "NewsDate");
                    aitemnews[i].NewsUrlLink = jsonobject1.getString("NewsUrlLink");
                    aitemnews[i].NewsUrlMarket = MCommon.checkURL((new StringBuilder(String.valueOf(jsonobject1.getString("NewsUrlMarket")))).append("-").append(MMUSIA.RefererComplement).toString());
                    aitemnews[i].imgUrl = jsonobject1.getString("NewsImgUrl");
                    MCommon.Log_d(aitemnews[i].NewsUrlMarket);
                    i++;
                }
                jsonarray1 = jsonobject.getJSONArray("updates");
                aitemappupdate = new ItemAppUpdate[jsonarray1.length()];
                j = 0;
                while (j < jsonarray1.length()) {
                    JSONObject jsonobject2 = jsonarray1.getJSONObject(j);
                    aitemappupdate[j] = new ItemAppUpdate();
                    aitemappupdate[j].Name = jsonobject2.getString("Name");
                    aitemappupdate[j].ChangeLog = jsonobject2.getString("ChangeLog");
                    aitemappupdate[j].MarketLink = MCommon.checkURL(jsonobject2.getString("MarketLink"));
                    aitemappupdate[j].PackageName = jsonobject2.getString("PackageName");
                    aitemappupdate[j].UpdateDate = JsonUtils.getJSDate(jsonobject2, "UpdateDate");
                    aitemappupdate[j].Version = jsonobject2.getString("Version");
                    aitemappupdate[j].VersionName = jsonobject2.getString("VersionName");
                    j++;
                }
                jsonarray2 = jsonobject.getJSONArray("moregames");
                aitemmoregames = new ItemMoreGames[jsonarray2.length()];
                k = 0;
                l = jsonarray2.length();
                while (k < l) {
                    JSONObject jsonobject3 = jsonarray2.getJSONObject(k);
                    aitemmoregames[k] = new ItemMoreGames();
                    aitemmoregames[k].title = jsonobject3.getString("title");
                    aitemmoregames[k].pname = jsonobject3.getString("pname");
                    aitemmoregames[k].urlImg = jsonobject3.getString("urlImg");
                    aitemmoregames[k].urlMarket = MCommon.checkURL((new StringBuilder(String.valueOf(jsonobject3.getString("urlMarket")))).append("-").append(MMUSIA.RefererComplement).toString());
                    aitemmoregames[k].free = jsonobject3.getInt("free");
                    k++;
                }
            } catch (Exception e) {
                MCommon.Log_e((new StringBuilder("MMUSIA MORE GAMES LIST Error : ")).append(e.getMessage()).toString());
                e.printStackTrace();
            }
            if (flag1) {
                saveRawJSon(context, "mmusia.json", s1);
            }
        }
_L4:
        MCommon.Log_w((new StringBuilder("JSON Parse time : ")).append(((float)SystemClock.currentThreadTimeMillis() - f) / 1000F).append(" sec").toString());
        apibase.news = aitemnews;
        apibase.updates = aitemappupdate;
        apibase.moregames = aitemmoregames;
        return apibase;
    }

    public static String readRawJson(Context context, String s)
    {
        File file = new File((new StringBuilder(String.valueOf(context.getFilesDir().getAbsolutePath()))).append("/").append(s).toString());
        StringBuilder stringbuilder;
        BufferedReader bufferedreader;
        IOException ioexception;
        String s1;
        try
        {
            if(file.exists())
            {
                stringbuilder = new StringBuilder();
                bufferedreader = new BufferedReader(new FileReader(file));
                while(true) {
                    s1 = bufferedreader.readLine();
                    if (s1 == null) {
                        break;
                    }
                    stringbuilder.append(s1);
                    stringbuilder.append('\n');
                }
                bufferedreader.close();
                return stringbuilder.toString();
            }
            MCommon.Log_d("file not exist in cache");
        }
        catch(Exception exception)
        {
        }
        return "";
    }

    private static void saveRawJSon(Context context, String s, String s1)
    {
        if(s1 == null || s1.equals(""))
            return;
        synchronized (s1){
            File file = new File((new StringBuilder(String.valueOf(context.getFilesDir().getAbsolutePath()))).append("/").append(s).toString());
            try {
                FileOutputStream fileoutputstream = new FileOutputStream(file);
                OutputStreamWriter outputstreamwriter = new OutputStreamWriter(fileoutputstream);
                outputstreamwriter.write(s1);
                outputstreamwriter.close();
                fileoutputstream.close();
                MCommon.Log_d((new StringBuilder("Saved ")).append(file.getAbsolutePath()).toString());
            }catch(Exception e) {
                MCommon.Log_e((new StringBuilder("Cache not Saved !!  ")).append(e.getMessage()).toString());
            }
        }
    }
}
