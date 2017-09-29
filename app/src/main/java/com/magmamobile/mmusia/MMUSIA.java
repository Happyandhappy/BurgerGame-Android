package com.magmamobile.mmusia;

import android.app.*;
import android.content.*;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.magmamobile.mmusia.activities.MMUSIAActivity;
import com.magmamobile.mmusia.activities.MMUSIABeforeExitActivity;
import com.magmamobile.mmusia.adapters.MoreGamesListAdapterEx;
import com.magmamobile.mmusia.data.LanguageBase;
import com.magmamobile.mmusia.parser.data.ApiBase;
import com.magmamobile.mmusia.parser.parsers.JSonNews;
import com.magmamobile.mmusia.utils.MMUtils;
import com.magmamobile.mmusia.views.MoreGamesDialogView;
import com.magmamobile.mmusia.wrappers.FeatureWrapper23;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import up.FeatureWrapper;

public class MMUSIA
{
    private static class MMUSIABundle
    {

        public Drawable drawable;
        public LinearLayout viewContent;
        public ImageView viewImage;
        public TextView viewText;

        private MMUSIABundle()
        {
        }

        MMUSIABundle(MMUSIABundle mmusiabundle)
        {
            this();
        }
    }

    private static class MMUSIAHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            try
            {
                if(message.what == 256)
                {
                    MMUSIABundle mmusiabundle = (MMUSIABundle)message.obj;
                    mmusiabundle.viewImage.setImageDrawable(mmusiabundle.drawable);
                    mmusiabundle.viewImage.setVisibility(View.VISIBLE);
                }
                return;
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }

        private MMUSIAHandler()
        {
        }

        MMUSIAHandler(MMUSIAHandler mmusiahandler)
        {
            this();
        }
    }


    public static boolean IS_AMAZON = false;
    private static boolean IS_NOTRE_TEMPS = false;
    public static String LANGUAGE = null;
    public static int RES_ID_BEFOREEXIT_BTN_CLOSE = 0;
    public static int RES_ID_BEFOREEXIT_CHK_DONTSHOW = 0;
    public static int RES_ID_IMG_MOREGAMES_HEAD = 0;
    public static int RES_ID_ITEM_DATE = 0;
    public static int RES_ID_ITEM_DESC = 0;
    public static int RES_ID_ITEM_IMG = 0;
    public static int RES_ID_ITEM_LINEARITEM = 0;
    public static int RES_ID_ITEM_TITLE = 0;
    public static int RES_ID_LISTVIEW_APPUPDATE = 0;
    public static int RES_ID_LISTVIEW_MAINTAB = 0;
    public static int RES_ID_LISTVIEW_MOREGAMES = 0;
    public static int RES_ID_LISTVIEW_NEWSLIST = 0;
    public static int RES_ID_MOREGAMES_ITEM_FREE = 0;
    public static int RES_ID_PREF_CHECK_ENABLE = 0;
    public static int RES_ID_TAB_NEWS = 0;
    public static int RES_ID_TAB_UPDATE = 0;
    public static int RES_ID_TITLE_MOREGAMES_HEAD = 0;
    public static String RefererComplement = "";
    public static boolean _notifAllowed = false;
    public static ApiBase api;
    public static ApiBase apiLog;
    public static final String apiUrl_Magma = "http://api.magmamobile.com/api/mmusia.ashx";
    public static final String apiUrl_NotreTemps = "http://notretemps.magmamobile.com/api/mmusiant.ashx";
    public static int appIconID = 0;
    public static MMUSIAHandler handler = null;
    private static FeatureWrapper instance;
    private static NotificationManager mNotificationManager;
    private static String packageName = null;
    public static float screenDensity = 0.0F;
    public static Typeface tf = null;

    public MMUSIA()
    {
    }

    public static Drawable ImageOperations(String s)
    {
        HttpURLConnection httpurlconnection;
        InputStream inputstream;
        Drawable drawable;
        byte abyte0[];
        Drawable drawable1;
        try
        {
            httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpurlconnection.setConnectTimeout(3000);
            httpurlconnection.setReadTimeout(3000);
            httpurlconnection.connect();
            inputstream = httpurlconnection.getInputStream();
        }
        catch(MalformedURLException malformedurlexception)
        {
            MCommon.Log_e((new StringBuilder("DialogImage ImageOperations Malformed :: ")).append(s).toString());
            return null;
        }
        catch(IOException ioexception)
        {
            MCommon.Log_e((new StringBuilder("DialogImage ImageOperations IO :: ")).append(ioexception.getMessage()).toString());
            MCommon.Log_e(s);
            return null;
        }
        catch(Exception exception)
        {
            MCommon.Log_e((new StringBuilder("DialogImage ImageOperations Exception Image :: ")).append(exception.getMessage()).toString());
            MCommon.Log_e(s);
            return null;
        }
        drawable = null;
        try {
            if (inputstream != null) {
                abyte0 = readBytes(inputstream);
                inputstream.close();
                drawable1 = loadResizedBitmap(abyte0);
                drawable = drawable1;
            }
        }catch(Exception e) {}
        httpurlconnection.disconnect();
        return drawable;
    }

    public static void LAppOfTheDay(final Context context, final int id)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.LAppOfTheDay_sync(context, id);
            }
        }).start();
    }

    private static void LAppOfTheDay_sync(Context context, int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("aodid", (new StringBuilder(String.valueOf(i))).toString()));
        arraylist.add(new BasicNameValuePair("pn", MCommon.getAppPackageName(context)));
        arraylist.add(new BasicNameValuePair("a", "appoday"));
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static void LNews(final Context context, final int newsid)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.LNews_async(context, newsid);
            }
        }).start();
    }

    private static void LNews_async(Context context, int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("nid", (new StringBuilder(String.valueOf(i))).toString()));
        arraylist.add(new BasicNameValuePair("a", "click"));
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static void LPromoDialog(final Context context, final String pName, final int id)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.LPromoDialog_async(context, pName, id);
            }
        }).start();
    }

    private static void LPromoDialog_async(Context context, String s, int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("prid", (new StringBuilder(String.valueOf(i))).toString()));
        arraylist.add(new BasicNameValuePair("pn", s));
        arraylist.add(new BasicNameValuePair("a", "clickpromo"));
        arraylist.add(new BasicNameValuePair("dlg", "1"));
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static void LUpdate(final Context context, final String pName)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.LUpdate_async(context, pName);
            }
        }).start();
    }

    public static void LUpdateDialog(final Context context, final String pName)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.LUpdateDialog_async(context, pName);
            }
        }).start();
    }

    private static void LUpdateDialog_async(Context context, String s)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("pn", s));
        arraylist.add(new BasicNameValuePair("a", "click"));
        arraylist.add(new BasicNameValuePair("dlg", "1"));
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private static void LUpdate_async(Context context, String s)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("pn", s));
        arraylist.add(new BasicNameValuePair("a", "click"));
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static void activateNews(final Context context, final boolean activation)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.activateNews_async(context, activation);
            }
        }).start();
    }

    public static void activateNews_async(Context context, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        if(flag)
        {
            arraylist.add(new BasicNameValuePair("a", "activate"));
        } else
        {
            arraylist.add(new BasicNameValuePair("a", "desactivate"));
        }
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static final String apiUrl()
    {
        if(IS_NOTRE_TEMPS)
        {
            return "http://notretemps.magmamobile.com/api/mmusiant.ashx";
        } else
        {
            return "http://api.magmamobile.com/api/mmusia.ashx";
        }
    }

    private static final MMUSIABundle createDialogView(Context context)
    {
        MMUSIABundle mmusiabundle = new MMUSIABundle(null);
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        ImageView imageview = new ImageView(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -1);
        imageview.setImageResource(android.R.drawable.ic_dialog_info);
        layoutparams.setMargins(dpi(10F), dpi(10F), dpi(10F), dpi(10F));
        layoutparams.width = dpi(48F);
        layoutparams.height = dpi(48F);
        linearlayout.addView(imageview, layoutparams);
        TextView textview = new TextView(context);
        android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, -1);
        layoutparams1.setMargins(dpi(10F), dpi(10F), dpi(10F), dpi(10F));
        textview.setGravity(16);
        textview.setTextSize(2, 15F);
        linearlayout.addView(textview, layoutparams1);
        mmusiabundle.viewImage = imageview;
        mmusiabundle.viewText = textview;
        mmusiabundle.viewContent = linearlayout;
        return mmusiabundle;
    }

    public static final int dpi(float f)
    {
        if(screenDensity != 1.0F)
        {
            f *= screenDensity;
        }
        return (int)f;
    }

    public static FeatureWrapper featureWrapper()
    {
        if(instance == null)
        {
            if(MCommon.getSDKVersion() >= 23)
            {
                instance = new FeatureWrapper23();
            } else
            {
                instance = new FeatureWrapper();
            }
        }
        return instance;
    }

    public static void getLatestNews(Context context, boolean flag, boolean flag1)
    {
        boolean flag2;
        int i;
        int j;
        int k;
        String s;
        String s1;
        up.FeatureWrapper.Pair apair[];
        int l;
        String s2;
        String s3;
        up.FeatureWrapper.Pair apair1[];
        int i1;
        try
        {
            api = JSonNews.loadItems(context, apiUrl(), MCommon.buildUrlParam(context, 0, flag1), false);
            if(api == null)
            {
                return;
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return;
        }
        if(flag)
        {
            if(api.HasNewNews == 1 && _notifAllowed)
            {
                MCommon.Log_d("NOTIF !!!");
                s2 = LanguageBase.NOTIF_NEWS_TITLE;
                s3 = LanguageBase.NOTIF_NEWS_DESC;
                apair1 = new up.FeatureWrapper.Pair[1];
                apair1[0] = new up.FeatureWrapper.Pair("tab", "news");
                i1 = context.getResources().getIdentifier(MCommon.iconFileName, "drawable", context.getPackageName());
                featureWrapper().sendNotification(context, s2, s3, null, false, com.magmamobile.mmusia.activities.MMUSIAActivity.class, i1, 0xf40e4, 16, apair1);
                MCommon.Log_d("NOTIF END !!!");
            }
            if(api.HasNewUpdates == 1 && _notifAllowed)
            {
                s = LanguageBase.NOTIF_UPDATE_TITLE;
                s1 = LanguageBase.NOTIF_UPDATE_DESC;
                apair = new up.FeatureWrapper.Pair[1];
                apair[0] = new up.FeatureWrapper.Pair("tab", "app");
                l = context.getResources().getIdentifier(MCommon.iconFileName, "drawable", context.getPackageName());
                featureWrapper().sendNotification(context, s, s1, null, false, com.magmamobile.mmusia.activities.MMUSIAActivity.class, l, 0xf40e4, 16, apair);
            }
        }
        if(api.HasNewVersionAvailable == 1) {
            if (_notifAllowed) {
                popupNewVersionDialog(context);
            }
            return;
        }
        if(!_notifAllowed || MCommon.getlaunchCount(context) <= 1)
            return;
        i = api.promoId;
        flag2 = false;
        if(i > 0) {
            j = api.promoId;
            k = MCommon.getLatestPromoIDPref1(context);
            flag2 = false;
            if (j != k) {
                if (MMUtils.isPackageNameInDevice(context, api.promoPName)) {
                    MCommon.Log_w((new StringBuilder(String.valueOf(api.promoPName))).append(" ALREADY EXISTS").toString());
                    MCommon.resetPromoCount(context);
                    MCommon.setLatestPromoIDPref1(context, api.promoId);
                    flag2 = false;
                } else {
                    MCommon.resetPromoCount(context);
                    flag2 = true;
                    popupPromoDialog(context, api, 1);
                    MCommon.setLatestPromoIDPref1(context, api.promoId);
                    MCommon.setLatestPromoIDPref2(context, 0);
                    MCommon.setLatestPromoIDPref3(context, 0);
                }
            }
        }
        if(!(api.promoId2 <= 0 || flag2)) {
            if (!(MCommon.getPromoCount(context) % 3 != 0 || api.promoId2 == MCommon.getLatestPromoIDPref2(context))) {
                if (MMUtils.isPackageNameInDevice(context, api.promoPName2)) {
                    MCommon.Log_w((new StringBuilder(String.valueOf(api.promoPName2))).append(" ALREADY EXISTS").toString());
                    MCommon.resetPromoCount(context);
                    MCommon.setLatestPromoIDPref2(context, api.promoId2);
                    flag2 = false;
                } else {
                    MCommon.resetPromoCount(context);
                    flag2 = true;
                    popupPromoDialog(context, api, 2);
                    MCommon.setLatestPromoIDPref2(context, api.promoId2);
                }
            }
        }
        if(!(api.promoId3 <= 0 || flag2)) {
            if (!(MCommon.getPromoCount(context) % 3 != 0 || api.promoId3 == MCommon.getLatestPromoIDPref3(context))) {
                if (MMUtils.isPackageNameInDevice(context, api.promoPName3)) {
                    MCommon.Log_w((new StringBuilder(String.valueOf(api.promoPName3))).append(" ALREADY EXISTS").toString());
                    MCommon.resetPromoCount(context);
                    MCommon.setLatestPromoIDPref3(context, api.promoId3);
                } else {
                    MCommon.resetPromoCount(context);
                    popupPromoDialog(context, api, 3);
                    MCommon.setLatestPromoIDPref3(context, api.promoId3);
                }
            }
        }
        MCommon.Log_d((new StringBuilder("Modulo : ")).append(MCommon.getPromoCount(context)).append("%3=").append(MCommon.getPromoCount(context) % 3).toString());
        MCommon.promoCountIncrement(context);
    }

    public static Typeface getTypeFace()
    {
        if(tf != null)
        {
            return tf;
        } else
        {
            return Typeface.DEFAULT;
        }
    }

    public static void lClickBeforeExit(final Context context, final String pname)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.lClickBeforeExit_async(context, pname);
            }
        }).start();
    }

    private static void lClickBeforeExit_async(Context context, String s)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("pn", MCommon.getAppPackageName(context)));
        arraylist.add(new BasicNameValuePair("pn2", s));
        arraylist.add(new BasicNameValuePair("a", "exit"));
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static void lClickMoreApp(final Context context, final String pname)
    {
        (new Thread() {
            public void run()
            {
                MMUSIA.lClickMoreApp_async(context, pname);
            }
        }).start();
    }

    private static void lClickMoreApp_async(Context context, String s)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", MCommon.getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("pn", MCommon.getAppPackageName(context)));
        arraylist.add(new BasicNameValuePair("pn2", s));
        arraylist.add(new BasicNameValuePair("a", "moregame"));
        try
        {
            apiLog = JSonNews.loadItems(context, apiUrl(), arraylist, true);
            return;
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    public static final void launch(Activity activity, int i)
    {
        activity.startActivityForResult(new Intent(activity, com.magmamobile.mmusia.activities.MMUSIAActivity.class), i);
    }

    public static final void launchBeforeExit(Activity activity, int i)
    {
        activity.startActivityForResult(new Intent(activity, com.magmamobile.mmusia.activities.MMUSIABeforeExitActivity.class), i);
    }

    public static final void launchBeforeExitNoResult(Activity activity)
    {
        activity.startActivity(new Intent(activity, com.magmamobile.mmusia.activities.MMUSIABeforeExitActivity.class));
    }

    private static final void loadNotifIconAsync(final MMUSIABundle bundle, final String imgUrl)
    {
        if(imgUrl == null || imgUrl.equals(""))
        {
            return;
        } else
        {
            (new Thread() {
                public void run()
                {
                    bundle.drawable = MMUSIA.ImageOperations(imgUrl);
                    if(bundle.drawable != null)
                    {
                        MMUSIA.handler.sendMessage(MMUSIA.handler.obtainMessage(256, bundle));
                    }
                }
            }).start();
            return;
        }
    }

    public static Drawable loadResizedBitmap(byte abyte0[])
    {
        android.graphics.BitmapFactory.Options options;
        int i;
        android.graphics.Bitmap bitmap;
        options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        i = options.outHeight;
        bitmap = null;
        if(i > 0) {
            int j;
            j = options.outWidth;
            bitmap = null;
            if (j > 0) {
                options.inJustDecodeBounds = false;
                options.inSampleSize = 1;
                while (options.outWidth / options.inSampleSize > 64 && options.outHeight / options.inSampleSize > 64) {
                    options.inSampleSize = 1 + options.inSampleSize;
                }
                bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
            }
        }
        return new BitmapDrawable(bitmap);
    }

    public static void notifNewVersionDialogThread(final Context context)
    {
        try
        {
            MMUSIABundle mmusiabundle = createDialogView(context);
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setCancelable(true);
            if(appIconID != 0)
            {
                mmusiabundle.viewImage.setImageResource(appIconID);
            }
            builder.setTitle(LanguageBase.DIALOG_UPDATE_TITLE);
            mmusiabundle.viewText.setText(LanguageBase.DIALOG_UPDATE_MESSAGE);
            builder.setView(mmusiabundle.viewContent);
            builder.setPositiveButton(LanguageBase.DIALOG_UPDATE_YES, new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int i)
                {
                    MMUSIA.LUpdateDialog(context, MCommon.getAppPackageName(context));
                    MCommon.openMarket(context, (new StringBuilder(String.valueOf(MCommon.getAppPackageName(context)))).append("&referrer=utm_source%3DMMUSIA%26utm_medium%3DMMUSIADialogUpdate%26utm_campaign%3DMMUSIADialogUpdate").toString());
                }
            });
            builder.setNegativeButton(LanguageBase.DIALOG_UPDATE_NO, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

            });
            builder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                public void onCancel(DialogInterface dialoginterface)
                {
                }

            });
            builder.show();
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void notifPromoDialogThread(final Context context, ApiBase apibase, int i)
    {
        String s;
        String s1;
        String s2;
        String s3;
        int j;
        s = "";
        s1 = "";
        s2 = "";
        s3 = "";
        j = 0;
        switch (i) {
            default:
                break;
            case 1:
                s = apibase.promoIconUrl;
                s1 = apibase.promoTitle;
                s2 = apibase.promoDesc;
                s3 = apibase.promoUrl;
                j = apibase.promoId;
                break;
            case 2:
                s = apibase.promoIconUrl2;
                s1 = apibase.promoTitle2;
                s2 = apibase.promoDesc2;
                s3 = apibase.promoUrl2;
                j = apibase.promoId2;
                break;
            case 3:
                s = apibase.promoIconUrl3;
                s1 = apibase.promoTitle3;
                s2 = apibase.promoDesc3;
                s3 = apibase.promoUrl3;
                j = apibase.promoId3;
                break;
        }
        String s4 = s;
        String s5 = s1;
        String s6 = s2;
        final String promoUrl = s3;
        final int promoId = j;
        try
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setCancelable(true);
            MMUSIABundle mmusiabundle = createDialogView(context);
            mmusiabundle.viewText.setText(s6);
            builder.setView(mmusiabundle.viewContent);
            builder.setTitle(s5);
            builder.setPositiveButton(LanguageBase.DIALOG_UPDATE_YES, new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface, int k)
                {
                    MMUSIA.LPromoDialog(context, MCommon.getAppPackageName(context), promoId);
                    MCommon.openMarketLink(context, promoUrl);
                }
            });
            builder.setNegativeButton(LanguageBase.DIALOG_UPDATE_NO, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int k)
                {
                }

            });
            builder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                public void onCancel(DialogInterface dialoginterface)
                {
                }

            });
            builder.show();
            loadNotifIconAsync(mmusiabundle, s4);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static final void openAppOfTheDay(Context context)
    {
        try
        {
            if(!api.appodayUrl.equals(""))
            {
                if(!api.appodayUrl.startsWith("http://") && !api.appodayUrl.startsWith("https://"))
                    MCommon.openMarketLink(context, (new StringBuilder(String.valueOf(api.appodayUrl))).append(RefererComplement).toString());
                else
                    MCommon.openUrlPage(context, api.appodayUrl);
            }
            LAppOfTheDay(context, api.appodayId);
        }
        catch(Exception exception)
        {
        }
    }

    public static final void ping(Context context)
    {
    }

    private static final void popupNewVersionDialog(final Context context)
    {
        handler.post(new Runnable() {
            public void run()
            {
                MMUSIA.notifNewVersionDialogThread(context);
            }
        });
    }

    private static final void popupPromoDialog(final Context context, final ApiBase api, final int PromoNum)
    {
        handler.post(new Runnable() {
            public void run()
            {
                MMUSIA.notifPromoDialogThread(context, api, PromoNum);
            }
        });
    }

    public static byte[] readBytes(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
        do
        {
            int i = inputstream.read(abyte0);
            if(i == -1)
            {
                return bytearrayoutputstream.toByteArray();
            }
            bytearrayoutputstream.write(abyte0, 0, i);
        } while(true);
    }

    public static void setTypeFace(Typeface typeface)
    {
        tf = typeface;
    }

    public static void showMoreGames(Activity activity)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        AlertDialog alertdialog = builder.create();
        MoreGamesDialogView moregamesdialogview = new MoreGamesDialogView(activity);
        moregamesdialogview.setBackgroundColor(-1);
        moregamesdialogview.setDrawingCacheBackgroundColor(-1);
        ListView listview = (ListView)moregamesdialogview.findViewById(RES_ID_LISTVIEW_MOREGAMES);
        MoreGamesListAdapterEx moregameslistadapterex = new MoreGamesListAdapterEx(activity);
        if(api != null)
        {
            moregameslistadapterex.setData(api.moregames);
            MCommon.Log_e((new StringBuilder("MMUSIA MORE GAMES LIST : ")).append(api.moregames.length).toString());
        } else
        {
            MCommon.Log_e("MMUSIA EMPTY MORE GAMES LIST");
        }
        try
        {
            listview.setAdapter(moregameslistadapterex);
        }
        catch(IllegalStateException illegalstateexception)
        {
            illegalstateexception.printStackTrace();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        listview.setBackgroundColor(0xffffff);
        listview.setCacheColorHint(-1);
        alertdialog.getWindow().setFlags(1024, 1024);
        alertdialog.setView(moregamesdialogview);
        alertdialog.setIcon(activity.getResources().getIdentifier(MCommon.iconFileName, "drawable", activity.getPackageName()));
        alertdialog.setCancelable(true);
        alertdialog.setTitle(LanguageBase.DIALOG_MOREGAMES_TITLE);
        builder.setPositiveButton(LanguageBase.DIALOG_SETTINGS_CLOSE, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

        });
        alertdialog.show();
        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
            }

        });
    }

    public void Init(Context context, String s, String s1)
    {
        Init(context, s, s1, true, false, true);
    }

    public void Init(Context context, String s, String s1, boolean flag)
    {
        Init(context, s, s1, flag, false, true);
    }

    public void Init(final Context context, String s, String s1, boolean flag, boolean flag1, boolean flag2)
    {
        MCommon.Log_i("MMUSIA INIT");
        IS_NOTRE_TEMPS = flag1;
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        screenDensity = displaymetrics.density;
        packageName = context.getPackageName();
        _notifAllowed = flag;
        RefererComplement = s1;
        handler = new MMUSIAHandler(null);
        appIconID = context.getResources().getIdentifier("app_icon", "drawable", packageName);
        if(appIconID == 0)
        {
            appIconID = context.getResources().getIdentifier("icon", "drawable", packageName);
        }
        if(MCommon.getSDKVersion() >= 20)
        {
            if(MCommon.getRDrawable(context, "mmusia_notif") != 0)
            {
                MCommon.iconFileName = "mmusia_notif";
            } else
            {
                MCommon.iconFileName = "app_icon";
            }
        } else
        {
            MCommon.iconFileName = "app_icon";
        }
        if(s.equals("fr"))
        {
            LanguageBase.setLanguageFR();
            LANGUAGE = "fr";
        } else
        {
            LanguageBase.setLanguageEN();
            LANGUAGE = "en";
        }
        mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(flag2)
        {
            (new Thread() {
                public void run()
                {
                    Context context1 = context;
                    boolean flag3;
                    boolean flag4;
                    boolean flag5;
                    if(MCommon.getPrefNotifStatus(context))
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    flag4 = MCommon.getPrefNotifStatus(context);
                    flag5 = false;
                    if(!flag4)
                    {
                        flag5 = true;
                    }
                    MMUSIA.getLatestNews(context1, flag3, flag5);
                    MMUSIA.ping(context);
                }
            }).start();
            MCommon.launchCountIncrement(context);
        }
    }

    static 
    {
        RES_ID_LISTVIEW_APPUPDATE = 10000;
        RES_ID_LISTVIEW_NEWSLIST = 10001;
        RES_ID_LISTVIEW_MAINTAB = 10002;
        RES_ID_TAB_UPDATE = 10003;
        RES_ID_TAB_NEWS = 10004;
        RES_ID_ITEM_LINEARITEM = 10005;
        RES_ID_ITEM_TITLE = 10006;
        RES_ID_ITEM_DATE = 10007;
        RES_ID_ITEM_DESC = 10008;
        RES_ID_ITEM_IMG = 10009;
        RES_ID_LISTVIEW_MOREGAMES = 10010;
        RES_ID_IMG_MOREGAMES_HEAD = 10011;
        RES_ID_TITLE_MOREGAMES_HEAD = 10012;
        RES_ID_MOREGAMES_ITEM_FREE = 10013;
        RES_ID_BEFOREEXIT_BTN_CLOSE = 10014;
        RES_ID_BEFOREEXIT_CHK_DONTSHOW = 10015;
        IS_NOTRE_TEMPS = false;
        IS_AMAZON = false;
        RES_ID_PREF_CHECK_ENABLE = 0;
    }
}
