package com.magmamobile.mmusia;

import android.app.Activity;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.*;
import android.widget.CheckBox;
import android.widget.Toast;
import com.magmamobile.mmusia.data.LanguageBase;
import com.magmamobile.mmusia.views.PrefView;
import java.io.*;
import java.util.*;
import org.apache.http.message.BasicNameValuePair;

import javax.xml.datatype.Duration;

public class MCommon
{

    public static String GUID = "";
    public static final String TAG = "MMUSIA";
    public static final boolean USE_DEBUG = false;
    public static Map drawableMap = new HashMap();
    public static String iconFileName = "icon";

    public MCommon()
    {
    }

    public static void Log_d(String s)
    {
    }

    public static void Log_d(String s, String s1)
    {
    }

    public static void Log_e(String s)
    {
    }

    public static void Log_e(String s, String s1)
    {
    }

    public static void Log_i(String s)
    {
    }

    public static void Log_i(String s, String s1)
    {
    }

    public static void Log_v(String s)
    {
    }

    public static void Log_v(String s, String s1)
    {
    }

    public static void Log_w(String s)
    {
    }

    public static void Log_w(String s, String s1)
    {
    }

    public static final String alphaNum(String s, String s1)
    {
        return s.replaceAll("[^a-zA-Z0-9]", s1);
    }

    public static final String alphaNumWithAccent(String s, String s1)
    {
        return s.replaceAll("[^a-zA-Z\300-\3770-9]", s1);
    }

    public static final List buildUrlParam(Context context, int i, boolean flag)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("di", getDeviceID(context)));
        arraylist.add(new BasicNameValuePair("lng", getLanguage()));
        arraylist.add(new BasicNameValuePair("lid", (new StringBuilder(String.valueOf(i))).toString()));
        arraylist.add(new BasicNameValuePair("ver", (new StringBuilder(String.valueOf(getAppVersionCode(context)))).toString()));
        arraylist.add(new BasicNameValuePair("pn", getAppPackageName(context)));
        arraylist.add(new BasicNameValuePair("pm", getModelNumber()));
        arraylist.add(new BasicNameValuePair("sw", getScreenSize(context)));
        arraylist.add(new BasicNameValuePair("sv", getSDK()));
        arraylist.add(new BasicNameValuePair("mmver", getMMUSIAVersion()));
        if(flag)
        {
            arraylist.add(new BasicNameValuePair("gu", "1"));
        }
        return arraylist;
    }

    public static final String checkURL(String s)
    {
        if(MMUSIA.IS_AMAZON)
        {
            if(s == null)
            {
                return null;
            }
            if(s.contains("market://search?"))
            {
                String s6 = getPackageNameFromURL(s);
                return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s6).toString();
            }
            if(s.contains("market://details?"))
            {
                String s5 = getPackageNameFromURL(s);
                return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s5).toString();
            }
            if(s.contains("http://play.google.com/store/apps/search?") || s.contains("https://play.google.com/store/apps/search?"))
            {
                String s1 = getPackageNameFromURL(s);
                return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s1).toString();
            }
            if(s.contains("http://play.google.com/store/apps/details?") || s.contains("https://play.google.com/store/apps/details?"))
            {
                String s2 = getPackageNameFromURL(s);
                return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s2).toString();
            }
            if(s.contains("http://market.android.com/search?") || s.contains("https://market.android.com/search?"))
            {
                String s3 = getPackageNameFromURL(s);
                return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s3).toString();
            }
            if(s.contains("http://market.android.com/details?") || s.contains("https://market.android.com/details?"))
            {
                String s4 = getPackageNameFromURL(s);
                return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s4).toString();
            }
        }
        return s;
    }

    public static final int dpiImage(int i)
    {
        return MMUSIA.dpi(i);
    }

    public static final String generateString(InputStream inputstream)
    {
        BufferedReader bufferedreader;
        StringBuilder stringbuilder;
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuilder = new StringBuilder();
        try
        {
            while(true) {
                String s = bufferedreader.readLine();
                if(s == null)
                    break;
                stringbuilder.append(s);
            }
            inputstream.close();
        }
        catch(IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        return stringbuilder.toString();
    }

    public static final String generateString(InputStream inputstream, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, s));
            while (true) {
                String s1 = bufferedreader.readLine();
                if (s1 == null) {
                    try {
                        inputstream.close();
                    } catch (IOException ioexception) {
                        ioexception.printStackTrace();
                    }
                    return stringbuilder.toString();
                }
                stringbuilder.append(s1).append("\n");
            }
        }catch(Exception e) {
            return stringbuilder.toString();
        }
    }

    public static final String getAppPackageName(Context context)
    {
        String s;
        try
        {
            s = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return "";
        }
        return s;
    }

    public static final int getAppVersionCode(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return 0;
        }
        return i;
    }

    public static final Drawable getAssetDrawable(Activity activity, String s)
    {
        android.graphics.drawable.BitmapDrawable bitmapdrawable;
        try
        {
            bitmapdrawable = BitmapUtils.loadDrawable(activity, s);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return bitmapdrawable;
    }

    public static final Drawable getAssetDrawableResize(Context context, Drawable drawable, int i, int j)
    {
        if(getSDKVersion() < 4)
        {
            return BitmapUtils.getAssetDrawableResize(context, drawable, i, j);
        } else
        {
            return BitmapUtils16.getAssetDrawableResize(context, drawable, i, j);
        }
    }

    public static final boolean getBeforeExitDontShow(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("bExitDshow", false);
    }

    private static final String getDIDPref(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("DIDBIS", "");
    }

    public static final String getDeviceID(Context context)
    {
        String s;
        String s1;
        String s5;
        try
        {
            s = android.provider.Settings.System.getString(context.getContentResolver(), "android_id");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return "";
        }
        if(s != null)
        {
            if(!s.toLowerCase().equals("9774d56d682e549c"))
            {
                if(!s.toLowerCase().equals("575c2ef207c21d5b"))
                {
                    if(!s.toLowerCase().equals("22a000002457bbd5"))
                    {
                        if(!s.toLowerCase().equals("200146e3ff6bd264"))
                        {
                            if(!s.toLowerCase().equals("emulator"))
                            {
                                return s;
                            }
                            s = getDIDPref(context);
                            if(!s.equals("") && s != null)
                            {
                                return s;
                            }
                            s = (new StringBuilder("Emulator/")).append(UUID.randomUUID().toString()).toString();
                            setDIDPref(context, s);
                            return s;
                        }
                        s = getDIDPref(context);
                        if(!s.equals("") && s != null)
                        {
                            return s;
                        }
                        String s2;
                        s2 = (new StringBuilder("200146e3ff6bd264/")).append(UUID.randomUUID().toString()).toString();
                        setDIDPref(context, s2);
                        return s2;
                    }
                    s = getDIDPref(context);
                    if(!s.equals("") && s != null)
                    {
                        return s;
                    }
                    String s3;
                    s3 = (new StringBuilder("22a000002457bbd5/")).append(UUID.randomUUID().toString()).toString();
                    setDIDPref(context, s3);
                    return s3;
                }
                s = getDIDPref(context);
                if(!s.equals("") && s != null)
                {
                    return s;
                }
                String s4;
                s4 = (new StringBuilder("575c2ef207c21d5b/")).append(UUID.randomUUID().toString()).toString();
                setDIDPref(context, s4);
                return s4;
            }
            s = getDIDPref(context);
            if(!s.equals("") && s != null)
            {
                return s;
            }
            s5 = (new StringBuilder("9774d56d682e549c/")).append(UUID.randomUUID().toString()).toString();
            setDIDPref(context, s5);
            return s5;
        }
        s = getDIDPref(context);
        if(!s.equals("") && s != null)
        {
            return s;
        }
        s1 = (new StringBuilder("emulator/")).append(UUID.randomUUID().toString()).toString();
        setDIDPref(context, s1);
        return s1;
    }

    public static final String getLanguage()
    {
        if(MMUSIA.LANGUAGE == null)
        {
            MMUSIA.LANGUAGE = "en";
        }
        return MMUSIA.LANGUAGE;
    }

    public static final int getLatestPromoIDPref1(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("PROMOID1", 0);
    }

    public static final int getLatestPromoIDPref2(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("PROMOID2", 0);
    }

    public static final int getLatestPromoIDPref3(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("PROMOID3", 0);
    }

    public static final String getMMUSIAVersion()
    {
        return "5";
    }

    public static final String getModelNumber()
    {
        return Build.MODEL;
    }

    public static final String getOperatorName(Context context)
    {
        return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName();
    }

    private static final String getPackageNameFromURL(String s)
    {
        int i = s.indexOf("?id=");
        if(i < 0)
        {
            return "";
        } else
        {
            return s.substring(i + 4);
        }
    }

    public static final boolean getPrefNotifStatus(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("mmusianotif", true);
    }

    public static final int getPromoCount(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("PromoCount", 0);
    }

    protected static final int getRDrawable(Context context, String s)
    {
        int i;
        try
        {
            i = context.getResources().getIdentifier(s, "drawable", context.getPackageName());
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static final String getSDK()
    {
        return android.os.Build.VERSION.SDK;
    }

    public static final int getSDKVersion()
    {
        return Integer.parseInt(android.os.Build.VERSION.SDK);
    }

    public static final String getScreenSize(Context context)
    {
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return (new StringBuilder(String.valueOf(display.getWidth()))).append("x").append(display.getHeight()).toString();
    }

    public static final int getlaunchCount(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("LaunchCount", 0);
    }

    public static final boolean isSDKAPI4Mini()
    {
        int i;
        boolean flag;
        try
        {
            i = Integer.parseInt(android.os.Build.VERSION.SDK);
        }
        catch(NumberFormatException numberformatexception)
        {
            numberformatexception.printStackTrace();
            return false;
        }
        flag = false;
        if(i >= 4)
        {
            flag = true;
        }
        return flag;
    }

    public static final void launchCountIncrement(Context context)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("LaunchCount", 1 + getlaunchCount(context));
        editor.commit();
    }

    public static final void openMarket(Context context, String s)
    {
        try
        {
            ((Activity)context).startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("market://details?id=")).append(s).toString())), 2000);
        }
        catch(Exception exception)
        {
            Toast.makeText(context, LanguageBase.MARKET_NOT_FOUND, Toast.LENGTH_LONG).show();
        }
    }

    public static final void openMarketLink(Context context, String s)
    {
        try
        {
            ((Activity)context).startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(s)), 2000);
        }
        catch(Exception exception)
        {
            Toast.makeText(context, LanguageBase.MARKET_NOT_FOUND, Toast.LENGTH_LONG).show();
        }
    }

    public static final void openUrlPage(Context context, String s)
    {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
        ((Activity)context).startActivityForResult(intent, 9998);
    }

    public static final void promoCountIncrement(Context context)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("PromoCount", 1 + getPromoCount(context));
        editor.commit();
    }

    public static final void resetPromoCount(Context context)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("PromoCount", 0);
        editor.commit();
    }

    public static final void setBeforeExitDontShow(Context context, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("bExitDshow", flag);
        editor.commit();
    }

    private static final void setDIDPref(Context context, String s)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("DIDBIS", s);
        editor.commit();
    }

    public static final void setLatestPromoIDPref1(Context context, int i)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("PROMOID1", i);
        editor.commit();
    }

    public static final void setLatestPromoIDPref2(Context context, int i)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("PROMOID2", i);
        editor.commit();
    }

    public static final void setLatestPromoIDPref3(Context context, int i)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("PROMOID3", i);
        editor.commit();
    }

    public static final void setPrefNotifStatus(Context context, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("mmusianotif", flag);
        editor.commit();
    }

    public static final void showPrefs(final Context context)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        PrefView prefview = new PrefView(context);
        final CheckBox check = (CheckBox)prefview.findViewById(MMUSIA.RES_ID_PREF_CHECK_ENABLE);
        check.setChecked(getPrefNotifStatus(context));
        builder.setView(prefview);
        builder.setCancelable(true);
        builder.setTitle(LanguageBase.DIALOG_SETTINGS_TITLE);
        builder.setPositiveButton(LanguageBase.DIALOG_SETTINGS_CLOSE, new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i)
            {
                MCommon.setPrefNotifStatus(context, check.isChecked());
                MMUSIA.activateNews(context, check.isChecked());
            }
        });
        builder.show();
    }

}
