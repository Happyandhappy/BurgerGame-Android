package com.magmamobile.mmusia.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.magmamobile.mmusia.MCommon;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.adapters.NewsListAdapterEx;
import com.magmamobile.mmusia.data.LanguageBase;
import com.magmamobile.mmusia.parser.data.ApiBase;
import com.magmamobile.mmusia.parser.data.ItemNews;
import com.magmamobile.mmusia.views.MainView;

public class MMUSIAActivity extends Activity
{

    private final int MENU_MMUSIA_QUIT = 3;
    private final int MENU_MMUSIA_REFRESH = 2;
    private final int MENU_MMUSIA_SETTINGS = 1;
    private final int MSG_CHANGE_MESSAGE = 2;
    private final int MSG_CLOSE = 1;
    private final int MSG_LOADJSONFINISH = 5;
    private final int MSG_OPEN = 0;
    private final int MSG_TOAST = 4;
    private ProgressDialog mDialog;
    private ListView mListAppUpdate;
    private ListView mListNewsList;
    private TabHost mTabs;
    Handler messageHandler;

    public MMUSIAActivity()
    {
        messageHandler = new Handler() {
            public void handleMessage(Message message)
            {
                switch(message.what)
                {
                default:
                    return;

                case 0: // '\0'
                    mDialog = ProgressDialog.show(MMUSIAActivity.this, LanguageBase.DIALOG_LOADING, LanguageBase.DIALOG_PLEASEWAIT, true, true);
                    return;

                case 1: // '\001'
                    try
                    {
                        mDialog.dismiss();
                        return;
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                    return;

                case 2: // '\002'
                    mDialog.setMessage((String)message.obj);
                    return;

                case 4: // '\004'
                    Toast.makeText(MMUSIAActivity.this, (String)message.obj, Toast.LENGTH_LONG).show();
                    return;

                case 5: // '\005'
                    displayNews();
                    return;

                case 999999: 
                    finish();
                    return;
                }
            }
        };
    }

    public void displayNews()
    {
        try
        {
            if(MMUSIA.api.news == null)
            {
                MMUSIA.api.news = new ItemNews[0];
            }
            NewsListAdapterEx newslistadapterex = new NewsListAdapterEx(this);
            newslistadapterex.setData(MMUSIA.api.news);
            mListNewsList.setAdapter(newslistadapterex);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
label0:
        {
            if(MMUSIA.api.updates.length == 0)
            {
                if(mListAppUpdate == null)
                {
                    break label0;
                }
                mTabs.setCurrentTab(1);
            }
            return;
        }
        try
        {
            mTabs.setCurrentTab(0);
            return;
        }
        catch(Exception exception1)
        {
            exception1.printStackTrace();
        }
        return;
    }

    public void lNews(Context context, int i)
    {
        MMUSIA.LNews(context, i);
    }

    public void lUpdate(Context context, String s)
    {
        MMUSIA.LUpdate(context, s);
    }

    public void lUpdateDialog(Context context, String s)
    {
        MMUSIA.LUpdateDialog(context, s);
    }

    public void loadNews(final Context context)
    {
        (new Thread() {
            public void run()
            {
                loadNewsThread(context);
            }
        }).start();
    }

    public void loadNewsThread(Context context)
    {
        messageHandler.sendMessage(Message.obtain(messageHandler, 0));
        MMUSIA.getLatestNews(context, false, true);
        messageHandler.sendMessage(Message.obtain(messageHandler, 1));
        messageHandler.sendMessage(Message.obtain(messageHandler, 5));
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        LanguageBase.reloadIfNeeded();
        Bundle bundle1;
        try
        {
            if(MMUSIA.appIconID == 0)
            {
                MMUSIA.appIconID = getResources().getIdentifier(MCommon.iconFileName, "drawable", getPackageName());
            }
        }
        catch(Exception exception)
        {
            MCommon.Log_e("TRIED TO RELOAD ICON APP ID WITHOUT SUCCESS !!!");
            exception.printStackTrace();
        }
        bundle1 = getIntent().getExtras();
        setContentView(new MainView(this));
        mListAppUpdate = (ListView)findViewById(MMUSIA.RES_ID_LISTVIEW_APPUPDATE);
        mListNewsList = (ListView)findViewById(MMUSIA.RES_ID_LISTVIEW_NEWSLIST);
        mTabs = (TabHost)findViewById(MMUSIA.RES_ID_LISTVIEW_MAINTAB);
        mTabs.setVisibility(View.VISIBLE);
        mTabs.setup();

        android.widget.TabHost.TabSpec tabspec;
        //strange part
        try
        {
            if(mListAppUpdate != null)
            {
                MCommon.Log_e((new StringBuilder("MMUSIA.RES_DRAWABLE_ICONAPP : ")).append(MMUSIA.appIconID).toString());
                if(MMUSIA.appIconID != 0)
                    mTabs.addTab(mTabs.newTabSpec("tab_main_channel").setIndicator(LanguageBase.TAB_UPDATES, MCommon.getAssetDrawableResize(this, getResources().getDrawable(MMUSIA.appIconID), MCommon.dpiImage(24), MCommon.dpiImage(24))).setContent(MMUSIA.RES_ID_TAB_UPDATE));
                else
                    mTabs.addTab(mTabs.newTabSpec("tab_main_channel").setIndicator(LanguageBase.TAB_UPDATES).setContent(MMUSIA.RES_ID_TAB_UPDATE));
            }
        }
        catch(android.content.res.Resources.NotFoundException notfoundexception)
        {
            MCommon.Log_e("OULALA ICON INTROUVABLE !!!");
            MCommon.Log_e((new StringBuilder("MMUSIA.RES_DRAWABLE_ICONAPP : ")).append(MMUSIA.appIconID).toString());
            MCommon.Log_e((new StringBuilder("LanguageBase.TAB_UPDATES : ")).append(LanguageBase.TAB_UPDATES).toString());
            MCommon.Log_e((new StringBuilder("MMUSIA.RES_ID_TAB_UPDATE : ")).append(MMUSIA.RES_ID_TAB_UPDATE).toString());
            tabspec = mTabs.newTabSpec("tab_main_channel").setIndicator(LanguageBase.TAB_UPDATES).setContent(MMUSIA.RES_ID_TAB_UPDATE);
            mTabs.addTab(tabspec);
            notfoundexception.printStackTrace();
        }

        mTabs.addTab(mTabs.newTabSpec("tab_main_last").setIndicator(LanguageBase.TAB_NEWS, MCommon.getAssetDrawableResize(this, MCommon.getAssetDrawable(this, "mmusiaicon.png"), MCommon.dpiImage(75), MCommon.dpiImage(24))).setContent(MMUSIA.RES_ID_TAB_NEWS));
        if(mListAppUpdate != null)
        {
            if(bundle1 != null)
            {
                if(bundle1.getString("tab").equals("news"))
                    mTabs.setCurrentTab(1);
                else
                    mTabs.setCurrentTab(0);
            }
        } else
            mTabs.setCurrentTab(0);
        if(MMUSIA.api != null)
        {
            if(MMUSIA.api.news.length == 0)
                loadNews(this);
            else
                displayNews();
        } else
            loadNews(this);
        mListNewsList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                ItemNews itemnews = ((NewsListAdapterEx)mListNewsList.getAdapter()).getItem(i);
                if(!itemnews.NewsUrlMarket.equals(""))
                {
                    MCommon.openMarketLink(MMUSIAActivity.this, itemnews.NewsUrlMarket);
                } else
                {
                    MCommon.openUrlPage(MMUSIAActivity.this, itemnews.NewsUrlLink);
                }
                lNews(MMUSIAActivity.this, itemnews.NewsID);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, 1, 0, LanguageBase.MENU_SETTINGS).setIcon(MCommon.getAssetDrawable(this, "mmusiasettings.png"));
        menu.add(0, 2, 0, LanguageBase.MENU_REFRESH).setIcon(MCommon.getAssetDrawable(this, "mmusiarefresh.png"));
        menu.add(0, 3, 0, LanguageBase.MENU_QUIT).setIcon(MCommon.getAssetDrawable(this, "mmusiaexit.png"));
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        switch (menuitem.getItemId()) {
            default:
                break;
            case 1:
                MCommon.showPrefs(this);
                break;
            case 2:
                loadNews(this);
                break;
            case 3:
                finish();
                break;
        }
        return true;
    }
}
